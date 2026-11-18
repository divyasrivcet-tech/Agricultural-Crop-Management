
package com.agriculture.cropmanagement;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final JdbcTemplate jdbc;

    public FarmerController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // View all farmers
    @GetMapping
    public List<Map<String, Object>> getAllFarmers() {
        String sql = """
            SELECT farmer_id, farmer_name, phone, village, district
            FROM farmers
            ORDER BY farmer_id
            """;

        return jdbc.queryForList(sql);
    }

    // View one farmer by ID
    @GetMapping("/{id}")
    public Map<String, Object> getFarmer(@PathVariable int id) {
        List<Map<String, Object>> rows = jdbc.queryForList(
            "SELECT farmer_id, farmer_name, phone, village, district FROM farmers WHERE farmer_id = ?",
            id
        );
        if (rows.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found");
        }
        return rows.get(0);
    }

    // Add a farmer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addFarmer(
            @RequestBody FarmerRequest farmer) {

        validate(farmer);

        String sql = """
            INSERT INTO farmers (farmer_name, phone, village, district)
            VALUES (?, ?, ?, ?)
            RETURNING farmer_id, farmer_name, phone, village, district
            """;

        return jdbc.queryForMap(
            sql,
            farmer.farmerName(),
            farmer.phone(),
            farmer.village(),
            farmer.district()
        );
    }

    // Update a farmer
    @PutMapping("/{id}")
    public Map<String, Object> updateFarmer(
            @PathVariable int id,
            @RequestBody FarmerRequest farmer) {

        validate(farmer);

        String sql = """
            UPDATE farmers
            SET farmer_name = ?, phone = ?, village = ?, district = ?
            WHERE farmer_id = ?
            RETURNING farmer_id, farmer_name, phone, village, district
            """;

        List<Map<String, Object>> rows = jdbc.queryForList(
            sql,
            farmer.farmerName(),
            farmer.phone(),
            farmer.village(),
            farmer.district(),
            id
        );

        if (rows.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Farmer not found"
            );
        }

        return rows.get(0);
    }

    // Delete a farmer
    @DeleteMapping("/{id}")
    public Map<String, String> deleteFarmer(@PathVariable int id) {

        try {
            int deleted = jdbc.update(
                "DELETE FROM farmers WHERE farmer_id = ?", id
            );
            if (deleted == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found");
            }
            return Map.of("message", "Farmer deleted successfully");
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Farmer has linked fields. Delete or reassign those fields first."
            );
        }
    }

    private void validate(FarmerRequest farmer) {
        if (farmer == null ||
            farmer.farmerName() == null || farmer.farmerName().isBlank() ||
            farmer.phone() == null || farmer.phone().isBlank() ||
            farmer.village() == null || farmer.village().isBlank() ||
            farmer.district() == null || farmer.district().isBlank()) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Farmer name, phone, village and district are required"
            );
        }
    }

    public record FarmerRequest(
        String farmerName,
        String phone,
        String village,
        String district
    ) {}
}
