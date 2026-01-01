package com.workflex.repositories.specifications;

import com.workflex.domain.models.Workation;
import org.springframework.data.jpa.domain.Specification;

public final class WorkationSpecifications {

    private WorkationSpecifications() {
        // utility class
    }

    /** w.deletedAt IS NULL */
    public static Specification<Workation> notDeleted() {
        return (root, query, cb) ->
                cb.isNull(root.get("deletedAt"));
    }

    /** LOWER(w.employee) LIKE %employee% */
    public static Specification<Workation> employeeContains(String employee) {
        if (employee == null || employee.isBlank()) {
            return null;
        }

        return (root, query, cb) ->
                cb.like(
                        cb.lower(root.get("employee")),
                        "%" + employee.toLowerCase() + "%"
                );
    }

    /** w.originCountry = originCountry */
    public static Specification<Workation> originCountryEquals(String originCountry) {
        if (originCountry == null || originCountry.isBlank()) {
            return null;
        }

        return (root, query, cb) ->
                cb.equal(root.get("originCountry"), originCountry);
    }
}
