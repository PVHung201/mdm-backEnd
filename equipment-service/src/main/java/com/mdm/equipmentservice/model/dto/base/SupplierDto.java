package com.mdm.equipmentservice.model.dto.base;

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
public class SupplierDto implements Serializable {

    private Long id;

    private String name;

    private String address;

    private String hotline;

    private String email;

    private String fax;

    private String website;

    private String taxCode;

    private String contactPersonName;

    private String contactPersonUsername;

    private String note;

    private Set<String> serviceNames;
}