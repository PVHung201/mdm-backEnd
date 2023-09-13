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
public class EquipmentCategoryDto implements Serializable {

    private Long id;

    private String name;

    private String note;

    private String alias;

    private EquipmentGroupDto group;
}