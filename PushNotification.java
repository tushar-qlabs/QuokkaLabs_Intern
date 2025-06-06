package dev.tushar.ecommerceapi.temp;

import org.springframework.stereotype.Component;

@Component("PUSH")
public class PushNotification implements Notification{
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending Push Notification: " + message);
    }
}
