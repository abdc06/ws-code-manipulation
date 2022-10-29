package me.abdc;

@MyAnnotation
public class MyClass implements MyInterface {

    private int no = 1;
    private String name = "myClass";

    public static final int CONSTANT_NUMBER = 10;
    public static String STATIC_NAME = "HELLO";

    public MyClass() {
    }

    public MyClass(int no, String name) {
        this.no = no;
        this.name = name;
    }

    private void print() {
        String text = "MyClass{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
        System.out.println(text);
    }

    @MyAnnotation
    public int sum(int x, int y) {
        return x + y;
    }
}
