package com.mdm.equipmentservice.query.param;

import com.mdm.equipmentservice.model.entity.FileDescription;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFileStoragesQueryParam {

    private Long associatedEntityId;

    private List<Long> listOfAssociatedEntityId;


    private String associatedEntityType;

    private FileDescription fileDescription;

}
