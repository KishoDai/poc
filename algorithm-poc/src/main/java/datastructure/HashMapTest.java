package datastructure;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>(13,1);
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("4", "1");
        System.out.println(map);
        System.out.println(map.size());
    }
}
