import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MyLock {

    private static Unsafe unsafe = getUnsafe();

    private volatile int state;

    private static long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上锁
     */
    void lock() {
        // 一个原子性的指令，四个参数 ：线程，属性值，期望值，更新值   先比较在交换
        while (!unsafe.compareAndSwapInt(this, stateOffset, 0, 1)) {
            System.out.println(Thread.currentThread().getName() + "正在加锁。。。");
        }
        System.out.println(Thread.currentThread().getName() + "加到锁了");
    }

    /**
     * 解锁
     */
    void unlock() {
        state = 0;
    }

    /**
     * Unsafe类根据名字则可以判断出是一个不安全的类，因为他可以对内存进行申请/释放/访问，支持底层硬件的atomic/volatile，
     * 可以创建未初始化对象等，存在很大的风险。原本的设计只应该在标准库中使用，所以不建议在生产环境中使用。
     *
     * 1.Unsafe被设计成单例模式，构造方法私有。
     *
     * 2.Unsafe被设计成只能从引导类加载器（bootstarp class loader）加载，如果不是从启动类加载器直接调用getUnsafe方法则会抛出以下异常:
     * Caused by: java.lang.SecurityException: Unsafe
     * at sun.misc.Unsafe.getUnsafe
     *
     * 因此通过让代码“受信任”, 通过java的反射机制，通过反射将private单例属性强行设置accessible为true，然后通过Field的get方法，
     * 直接获取Object再进行类型转化强转为Unsafe。
     */
    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
