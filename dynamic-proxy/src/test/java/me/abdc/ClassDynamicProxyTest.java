package me.abdc;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.jupiter.api.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ClassDynamicProxyTest {

    @Test
    void cglib() {
        MethodInterceptor handler = new MethodInterceptor() {
            DefaultBookService defaultBookService = new DefaultBookService();
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("before");
                    Object invoke = method.invoke(defaultBookService, args);
                    System.out.println("after");
                    return invoke;
                }

                return method.invoke(defaultBookService, args);
            }
        };

        Book book = new Book();
        book.setTitle("Proxy");

        DefaultBookService defaultBookService = (DefaultBookService) Enhancer.create(DefaultBookService.class, handler);
        System.out.println(defaultBookService.getClass());
        defaultBookService.rent(book);
    }

    @Test
    void bytebuddy() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends DefaultBookService> proxyClass= new ByteBuddy()
                .subclass(DefaultBookService.class)
                .method(named("rent"))
                .intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    DefaultBookService defaultBookService = new DefaultBookService();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        DefaultBookService defaultBookService = new DefaultBookService();
                        System.out.println("before");
                        Object invoke = method.invoke(defaultBookService, args);
                        System.out.println("after");
                        return invoke;
                    }
                }))
                .make()
                .load(DefaultBookService.class.getClassLoader())
                .getLoaded();

        Book book = new Book();
        book.setTitle("Proxy");

        DefaultBookService defaultBookService = proxyClass.getConstructor(null).newInstance();
        System.out.println(defaultBookService.getClass());
        defaultBookService.rent(book);
    }
}
