package org.artc.core.utils;

import org.apache.shiro.SecurityUtils;
import org.artc.core.entity.User;

public class UserUtils {

    public static User getCurrentUser() {
        try {
            return (User) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            return new User();
        }
    }

    public static String getCurrentUserId() {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            return user.getId();
        } catch (Exception e) {
            return "system";
        }
    }
}
