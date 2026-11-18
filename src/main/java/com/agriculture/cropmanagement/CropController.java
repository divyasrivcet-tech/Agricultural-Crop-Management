package com.agriculture.cropmanagement;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/crops")
public class CropController {
    private final JdbcTemplate jdbc;

    public CropController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping
    public List<Map<String, Object>> getAllCrops() {
        return jdbc.queryForList(
            "SELECT crop_id, crop_name, crop_type FROM crops ORDER BY crop_id"
        );
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCrop(@PathVariable int id) {
        List<Map<String, Object>> rows = jdbc.queryForList(
            "SELECT crop_id, crop_name, crop_type FROM crops WHERE crop_id = ?", id
        );
        if (rows.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Crop not found");
        }
        return rows.get(0);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addCrop(@RequestBody CropRequest crop) {
        validate(crop);
        return jdbc.queryForMap(
            "INSERT INTO crops (crop_name, crop_type) VALUES (?, ?) RETURNING crop_id, crop_name, crop_type",
            crop.cropName().trim(), crop.cropType().trim()
        );
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateCrop(@PathVariable int id, @RequestBody CropRequest crop) {
        validate(crop);
        List<Map<String, Object>> rows = jdbc.queryForList(
            "UPDATE crops SET crop_name = ?, crop_type = ? WHERE crop_id = ? RETURNING crop_id, crop_name, crop_type",
            crop.cropName().trim(), crop.cropType().trim(), id
        );
        if (rows.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Crop not found");
        }
        return rows.get(0);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteCrop(@PathVariable int id) {
        try {
            if (jdbc.update("DELETE FROM crops WHERE crop_id = ?", id) == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Crop not found");
            }
            return Map.of("message", "Crop deleted successfully");
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Crop is used in a yield record. Delete that record first."
            );
        }
    }

    private void validate(CropRequest crop) {
        if (crop == null || crop.cropName() == null || crop.cropName().isBlank()
                || crop.cropType() == null || crop.cropType().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Crop name and crop type are required"
            );
        }
    }

    public record CropRequest(String cropName, String cropType) {}
}
