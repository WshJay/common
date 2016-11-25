package org.wsh.common.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.wsh.common.model.basic.UserBasicDO;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  用户对象校验
 * since Date： 2016-10-24 11:18
 */
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return UserBasicDO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        // 判空
        ValidationUtils.rejectIfEmpty(errors, "userName", null, "Username is empty.");
        ValidationUtils.rejectIfEmpty(errors, "password", null, "Password is empty.");
        UserBasicDO user = (UserBasicDO) obj;
        if (user.getUserName().length() < 3)
            errors.rejectValue("userName", null, "userName is min 3!");
        else if (user.getUserName().length() < 8)
            errors.rejectValue("userName", null, "userName is max 8!");
    }
}
