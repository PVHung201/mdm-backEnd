//package com.mdm.equipmentservice.model.mongo;
//
//import com.mdm.equipmentservice.model.entity.Notification;
//import jakarta.validation.constraints.NotNull;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface NotificationMongoRepository extends MongoRepository<Notification, Long>, QuerydslPredicateExecutor<Notification> {
//
//    @NotNull Page<Notification> findAll(@NotNull Pageable pageable);

    //void deleteById(String id);
//    public void deleteAllById(List<String> ids);
//    public Page<Notification> findByNotificationTypeAndEquipmentId(NotificationType type, Long equipmentId, Pageable pageable);
//}
