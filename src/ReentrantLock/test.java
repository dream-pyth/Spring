package ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class test {

    private static ReentrantLock lock = new ReentrantLock();
//    private static ReentrantLock.MyLock lock = new ReentrantLock.MyLock();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock(); // state :0 --> 1
//                lock.lock(); // state :1 --> 2
                try {
                    drawMoney();
                } finally {
                    lock.unlock();
                }
            }
        }, "线程1").start();

        // ========================================

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    drawMoney();
                } finally {
                    lock.unlock();
                }
            }
        }, "线程2").start();

    }

    private static void drawMoney() {
        System.out.println(Thread.currentThread().getName() + "正在取钱。。。");
        sleep(3000);
        System.out.println(Thread.currentThread().getName() + "取钱完成。。。");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
