package com.course.server.util;

import com.course.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

/*
 * @ProjectName: course
 * @Package: com.course.server.util
 * @ClassName: ValidatorUtil
 * @Author: 游佳琪
 * @Description: 校验工具类
 * @Date: 2020-10-30 17:25
 * @Version: 1.0
 */
public class ValidatorUtil {

    /*
     *  空校验
     * @Author: YJQ
     * @Date: 2020-10-30 17:26
     */

    public static void require(Object str, String fieldName) {
        if (StringUtils.isEmpty(str)) {
            throw new ValidatorException(fieldName + "不能为空");
        }
    }
    /*
     *  长度校验
     * @Author: YJQ
     * @Date: 2020-10-30 17:29
     */

    public static void length(String str, String fieldName, int min, int max) {
        int length = 0;
        if (StringUtils.isEmpty(str)) {
            return;
        }
        if (!StringUtils.isEmpty(str)){
            length = str.length();
        }
        if (length < min || length > max) {
            throw new ValidatorException(fieldName + "长度" + min + "~" + max + "位");
        }
    }
}
