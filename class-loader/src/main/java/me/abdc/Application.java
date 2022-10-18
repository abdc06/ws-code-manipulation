package me.abdc;

public class Application {
    public static void main(String[] args) {

        ClassLoader classLoader = Application.class.getClassLoader(); // AppClassLoader
        ClassLoader parentClassLoader = classLoader.getParent(); // PlatformClassLoader
        ClassLoader parentParentClassLoader = parentClassLoader.getParent(); // BootstrapClassLoader

        assert classLoader != null;
        assert parentClassLoader != null;
        assert parentParentClassLoader == null;

        assert "jdk.internal.loader.ClassLoaders$AppClassLoader".equals(classLoader.getClass().getName());
        assert "jdk.internal.loader.ClassLoaders$PlatformClassLoader".equals(parentClassLoader.getClass().getName());
        assert parentParentClassLoader == null;

        System.out.println(classLoader);
        System.out.println(parentClassLoader);
        System.out.println(parentParentClassLoader);
    }
}