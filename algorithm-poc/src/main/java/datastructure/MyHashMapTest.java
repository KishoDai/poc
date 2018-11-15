package datastructure;

import java.util.HashMap;
import java.util.Map;

public class MyHashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>(4, 1);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        System.out.println(map);
        System.out.println(map.size());
    }
}
