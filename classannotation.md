## 编译期注解

##### 什么是编译期注解
当注解被标注为`@Retention(RetentionPolicy.CLASS)`时，代表此注解为编辑器注解，jvm会将其打包编译到.class文件中

##### 使用处理编译期注解

+ 需要实现一个继承`AbstractProcessor `的类，因为`AbstractProcessor`在java包中，因此需要一个module为java library
+ 需要使用`auto-service`注解Processor,自动生成`META-INF`,主要为了``AbstractProcessor能被jvm识别加载
+ 使用编译期注解最后会生成一个java文件，而这个java文件里自动生成了一堆findviewbyid的代码，所以需要`javapoet`库来生成java文件

##### 简单demo参照([https://joyrun.github.io/2016/07/19/AptHelloWorld/](https://joyrun.github.io/2016/07/19/AptHelloWorld/))
