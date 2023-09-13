package com.mdm.equipmentservice.model.dto.fullinfo;

import com.mdm.equipmentservice.model.dto.base.EquipmentCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentGroupFullInfoDto implements Serializable {

    private Long id;

    private String name;

    private String note;

    private String alias;

    private Set<EquipmentCategoryDto> equipmentCategories;
}