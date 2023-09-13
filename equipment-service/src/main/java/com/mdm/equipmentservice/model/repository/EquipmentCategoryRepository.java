package com.mdm.equipmentservice.model.repository;

import com.mdm.equipmentservice.model.entity.EquipmentCategory;
import com.querydsl.core.types.Predicate;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentCategoryRepository extends ParentRepository<EquipmentCategory, Long> {

    @Override
    @EntityGraph(value = "fullInfo")
    @NotNull
    Page<EquipmentCategory> findAll(@NotNull Predicate predicate, @NotNull Pageable pageable);
}