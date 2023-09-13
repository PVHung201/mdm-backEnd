package com.mdm.equipmentservice.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto implements Serializable {

    private Long id;

    private LocalDateTime inventoryDate;

    private String note;

    private String inventoryPersonName;

    private String inventoryPersonUsername;

    private Integer time;
}