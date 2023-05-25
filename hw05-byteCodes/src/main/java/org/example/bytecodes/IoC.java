package org.example.bytecodes;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IoC {

    private IoC() {
    }

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLoggingInterfaceImpl());
        return (TestLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private final List<String> list = new ArrayList<>();

        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            addLogAnnotation();
        }

        private void addLogAnnotation() {
            Method[] methods = myClass.getClass().getMethods();
            for (var method: methods) {
                if (method.isAnnotationPresent(Log.class)) {
                    list.add(method.getName() + Arrays.toString(method.getParameterTypes()));
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (list.contains(method.getName() + Arrays.toString(method.getParameterTypes()))) {
                System.out.println("executed method:" + method.getName() + ", param: " + Arrays.toString(args));
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
