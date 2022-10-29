package me.abdc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<MyClass> myClassClass = MyClass.class;
        MyClass myClass = new MyClass();

        System.out.println("--------------- MyClass Superclass ---------------");
        System.out.println(myClassClass.getSuperclass());
        System.out.println();


        System.out.println("--------------- MyClass Interfaces ---------------");
        Arrays.stream(myClassClass.getInterfaces()).forEach(System.out::println);
        System.out.println();


        System.out.println("--------------- MyClass DeclaredFields ---------------");
        Arrays.stream(myClassClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.printf("%s ==> %s\n", f, f.get(myClass));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println();


        System.out.println("--------------- MyClass DeclaredConstructors ---------------");
        Arrays.stream(myClassClass.getDeclaredConstructors()).forEach(System.out::println);
        System.out.println();


        System.out.println("--------------- MyClass DeclaredFields ---------------");
        Arrays.stream(myClassClass.getDeclaredMethods()).forEach(m -> {
            m.setAccessible(true);
            System.out.println(m);
        });
        System.out.println();

        System.out.println("--------------- MyClass Annotations ---------------");
        Arrays.stream(myClassClass.getAnnotations()).forEach(System.out::println);
        System.out.println();


        System.out.println("--------------- MyClass Method Annotations ---------------");
        Method method = myClassClass.getMethod("sum", int.class, int.class);
        Arrays.stream(method.getAnnotations()).forEach(System.out::println);

        Integer invoke = (Integer) method.invoke(myClass, 10, 20);
        System.out.printf("%s %d\n", "MyClass sum invoke =>", invoke);
        System.out.println();
    }
}