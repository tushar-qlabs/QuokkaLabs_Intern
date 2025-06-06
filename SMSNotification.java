package dev.tushar.practice.temp;

import org.springframework.stereotype.Component;

@Component("SMS")
public class SMSNotification implements Notification{
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending SMS Notification: " + message);
    }
}
