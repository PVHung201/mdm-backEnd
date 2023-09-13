package com.mdm.equipmentservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(
        name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}, name = "username"),
        @UniqueConstraint(columnNames = {"phone"}, name = "phone"),
        @UniqueConstraint(columnNames = {"email"}, name = "email")
}
)
@NamedEntityGraph(
        name = "userFullInfo", subgraphs = @NamedSubgraph(name = "role-subgraph", attributeNodes = @NamedAttributeNode("permissions")),
        attributeNodes = {
                @NamedAttributeNode("department"),
                @NamedAttributeNode("departmentResponsibilities"),
                @NamedAttributeNode(value = "role", subgraph = "role-subgraph")
        }
)
@NamedEntityGraph(
        name = "userWithRole", subgraphs = @NamedSubgraph(name = "role-subgraph", attributeNodes = @NamedAttributeNode("permissions")),
        attributeNodes = {
                @NamedAttributeNode("department"),
                @NamedAttributeNode("departmentResponsibilities"),
                @NamedAttributeNode(value = "role", subgraph = "role-subgraph")
        }
)
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false) //true is male, false is female
    private Boolean gender;

    private String address;

    @Column(nullable = false)
    private LocalDateTime birthday;

    @Column(nullable = false)
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private WorkingStatus workingStatus;

    @JoinColumn(name = "role_id")
    @ManyToOne(targetEntity = Role.class)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "department"))
    private Department department;

    //phụ trách khoa phòng
    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Department> departmentResponsibilities;

    @Column(nullable = false)
    private String password;
}