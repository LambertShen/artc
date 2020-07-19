package test;

import org.artc.security.entity.User;
import org.artc.security.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void insert() {
        User user = new User();
        user.setLoginName("admin");
        user.setEmail("2357111357@outlook.com");
        user.setFamilyName("Shen");
        user.setGiveName("Lambert");
        user.setSex(0);
        user.setHeadImgUrl("");
        user.setIdCard("232700199809166317");
        user.setNickName("Hacker");
        user.setMobile("13634339865");
        user.setPassword("963963");
        userService.insert(user);
    }

}