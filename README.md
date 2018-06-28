## 了解android annotation

### 概念
  
&emsp;&emsp;annotation可以理解为一种修饰符，用来将一些元数据与程序的类，方法，成员变量等进行关联。

### 原理

&emsp;&emsp;annotation其实是通过动态代理来实现的，其中的成员变量以map形式则存储在java常量池中具体可以参考这篇文章[https://blog.csdn.net/lylwo317/article/details/52163304](https://blog.csdn.net/lylwo317/article/details/52163304)

### 常见的 annotation

&emsp;&emsp;在android中常见的annotation有@Override @Deprecated @SuppressWarning等

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Override {
    }

&emsp;&emsp;在Override中，可以看到类的类型是@interface
##### @Target 也是java内置的annotation用于定义修饰的对象范围

+ `ElementType.CONSTRUCTOR:` 用于描述构造器
+ `ElementType.FIELD:` 用于描述域
+ `ElementType.LOCAL_VARIABLE:` 用于描述局部变量
+ `ElementType.METHOD:` 用于描述方法
+ `ElementType.PACKAGE:` 用于描述包
+ `ElementType.PARAMETER:` 用于描述参数
+ `ElementType.TYPE:` 用于描述类、接口(包括注解类型) 或enum声明

##### @Retention 用于定义如何存储注解

+ `RetentionPolicy.SOURCE :` 注解只保留在源码中，编译时会忽略
+ `RetentionPolicy.CLASS :` 更高一级，编译时被编译器保留，但是运行时会被 JVM 忽略
+ `RetentionPolicy.RUNTIME :` 最高级，运行时会被保留，可以被运行时访问

### 自定义注解

##### 自定义注解ContentView来代替setContentView

+ 新建 ContentView类

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.TYPE)
        public @interface ContentView {
            int value();
        }

+ 在activity中应用


        @ContentView(R.layout.activity_main)
        public class RuntimeAnnotionActivity extends AppCompatActivity {
        
            @BindView(R.id.classAnnotion)
            private Button btnGotoClassAnnotionActivity;
        
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                ContentViewInject.inject(RuntimeAnnotionActivity.this);
                BindViewInject.inject(RuntimeAnnotionActivity.this);
                btnGotoClassAnnotionActivity.setText("haha");
            }
    
        }

+ 如何关联


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

##### 学习了解动态代理
[链接](https://github.com/DaiHangLin/android-annotion/blob/master/dynamicProxy.md)
