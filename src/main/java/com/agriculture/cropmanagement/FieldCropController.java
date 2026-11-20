package com.agriculture.cropmanagement;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/field-crops")
public class FieldCropController {
    private final JdbcTemplate jdbc;

    public FieldCropController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping
    public List<Map<String, Object>> getCropsByField(
            @RequestParam Integer fieldId) {

        return jdbc.queryForList("""
            SELECT c.crop_id, c.crop_name, c.crop_type
            FROM field_crops fc
            JOIN crops c ON c.crop_id = fc.crop_id
            WHERE fc.field_id = ?
            ORDER BY c.crop_name
            """, fieldId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> addMapping(
            @RequestBody FieldCropRequest request) {

        if (request == null || request.fieldId() == null
                || request.cropId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Field ID and Crop ID are required"
            );
        }

        Boolean fieldExists = jdbc.queryForObject(
            "SELECT EXISTS(SELECT 1 FROM fields WHERE field_id = ?)",
            Boolean.class, request.fieldId()
        );

        Boolean cropExists = jdbc.queryForObject(
            "SELECT EXISTS(SELECT 1 FROM crops WHERE crop_id = ?)",
            Boolean.class, request.cropId()
        );

        if (!Boolean.TRUE.equals(fieldExists)
                || !Boolean.TRUE.equals(cropExists)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Field or Crop not found"
            );
        }

        int inserted = jdbc.update("""
            INSERT INTO field_crops (field_id, crop_id)
            VALUES (?, ?)
            ON CONFLICT (field_id, crop_id) DO NOTHING
            """, request.fieldId(), request.cropId());

        return Map.of(
            "message",
            inserted == 1 ? "Crop linked to field successfully"
                          : "This crop is already linked to the field"
        );
    }

    @DeleteMapping
    public Map<String, String> removeMapping(
            @RequestParam Integer fieldId,
            @RequestParam Integer cropId) {

        int deleted = jdbc.update(
            "DELETE FROM field_crops WHERE field_id = ? AND crop_id = ?",
            fieldId, cropId
        );

        if (deleted == 0) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Field-Crop relationship not found"
            );
        }

        return Map.of("message", "Field-Crop relationship removed");
    }

    public record FieldCropRequest(Integer fieldId, Integer cropId) {}
}