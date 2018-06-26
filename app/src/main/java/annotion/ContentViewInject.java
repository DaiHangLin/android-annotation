package annotion;

import android.app.Activity;

/**
 * @author lin
 * @date 18/6/26
 * @license Copyright (c) 2016 那镁克
 */

public class ContentViewInject {
    private ContentViewInject() {

    }

    public static void inject(Activity activity) {
        // 找到使用 ContentView 注解的类
        ContentView contentView = activity.getClass().getAnnotation(ContentView.class);
        if (contentView != null) {
            try {
                activity.setContentView(contentView.value());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }
}
