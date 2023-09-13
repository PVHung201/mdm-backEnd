package com.mdm.equipmentservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(
        info =
        @Info(title = "Equipment API", version = "${springdoc.version}", description = "Documentation Equipment API v1.0")
)
public class EquipmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentServiceApplication.class, args);
    }
//    @Bean
//    CommandLineRunner runner(NotificationMongoRepository repository) {
//        return args -> {
//            Notification notification = new Notification();
//            notification.setNotificationType(NotificationType.LIQUIDATION);
//            notification.setContent("Test");
//            repository.save(notification);
//        };
//    }
}
