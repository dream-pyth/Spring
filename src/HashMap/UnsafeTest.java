package HashMap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    private static String[] arr = {"1", "2", "3", "4", "5"};
    private static Unsafe unsafe = null;
    private static long I_OFFSET;
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

        // 数组中存储的对象的对象头大小
        int ns = unsafe.arrayIndexScale(String[].class);
        // 数组中第一个元素的起始位置
        int base = unsafe.arrayBaseOffset(String[].class);
        System.out.println(unsafe.getObject(UnsafeTest.arr, base + 3 *ns));
    }
}
