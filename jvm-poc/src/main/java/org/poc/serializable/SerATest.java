package org.poc.serializable;

import java.io.*;

public class SerATest {

    private static void writeObject(SerA serA) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SerA.ser"));
        oos.writeObject(serA);
        oos.close();
    }

    private static SerA readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SerA.ser"));
        SerA serA = (SerA) ois.readObject();
        return serA;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        SerA serA = new SerA();
//        serA.setName("kisho");
//        writeObject(serA);

        SerA serA = readObject();
        System.out.println(serA.getName());

    }
}
