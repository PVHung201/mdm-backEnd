package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotBlank;
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
public class UpsertSupplierForm implements Serializable {

    @NotBlank(message = "{supplierNameMustNotBeBlank}")
    private String name;

    private String address;

    @NotBlank(message = "{supplierHotlineMustNotBeBlank}")
    private String hotline;

    @NotBlank(message = "{supplierEmailMustNotBeBlank}")
    private String email;

    private String fax;

    private String website;

    private String taxCode;

    private Long contactPersonId;

    private String note;

    private Set<Long> serviceIds;
}