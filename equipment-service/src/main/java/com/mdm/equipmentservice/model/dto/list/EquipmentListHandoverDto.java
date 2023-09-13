package com.mdm.equipmentservice.model.dto.list;

import com.mdm.equipmentservice.model.dto.base.DepartmentDto;
import com.mdm.equipmentservice.model.dto.base.EquipmentCategoryDto;
import com.mdm.equipmentservice.model.dto.fullinfo.HandoverTicketFullInfoDto;
import com.mdm.equipmentservice.model.entity.EquipmentStatus;
import com.mdm.equipmentservice.model.entity.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentListHandoverDto implements Serializable {

    private Long id;

    private String name;

    private String model;

    private String serial;

    private String code;

    private String hashCode;

    private RiskLevel riskLevel;

    private String technicalParameter;

    private LocalDateTime warehouseImportDate;

    private Year yearOfManufacture;

    private Year yearInUse;

    private String configuration;

    private Double importPrice;

    private Double initialValue;

    private Double annualDepreciation;

    private String usageProcedure;

    private String note;

    private EquipmentStatus status;

    private String manufacturer;

    private String manufacturingCountry;

    private EquipmentCategoryDto category;

    private DepartmentDto department;

    private Integer regularMaintenance;

    private Integer regularInspection;

    private LocalDateTime jointVentureContractExpirationDate;

    private LocalDateTime warrantyExpirationDate;

    private Set<HandoverTicketFullInfoDto> handoverTickets;

    private Boolean deleted = false;
}