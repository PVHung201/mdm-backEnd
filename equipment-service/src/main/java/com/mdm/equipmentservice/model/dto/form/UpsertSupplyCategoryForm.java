package com.mdm.equipmentservice.model.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertSupplyCategoryForm implements Serializable {

    private Long id;

    private String name;

    private String alias;

    private String note;
}