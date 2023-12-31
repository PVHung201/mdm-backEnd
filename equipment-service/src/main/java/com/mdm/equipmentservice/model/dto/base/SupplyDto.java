package com.mdm.equipmentservice.model.dto.base;

import com.mdm.equipmentservice.model.entity.RiskLevel;
import com.mdm.equipmentservice.model.entity.SupplyStatus;
import com.mdm.equipmentservice.model.entity.SupplyUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SupplyDto implements Serializable {

    private final Long id;

    private final String name;

    private final Double amountUsed;

    private final Double amount;

    private final Date warehouseImportDate;

    private final int yearOfManufacture;

    private final SupplyUnitDto unit;

    private final SupplyStatus status;

    private final String model;

    private final String serial;

    private final String code;

    private final String hashCode;

    private final int yearInUse;

    private final Double importPrice;

    private final RiskLevel riskLevel;

    private final String manufacturer;

    private final String manufacturingCountry;

    private final Date expiryDate;

    private final String technicalParameter;

    private final String configuration;

    private final String usageProcedure;

    private final String note;

    /**
     * A DTO for the {@link SupplyUnit} entity
     */
    @Getter
    @Setter
    @AllArgsConstructor
    public static class SupplyUnitDto implements Serializable {

        private final String name;
    }
}