package dev.tushar.practice;

import dev.tushar.practice.temp.Notification;
import dev.tushar.practice.temp.NotificationFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//Factory method with DI in Spring...

@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(PracticeApplication.class, args);
        NotificationFactory factory = context.getBean(NotificationFactory.class);

        Notification notification = factory.getNotification("EMAIL");
        notification.notifyUser("Please verify the OTP.");

        Notification smsNotification = factory.getNotification("SMS");
        smsNotification.notifyUser("Please recharge your account.");

        Notification pushNotification = factory.getNotification("PUSH");
        pushNotification.notifyUser("Please update your profile.");
    }

}
