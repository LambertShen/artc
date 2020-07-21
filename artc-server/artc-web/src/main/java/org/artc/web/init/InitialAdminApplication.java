package org.artc.web.init;

import org.artc.core.entity.User;
import org.artc.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class InitialAdminApplication implements ApplicationRunner {

    private final String LOGIN_NAME = "admin";
    private final String PASSWORD = "admin";
    private final String FAMILY_NAME = "Artc";
    private final String GIVEN_NAME = "Admin";
    private final String NICK_NAME = "Artist";
    private final Integer SEX = 0;
    private final String ID_CARD = "888888888888888888";
    private final String MOBILE = "88888888888";
    private final String EMAIL = "Lambert.Shen@outlook.com";
    private final Integer ADMIN = 0;

    @Autowired
    private UserService userService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userService.findUserByLoginName(LOGIN_NAME) != null) return;
        User user = new User();
        user.setLoginName(LOGIN_NAME);
        user.setPassword(PASSWORD);
        user.setFamilyName(FAMILY_NAME);
        user.setGivenName(GIVEN_NAME);
        user.setNickName(NICK_NAME);
        user.setSex(SEX);
        user.setIdCard(ID_CARD);
        user.setMobile(MOBILE);
        user.setEmail(EMAIL);
        user.setAdmin(ADMIN);
        userService.insert(user);
    }
}
