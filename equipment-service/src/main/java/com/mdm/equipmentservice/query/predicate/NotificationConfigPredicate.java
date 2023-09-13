package com.mdm.equipmentservice.query.predicate;

import com.mdm.equipmentservice.model.entity.QNotificationConfig;

public class NotificationConfigPredicate {

    private static final QNotificationConfig notificationConfig = QNotificationConfig.notificationConfig;

   /* public static Predicate getNotificationConfigs(GetNotificationConfigsQueryParam getNotificationConfigsQueryParam) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(matchRole(getNotificationConfigsQueryParam.getRoleName()))
                .and(matchNotificationType(getNotificationConfigsQueryParam.getNotificationType()));
        return booleanBuilder;
    }

    public static BooleanExpression matchRole(String role) {
        return StringUtils.isNotBlank(role) ? notificationConfig.role.id.eq(role) : null;
    }

    public static BooleanExpression matchNotificationType(NotificationType notificationType) {
        return notificationType != null ? notificationConfig.notificationType.eq(notificationType) : null;
    }*/
}
