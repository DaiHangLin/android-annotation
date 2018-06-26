package annotion;

import android.app.Activity;
import android.widget.Button;

import java.lang.reflect.Field;

/**
 * @author lin
 * @date 18/6/26
 * @license Copyright (c) 2016 那镁克
 */

public class BindViewInject {

    private BindViewInject() {

    }

    public static void inject(Activity activity) {
        Class c = activity.getClass();
        Field[] fields = c.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (field.getAnnotations() != null && field.isAnnotationPresent(BindView.class)) {
                    BindView bindView = field.getAnnotation(BindView.class);
                    // 允许修改反射属性
                    field.setAccessible(true);
                    // findViewById将注解的id，找到View注入成员变量中
                    try {
                        field.set(activity, activity.findViewById(bindView.value()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
