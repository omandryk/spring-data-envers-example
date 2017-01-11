package com.icoderman.app;

import com.icoderman.app.config.SpringConfig;
import com.icoderman.app.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Oleksandr Mandryk
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.createNewUser();
        userService.updateUserFields();
        userService.updateUserAddress();

        userService.showAllUserRevisions();

        userService.restoreLastRevision();

        userService.showAllUserRevisions();
    }
}
