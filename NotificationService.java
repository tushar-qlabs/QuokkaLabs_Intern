package dev.tushar.ecommerceapi.temp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationFactory notificationFactory;

    public void send(String type, String message) {
        Notification notification = notificationFactory.getNotification(type);
        notification.notifyUser(message);
    }
}
