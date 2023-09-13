package com.mdm.equipmentservice.model.repository;

import com.mdm.equipmentservice.model.entity.Equipment;
import com.querydsl.core.types.Predicate;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends ParentRepository<Equipment, Long> {

    @Override
    @EntityGraph(value = "equipmentFullInfoDto")
    @NotNull
    Optional<Equipment> findById(@NotNull Long id);

    /*@EntityGraph(
            attributePaths = {
                    "category.group",
                    "department.manager",
                    "department.contactPerson",
                    "department.headOfDepartment",
                    "department.chiefNurse",
                    "project",
                    "supplier.contactPerson",
                    "unit",
                    "equipmentSupplyUsages.equipment",
                    "equipmentSupplyUsages.supply",
                    "inspectionTickets.creator",
                    "inspectionTickets.approver",
                    "inspectionTickets.equipment",
                    "inspectionTickets.inspectionCompany",
                    "maintenanceTickets.creator",
                    "maintenanceTickets.approver",
                    "maintenanceTickets.equipment",
                    "maintenanceTickets.maintenanceCompany",
                    "transferTickets.creator",
                    "transferTickets.approver",
                    "transferTickets.equipment",
                    "transferTickets.fromDepartment",
                    "transferTickets.toDepartment",
                    "handoverTickets.creator",
                    "handoverTickets.approver",
                    "handoverTickets.equipment",
                    "handoverTickets.department",
                    "handoverTickets.responsiblePerson",
                    "repairTickets.creator",
                    "repairTickets.approver",
                    "repairTickets.equipment",
                    "repairTickets.repairCompany",
                    "repairTickets.acceptanceTester",
                    "reportBrokenTickets.creator",
                    "reportBrokenTickets.approver",
                    "reportBrokenTickets.equipment",
                    "liquidationTickets.creator",
                    "liquidationTickets.approver",
                    "liquidationTickets.equipment"
            }
    )*/
//    @EntityGraph(value = "equipmentFullInfoDto")
    @NotNull
    Page<Equipment> findAll(@NotNull Predicate predicate, @NotNull Pageable pageable);
}