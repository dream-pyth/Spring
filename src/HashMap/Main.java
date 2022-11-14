package HashMap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("王志斌", "2");

        // key相同时会进行覆盖操作，并且返回被覆盖的值
        String result  = hashMap.put("王志斌", "3");

        System.out.println(result); // 2

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("ceshi", "2");


        System.out.println(Integer.highestOneBit(15)); // 8
        System.out.println(Integer.highestOneBit(16)); // 16
        System.out.println(Integer.highestOneBit(17)); //16
    }
}
