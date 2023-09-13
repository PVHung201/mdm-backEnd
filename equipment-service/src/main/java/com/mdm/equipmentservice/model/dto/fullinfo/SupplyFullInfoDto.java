package com.mdm.equipmentservice.model.dto.fullinfo;

import com.mdm.equipmentservice.model.dto.base.*;
import com.mdm.equipmentservice.model.entity.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplyFullInfoDto implements Serializable {

    private Long id;

    private String name;

    private String model;

    private String serial;

    private String hashCode;

    private Double amountUsed;

    private Double amount;

    private Date warehouseImportDate;

    private Integer yearOfManufacture;

    private Integer yearInUse;

    private Double importPrice;

    private Set<EquipmentSupplyUsageDto> equipmentSupplyUsages;

    private ProjectDto project;

    private SupplyUnitDto unit;

    private SupplyCategoryDto category;

    private RiskLevel riskLevel;

    private String manufacturer;

    private String manufacturingCountry;

    private SupplierDto supplier;

    private Date expiryDate;

    private String technicalParameter;

    private String configuration;

    private String usageProcedure;

    private String note;
}