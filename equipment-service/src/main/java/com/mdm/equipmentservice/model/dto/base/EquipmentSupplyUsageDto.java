package com.mdm.equipmentservice.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentSupplyUsageDto implements Serializable {

    private EquipmentDto equipment;

    private SupplyDto supply;

    private Long id;

    private String note;

    private Double amount;
}