package com.mdm.equipmentservice.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class SupplyUnitDto implements Serializable {

    private final Long id;

    private final String name;
}