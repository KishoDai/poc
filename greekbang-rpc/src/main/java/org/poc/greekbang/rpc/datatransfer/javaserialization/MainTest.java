package org.poc.greekbang.rpc.datatransfer.javaserialization;

import java.io.*;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Foo foo = new Foo();
        foo.setName("kisho");
        serialize(foo);
        deserialize();
    }

    private static void serialize(Foo foo) throws Exception {
        File file = new File("foo.ser");
        if (file.exists()) {
            file.delete();
        } else {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(foo);
        fos.close();
    }

    private static void deserialize() throws Exception {
        File file = new File("foo.ser");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Foo foo = (Foo) ois.readObject();
        System.out.println(foo.getName());
    }
}
