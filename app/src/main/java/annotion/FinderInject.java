package annotion;

import android.app.Activity;

import com.explore.lin.processorlib.Finder;

/**
 * @author lin
 * @date 18/6/29
 * @license Copyright (c) 2016 那镁克
 */

public class FinderInject {

    public static void inject(Activity activity) {
        Class<?> cls = activity.getClass();
        String clsName = cls.getName();
        try {
            Class<?> finderClass = Class.forName(clsName + "_builder");
            Finder finder = (Finder) finderClass.newInstance();
            finder.inject(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
