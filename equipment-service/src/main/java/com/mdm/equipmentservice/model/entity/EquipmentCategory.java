package com.mdm.equipmentservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity

@RequiredArgsConstructor
@Setter
@Table(name = "equipment_categories")
@Getter
@NamedEntityGraph(
        name = "fullInfo", attributeNodes = {
        @NamedAttributeNode("group")
}
)
public class EquipmentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String note;

    private String alias;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private EquipmentGroup group;

}
