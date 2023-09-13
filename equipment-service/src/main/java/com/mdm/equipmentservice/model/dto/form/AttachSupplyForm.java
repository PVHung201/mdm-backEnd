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
public class AttachSupplyForm implements Serializable {

    private Long supplyId;

    private Double amount;

    private String note;

    private Long equipmentId;

    private Double amountUsed;


}
