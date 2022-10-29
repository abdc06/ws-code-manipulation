package me.abdc.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);
        injectFieldInstance(classType, instance);
        return instance;
    }

    /**
     * 전달 받은 클래스 타입의 필드 중 @MyInject 어노테이션이 입력된 필드에 대해 인스턴스를 생성하여 주입
     * @param classType
     * @param instance
     * @param <T>
     */
    private static <T> void injectFieldInstance(Class<T> classType, T instance) {
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            if (f.getAnnotation(MyInject.class) != null) {
                Object fieldInstance = createInstance(f.getType());
                f.setAccessible(true);
                try {
                    f.set(instance, fieldInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * 전달 받은 클래스 타입의 인스턴스를 생성
     * @param classType
     * @return
     * @param <T>
     */
    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
