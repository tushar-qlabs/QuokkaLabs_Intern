package dev.tushar.practice.temp;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@AllArgsConstructor
@Component
public class NotificationFactory {

    private final Map<String, Notification> notificationMap;

    public Notification getNotification(String notificationType) {
        Notification notification = notificationMap.get(notificationType);
        if (notification == null) {
            throw new IllegalArgumentException("Invalid notification type: " + notificationType);
        }
        return notification;
    }
}
