## 优化编译期注册findviewbyid的行为

#### 优化前注册的行为

需要动态的注册通过注解生成的类比如

```java
BindViewFor_ClassAnnotationActivity.bind(this)
```
> 造成每个activity都需要对应的不同的注册代码，使用时需要了解到如何注册的，

#### 优化后
针对每一个activity，或者其他的view都应该有一个统一的入口，对于外部是可见的，并且不需要关心内部的实现，比如

```java
FinderInject.inject(this);
```

#### 实现

```java
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
```

#### 思路

+ 通过Class.forName找到需要的Class

```java
/* @param      className   the fully qualified name of the desired class.
     */
    @CallerSensitive
    public static Class<?> forName(String className)
                throws ClassNotFoundException {
        return forName(className, true, VMStack.getCallingClassLoader());
    }
```

+ forName 需要类完整的限定名称，所以限定符最好加在最后，可以省去解析过程
