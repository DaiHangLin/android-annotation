## 什么是动态代理

##### 首先什么代理模式

>[wiki](https://zh.wikipedia.org/wiki/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F)
>代理模式（英语：Proxy Pattern）是程序设计中的一种设计模式。
>
>所谓的代理者是指一个类别可以作为其它东西的接口。代理者可以作任何东西的接口：网络连接、存储器中的大对象、文件或其它昂贵或无法复制的资源

##### 代理模式UML图
![](https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Proxy_pattern_diagram.svg/439px-Proxy_pattern_diagram.svg.png)

##### 代理模式实现
[参考链接https://web.archive.org/web/20150226062232/http://userpages.umbc.edu/~tarr/dp/lectures/DynProxies-2pp.pdf](https://web.archive.org/web/20150226062232/http://userpages.umbc.edu/~tarr/dp/lectures/DynProxies-2pp.pdf)

    // 接口
    public interface IVehicle {
        void start();
        void forward();
        void reverse();
        void stop();
    }
    
    // 具体实现类
    public class Car implements IVehicle {
        @Override
        public void start() {
            System.out.println("start");
        }
    
        @Override
        public void forward() {
            System.out.println("forward");
        }
    
        @Override
        public void reverse() {
            System.out.println("reverse");
        }
    
        @Override
        public void stop() {
            System.out.println("stop");
        }
    }
    
    // 代理实现类
    public class VehicleProxy implements IVehicle {
        private IVehicle v;
    
        public VehicleProxy(IVehicle v) {
            this.v = v;
        }
    
        @Override
        public void start() {
            System.out.println("VehicleProxy: Begin of start()");
            v.start();
            System.out.println("VehicleProxy: End of start()");
        }
    
        @Override
        public void forward() {
            System.out.println("VehicleProxy: Begin of forward()");
            v.forward();
            System.out.println("VehicleProxy: End of forward()");
        }
    
        @Override
        public void reverse() {
            System.out.println("VehicleProxy: Begin of reverse()");
            v.reverse();
            System.out.println("VehicleProxy: End of reverse()");
        }
    
        @Override
        public void stop() {
            System.out.println("VehicleProxy: Begin of stop()");
            v.stop();
            System.out.println("VehicleProxy: End of stop()");
        }
    }
    
    // example
    public static void main(String[] args) {
         IVehicle v = new Car();
         IVehicle p = new VehicleProxy(v);
         p.start();
         p.forward();
         p.reverse();
         p.stop();
    }
    
    // 输出
    I/System.out: VehicleProxy: Begin of start()
    I/System.out: start
    I/System.out: VehicleProxy: End of start()
    I/System.out: VehicleProxy: Begin of forward()
    I/System.out: forward
    I/System.out: VehicleProxy: End of forward()
    ...
![](https://images2018.cnblogs.com/blog/596306/201806/596306-20180628132910410-752345194.png)

##### 使用动态代理如何实现


     // 接口
        public interface IVehicle {
            void start();
            void forward();
            void reverse();
            void stop();
        }
    
    // 具体实现类
    public class Car implements IVehicle {
        @Override
        public void start() {
            System.out.println("start");
        }
    
        @Override
        public void forward() {
            System.out.println("forward");
        }
    
        @Override
        public void reverse() {
            System.out.println("reverse");
        }
    
        @Override
        public void stop() {
            System.out.println("stop");
        }
    }
    
    // 动态代理实现类
    public class VehicleHandler implements InvocationHandler {

        private IVehicle v;
    
        public VehicleHandler(IVehicle v) {
            this.v = v;
        }
    
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Vehicle Handler: Invoking " + method.getName());
            return method.invoke(v, args);
        }
    }
    
    // example
    public static void main(String[] args) {
        IVehicle v = new Car();
        ClassLoader cl = IVehicle.class.getClassLoader();
        IVehicle dp = (IVehicle) Proxy.newProxyInstance(cl, new Class[]{IVehicle.class}, new VehicleHandler(v));
        dp.start();
        dp.forward();
        dp.reverse();
        dp.stop();
    }
    
    // 输出
    I/System.out: Vehicle Handler: Invoking start
    I/System.out: start
    I/System.out: Vehicle Handler: Invoking forward
    I/System.out: forward
    ...
    
![](https://images2018.cnblogs.com/blog/596306/201806/596306-20180628133007498-2015794119.png)
    
##### 为什么使用动态管理
&emsp;&emsp;当你的proxy需要多个时，使用动态代理就不需要新建更多的类，可以简化代码
