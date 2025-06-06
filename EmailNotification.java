package dev.tushar.ecommerceapi.temp;

import org.springframework.stereotype.Component;

@Component("EMAIL")
public class EmailNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending Email Notification: " + message);
    }
}
