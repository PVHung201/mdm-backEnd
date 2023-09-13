package com.mdm.equipmentservice.model.dto.base;

import com.mdm.equipmentservice.model.entity.EquipmentStatus;
import com.mdm.equipmentservice.model.entity.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDashboardDto {

    private Set<CountByDepartment> countByDepartment;

    private Set<CountByRiskLevel> countByRiskLevels;

    private Set<CountByEquipmentStatus> countByEquipmentStatuses;

    private Set<CountByDepartment> countRepairingByDepartment;

    private Set<CountByDepartment> countBrokenByDepartment;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CountByDepartment {

        private Long departmentId;

        private String departmentName;

        private Long count;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class CountByRiskLevel {

        private RiskLevel riskLevel;

        private Long count;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class CountByEquipmentStatus {

        private EquipmentStatus status;

        private Long count;
    }
}
