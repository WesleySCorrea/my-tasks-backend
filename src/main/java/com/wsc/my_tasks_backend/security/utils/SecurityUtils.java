package com.wsc.my_tasks_backend.security.utils;

import com.wsc.auth.lib.model.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserInfo getCurrentUser() {
        return (UserInfo) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
