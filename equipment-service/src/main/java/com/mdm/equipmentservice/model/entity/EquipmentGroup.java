package com.mdm.equipmentservice.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@RequiredArgsConstructor
@Setter
@Table(name = "equipment_groups")
@Getter
public class EquipmentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String note;

    private String alias;

    @OneToMany(mappedBy = "group", targetEntity = EquipmentCategory.class)
    private Set<EquipmentCategory> equipmentCategories;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "note = " + note + ", " +
                "alias = " + alias + ")";
    }
}
