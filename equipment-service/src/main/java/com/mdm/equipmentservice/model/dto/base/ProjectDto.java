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
public class ProjectDto implements Serializable {

    private Long id;

    private String name;

    private String fundingSource;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}