package com.agriculture.cropmanagement;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportsController {

    private final JdbcTemplate jdbc;

    public ReportsController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/api/reports/summary")
    public Map<String, Object> getReportSummary() {

        Map<String, Object> totals = jdbc.queryForMap("""
            SELECT
                (SELECT COALESCE(SUM(quantity_kg), 0)
                 FROM harvest_records) AS total_harvest_kg,

                (SELECT COALESCE(SUM(quantity_kg * selling_price_per_kg), 0)
                 FROM harvest_records) AS total_revenue,

                (SELECT COALESCE(SUM(other_cost), 0)
                 FROM harvest_records) AS harvest_other_cost,

                (SELECT COALESCE(SUM(cost), 0)
                 FROM fertilizer_inputs) AS fertilizer_cost,

                (SELECT COALESCE(SUM(cost), 0)
                 FROM irrigation_activities) AS irrigation_cost,

                (SELECT COALESCE(SUM(area_acres), 0)
                 FROM fields
                 WHERE field_id IN (
                     SELECT DISTINCT field_id FROM harvest_records
                 )) AS harvested_area_acres
            """);

        BigDecimal harvest = number(totals, "total_harvest_kg");
        BigDecimal revenue = number(totals, "total_revenue");
        BigDecimal fertilizer = number(totals, "fertilizer_cost");
        BigDecimal irrigation = number(totals, "irrigation_cost");
        BigDecimal harvestCost = number(totals, "harvest_other_cost");
        BigDecimal area = number(totals, "harvested_area_acres");

        BigDecimal totalCost = fertilizer.add(irrigation).add(harvestCost);
        BigDecimal profit = revenue.subtract(totalCost);

        BigDecimal productivity = area.compareTo(BigDecimal.ZERO) > 0
            ? harvest.divide(area, 2, java.math.RoundingMode.HALF_UP)
            : BigDecimal.ZERO;

        Map<String, Object> report = new LinkedHashMap<>();
        report.put("totalHarvestKg", harvest);
        report.put("harvestedAreaAcres", area);
        report.put("productivityKgPerAcre", productivity);
        report.put("fertilizerCost", fertilizer);
        report.put("irrigationCost", irrigation);
        report.put("harvestOtherCost", harvestCost);
        report.put("totalCost", totalCost);
        report.put("totalRevenue", revenue);
        report.put("profit", profit);

        return report;
    }

    private BigDecimal number(Map<String, Object> values, String key) {
        Object value = values.get(key);
        return value instanceof BigDecimal decimal
            ? decimal : new BigDecimal(value.toString());
    }
}