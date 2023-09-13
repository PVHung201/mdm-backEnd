package com.mdm.equipmentservice.model.repository;

import com.mdm.equipmentservice.model.entity.Supply;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyRepository extends ParentRepository<Supply, Long> {

}
