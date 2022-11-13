package HashMap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Person {

    private int i = 0;
    // 偏移量
    private static long I_OFFSET;

    private static Unsafe unsafe = null;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            // 只有bootstrap加载的类才可以直接使用getUnsafe()方法
//        unsafe = Unsafe.getUnsafe();
            I_OFFSET = unsafe.objectFieldOffset(Person.class.getDeclaredField("i"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Person person = new Person();
                while (true) {
//                    person.i++;
                    boolean b = unsafe.compareAndSwapInt(person, I_OFFSET, person.i, person.i + 1);
                    if (b) {
                        System.out.println(unsafe.getIntVolatile(person, I_OFFSET));
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Person person = new Person();
                while (true) {
//                    person.i++;
                    boolean b = unsafe.compareAndSwapInt(person, I_OFFSET, person.i, person.i + 1);
                    if (b) {
                        System.out.println(unsafe.getIntVolatile(person, I_OFFSET));
                    }
                }
            }
        }).start();
    }
}
