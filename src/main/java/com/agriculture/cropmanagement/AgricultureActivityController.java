package com.agriculture.cropmanagement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AgricultureActivityController {

    private final JdbcTemplate jdbc;

    public AgricultureActivityController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // CULTIVATION ACTIVITIES

    @GetMapping("/api/cultivation")
    public List<Map<String, Object>> getCultivation() {
        return jdbc.queryForList("""
            SELECT * FROM cultivation_activities
            ORDER BY activity_date DESC
            """);
    }

    @PostMapping("/api/cultivation")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addCultivation(@RequestBody CultivationRequest r) {
        if (r == null || r.fieldId() == null || r.cropId() == null ||
            blank(r.activityName()) || r.activityDate() == null) {
            throw badRequest();
        }

        return jdbc.queryForMap("""
            INSERT INTO cultivation_activities
            (field_id, crop_id, activity_name, activity_date, description)
            VALUES (?, ?, ?, ?, ?)
            RETURNING *
            """, r.fieldId(), r.cropId(), r.activityName().trim(),
            r.activityDate(), r.description());
    }

    @PutMapping("/api/cultivation/{id}")
    public Map<String, Object> updateCultivation(
        @PathVariable int id, @RequestBody CultivationRequest r) {

        if (r == null || r.fieldId() == null || r.cropId() == null ||
            blank(r.activityName()) || r.activityDate() == null) {
            throw badRequest();
        }

        return update("""
            UPDATE cultivation_activities
            SET field_id=?, crop_id=?, activity_name=?, activity_date=?, description=?
            WHERE activity_id=?
            RETURNING *
            """, r.fieldId(), r.cropId(), r.activityName().trim(),
            r.activityDate(), r.description(), id);
    }

    @DeleteMapping("/api/cultivation/{id}")
    public Map<String, String> deleteCultivation(@PathVariable int id) {
        return delete("cultivation_activities", "activity_id", id);
    }

    // FERTILIZER AND INPUT TRACKING

    @GetMapping("/api/fertilizers")
    public List<Map<String, Object>> getFertilizers() {
        return jdbc.queryForList("""
            SELECT * FROM fertilizer_inputs ORDER BY input_date DESC
            """);
    }

    @PostMapping("/api/fertilizers")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addFertilizer(@RequestBody FertilizerRequest r) {
        validateFertilizer(r);

        return jdbc.queryForMap("""
            INSERT INTO fertilizer_inputs
            (field_id, crop_id, input_name, quantity, unit, cost, input_date)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            RETURNING *
            """, r.fieldId(), r.cropId(), r.inputName().trim(),
            r.quantity(), r.unit(), amount(r.cost()), r.inputDate());
    }

    @PutMapping("/api/fertilizers/{id}")
    public Map<String, Object> updateFertilizer(
        @PathVariable int id, @RequestBody FertilizerRequest r) {

        validateFertilizer(r);

        return update("""
            UPDATE fertilizer_inputs
            SET field_id=?, crop_id=?, input_name=?, quantity=?, unit=?, cost=?, input_date=?
            WHERE input_id=?
            RETURNING *
            """, r.fieldId(), r.cropId(), r.inputName().trim(),
            r.quantity(), r.unit(), amount(r.cost()), r.inputDate(), id);
    }

    @DeleteMapping("/api/fertilizers/{id}")
    public Map<String, String> deleteFertilizer(@PathVariable int id) {
        return delete("fertilizer_inputs", "input_id", id);
    }

    // IRRIGATION ACTIVITIES

    @GetMapping("/api/irrigation")
    public List<Map<String, Object>> getIrrigation() {
        return jdbc.queryForList("""
            SELECT * FROM irrigation_activities ORDER BY irrigation_date DESC
            """);
    }

    @PostMapping("/api/irrigation")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addIrrigation(@RequestBody IrrigationRequest r) {
        validateIrrigation(r);

        return jdbc.queryForMap("""
            INSERT INTO irrigation_activities
            (field_id, irrigation_date, water_quantity, duration_minutes, cost, notes)
            VALUES (?, ?, ?, ?, ?, ?)
            RETURNING *
            """, r.fieldId(), r.irrigationDate(), r.waterQuantity(),
            r.durationMinutes(), amount(r.cost()), r.notes());
    }

    @PutMapping("/api/irrigation/{id}")
    public Map<String, Object> updateIrrigation(
        @PathVariable int id, @RequestBody IrrigationRequest r) {

        validateIrrigation(r);

        return update("""
            UPDATE irrigation_activities
            SET field_id=?, irrigation_date=?, water_quantity=?,
                duration_minutes=?, cost=?, notes=?
            WHERE irrigation_id=?
            RETURNING *
            """, r.fieldId(), r.irrigationDate(), r.waterQuantity(),
            r.durationMinutes(), amount(r.cost()), r.notes(), id);
    }

    @DeleteMapping("/api/irrigation/{id}")
    public Map<String, String> deleteIrrigation(@PathVariable int id) {
        return delete("irrigation_activities", "irrigation_id", id);
    }

    // HARVEST RECORDS

    @GetMapping("/api/harvests")
    public List<Map<String, Object>> getHarvests() {
        return jdbc.queryForList("""
            SELECT * FROM harvest_records ORDER BY harvest_date DESC
            """);
    }

    @PostMapping("/api/harvests")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addHarvest(@RequestBody HarvestRequest r) {
        validateHarvest(r);

        return jdbc.queryForMap("""
            INSERT INTO harvest_records
            (field_id, crop_id, season_id, harvest_date,
             quantity_kg, selling_price_per_kg, other_cost)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            RETURNING *
            """, r.fieldId(), r.cropId(), r.seasonId(), r.harvestDate(),
            r.quantityKg(), amount(r.sellingPricePerKg()), amount(r.otherCost()));
    }

    @PutMapping("/api/harvests/{id}")
    public Map<String, Object> updateHarvest(
        @PathVariable int id, @RequestBody HarvestRequest r) {

        validateHarvest(r);

        return update("""
            UPDATE harvest_records
            SET field_id=?, crop_id=?, season_id=?, harvest_date=?,
                quantity_kg=?, selling_price_per_kg=?, other_cost=?
            WHERE harvest_id=?
            RETURNING *
            """, r.fieldId(), r.cropId(), r.seasonId(), r.harvestDate(),
            r.quantityKg(), amount(r.sellingPricePerKg()),
            amount(r.otherCost()), id);
    }

    @DeleteMapping("/api/harvests/{id}")
    public Map<String, String> deleteHarvest(@PathVariable int id) {
        return delete("harvest_records", "harvest_id", id);
    }

    // COMMON HELPERS

    private Map<String, Object> update(String sql, Object... args) {
        List<Map<String, Object>> rows = jdbc.queryForList(sql, args);
        if (rows.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found");
        }
        return rows.get(0);
    }

    private Map<String, String> delete(String table, String idColumn, int id) {
        int count = jdbc.update(
            "DELETE FROM " + table + " WHERE " + idColumn + " = ?", id
        );
        if (count == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found");
        }
        return Map.of("message", "Record deleted successfully");
    }

    private boolean blank(String value) {
        return value == null || value.isBlank();
    }

    private BigDecimal amount(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    private ResponseStatusException badRequest() {
        return new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "Please enter all required details correctly"
        );
    }

    private void validateFertilizer(FertilizerRequest r) {
        if (r == null || r.fieldId() == null || r.cropId() == null ||
            blank(r.inputName()) || r.quantity() == null ||
            r.quantity().compareTo(BigDecimal.ZERO) < 0 || r.inputDate() == null ||
            amount(r.cost()).compareTo(BigDecimal.ZERO) < 0) {
            throw badRequest();
        }
    }

    private void validateIrrigation(IrrigationRequest r) {
        if (r == null || r.fieldId() == null || r.irrigationDate() == null ||
            amount(r.cost()).compareTo(BigDecimal.ZERO) < 0) {
            throw badRequest();
        }
    }

    private void validateHarvest(HarvestRequest r) {
        if (r == null || r.fieldId() == null || r.cropId() == null ||
            r.harvestDate() == null || r.quantityKg() == null ||
            r.quantityKg().compareTo(BigDecimal.ZERO) < 0 ||
            amount(r.sellingPricePerKg()).compareTo(BigDecimal.ZERO) < 0 ||
            amount(r.otherCost()).compareTo(BigDecimal.ZERO) < 0) {
            throw badRequest();
        }
    }

    public record CultivationRequest(
        Integer fieldId, Integer cropId, String activityName,
        LocalDate activityDate, String description
    ) {}

    public record FertilizerRequest(
        Integer fieldId, Integer cropId, String inputName,
        BigDecimal quantity, String unit, BigDecimal cost, LocalDate inputDate
    ) {}

    public record IrrigationRequest(
        Integer fieldId, LocalDate irrigationDate, BigDecimal waterQuantity,
        Integer durationMinutes, BigDecimal cost, String notes
    ) {}

    public record HarvestRequest(
        Integer fieldId, Integer cropId, Integer seasonId,
        LocalDate harvestDate, BigDecimal quantityKg,
        BigDecimal sellingPricePerKg, BigDecimal otherCost
    ) {}
}
