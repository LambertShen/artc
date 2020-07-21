package org.artc.core.utils;

import org.apache.shiro.SecurityUtils;
import org.artc.core.entity.User;

public class UserUtils {

    public static User getCurrentUser() {
        return (User)SecurityUtils.getSubject().getPrincipal();
    }

    public static String getCurrentUserId() {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        return user.getId();
    }
}
