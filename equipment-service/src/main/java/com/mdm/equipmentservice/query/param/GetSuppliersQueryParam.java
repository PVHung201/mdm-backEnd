package com.mdm.equipmentservice.query.param;

import lombok.Data;

@Data
public class GetSuppliersQueryParam {

    private String keyword;

    private Long serviceId;
}
