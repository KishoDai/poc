package org.poc.classload;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    private String path;
    private String name;

    public MyClassLoader(String path, String name) {
        this.path = path;
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        name = path + name;
        //使用输入流读取类文件
        InputStream in = null;
        //使用byteArrayOutputStream保存类文件。然后转化为byte数组
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] bArr = out.toByteArray();
        return defineClass(name, bArr, 0, bArr.length);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader loader = new MyClassLoader("C:\\git_code\\kisho\\poc\\jvm-poc\\target\\test-classes\\", "MyClassLoader");
        Class clz = loader.findClass("org\\poc\\classload\\MyClassLoader.class");
        System.out.println(clz);
    }
}
