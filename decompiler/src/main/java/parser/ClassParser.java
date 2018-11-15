package parser;

import model.*;

import java.io.*;

public class ClassParser {

    //class文件字节数字
    private byte[] b;

    //当前解析的位置
    private int currentIndex = 0;

    //解析后的class数据结构
    private ClassInfo classInfo;

    //-XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
    public static void main(String[] args) throws IOException {
//        String classFilePath = "C:\\Users\\daiji\\Documents\\bsgit\\BS-capitalmgmt-gateway\\capitalmgmt-gateway-core\\target\\classes\\cn\\bs\\capitalmgmt\\gateway\\core\\service\\RepaymentImpl.class";
        String classFilePath = "C:\\git_code\\kisho\\poc\\decompiler\\target\\classes\\model\\ClassConstantTagEnum.class";
        FileInputStream fis = null;
        ClassInfo classInfo = null;
        try {
            fis = new FileInputStream(classFilePath);
            classInfo = new ClassParser().parse(fis);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (classInfo != null) {
                System.out.println("classInfo:" + classInfo.toString());
            }
        }
    }

    public ClassInfo parse(InputStream is) throws IOException {
        byte[] tempB = new byte[is.available()];
        is.read(tempB);
        if (tempB.length <= 0) {
            System.out.println("文件内容为空!");
            return null;
        }
        return parse(tempB);
    }

    public ClassInfo parse(byte[] b) throws IOException {
        this.b = b;
        classInfo = new ClassInfo();
        //解析magic
        parseMagic();
        //解析minor_version
        parseMinorVersion();
        //解析major_version
        parseMajorVersion();
        //解析constant_pool
        parseConstantPool();
        //access_flags解析
        parseAccessFlags();
        //this_class解析
        parseThisClass();
        //super_class解析
        parseSuperClass();
        //interfaces解析
        parseInterfaces();
        //fields解析
        parseFieldsInfo();
        //methods解析
        parseMethodsInfo();
        //attributes解析
        classInfo.setAttributesInfo(parseClassAttributesInfo());
        return classInfo;
    }

    private void parseMethodsInfo() throws IOException {
        ClassMethodsInfo classMethodsInfo = new ClassMethodsInfo();
        classMethodsInfo.setMethodsCount(readShort(currentIndex));

        classMethodsInfo.setMethodInfos(new ClassMethodInfo[classMethodsInfo.getMethodsCount()]);
        for (int i = 0; i < classMethodsInfo.getMethodsCount(); i++) {
            ClassMethodInfo classMethodInfo = new ClassMethodInfo();

            classMethodInfo.setMethodIndex(i);
            //access_flags解析
            short methodAccessFlags = readShort(currentIndex);
            classMethodInfo.setAccPublic((methodAccessFlags & 1) == 1);
            classMethodInfo.setAccPrivate((methodAccessFlags & 2) == 2);
            classMethodInfo.setAccProtected((methodAccessFlags & 4) == 4);
            classMethodInfo.setAccStatic((methodAccessFlags & 8) == 8);
            classMethodInfo.setAccFinal((methodAccessFlags & 16) == 16);
            classMethodInfo.setAccSynchronized((methodAccessFlags & 32) == 32);
            classMethodInfo.setAccNative((methodAccessFlags & 128) == 128);
            classMethodInfo.setAccAbstract((methodAccessFlags & 512) == 512);
            classMethodInfo.setAccStrict((methodAccessFlags & 2048) == 2048);

            //name_index解析
            classMethodInfo.setNameIndex(readShort(currentIndex));
            //descriptor_index解析
            classMethodInfo.setDescriptorIndex(readShort(currentIndex));
            //attributes解析
            classMethodInfo.setAttributesInfo(parseClassAttributesInfo());
            classMethodsInfo.getMethodInfos()[i] = classMethodInfo;
        }
        classInfo.setMethodsInfo(classMethodsInfo);
    }

    private void parseFieldsInfo() throws IOException {
        parseFieldsInfoCount();
        parseFieldInfoArray();
    }

    private void parseFieldInfoArray() throws IOException {
        ClassFieldsInfo classFieldsInfo = classInfo.getFieldsInfo();
        classFieldsInfo.setFieldInfos(new ClassFieldInfo[classFieldsInfo.getFieldsCount()]);
        for (int i = 0; i < classFieldsInfo.getFieldsCount(); i++) {
            ClassFieldInfo classFieldInfo = new ClassFieldInfo();
            classFieldInfo.setFieldIndex(i);
            //access_flags解析
            short methodAccessFlags = readShort(currentIndex);
            classFieldInfo.setAccPublic((methodAccessFlags & 1) == 1);
            classFieldInfo.setAccPrivate((methodAccessFlags & 2) == 2);
            classFieldInfo.setAccProtected((methodAccessFlags & 4) == 4);
            classFieldInfo.setAccStatic((methodAccessFlags & 8) == 8);
            classFieldInfo.setAccFinal((methodAccessFlags & 16) == 16);
            classFieldInfo.setAccVolatile((methodAccessFlags & 64) == 64);
            classFieldInfo.setAccTransient((methodAccessFlags & 128) == 128);
            classFieldInfo.setAccSynthetic((methodAccessFlags & 4096) == 4096);
            classFieldInfo.setAccEnum((methodAccessFlags & 16384) == 16384);
            //name_index解析
            classFieldInfo.setNameIndex(readShort(currentIndex));
            //descriptor_index解析
            classFieldInfo.setDescriptorIndex(readShort(currentIndex));
            //attribute解析
            classFieldInfo.setAttributesInfo(parseClassAttributesInfo());
            classFieldsInfo.getFieldInfos()[i] = classFieldInfo;
        }
    }

    private ClassAttributesInfo parseClassAttributesInfo() throws IOException {
        ClassAttributesInfo classAttributesInfo = new ClassAttributesInfo();
        classAttributesInfo.setAttributeCount(readShort(currentIndex));
        classAttributesInfo.setAttributeInfos(new ClassAttributeInfo[classAttributesInfo.getAttributeCount()]);

        for (int i = 0; i < classAttributesInfo.getAttributeCount(); i++) {
            int attributeNameIndex = readShort(currentIndex);
            ClassConstantUTF8Info constantUTF8Info = (ClassConstantUTF8Info) classInfo.getConstantPoolInfo().getClassConstantInfos()[attributeNameIndex];
            String attributeName = constantUTF8Info.getValue();
            if ("Code".equals(attributeName)) {
                ClassAttributeCodeInfo classAttributeInfo = new ClassAttributeCodeInfo();
                classAttributeInfo.setAttributeIndex(i);
                //attribute_name_index
                classAttributeInfo.setAttributeNameIndex(attributeNameIndex);
                //attribute_length u4
                classAttributeInfo.setAttributeLength(readInt(currentIndex));
                classAttributeInfo.setMaxStack(readShort(currentIndex));
                classAttributeInfo.setMaxLocals(readShort(currentIndex));
                classAttributeInfo.setCodeLength(readInt(currentIndex));
//                System.out.println("classAttributeInfo.getCodeLength()=" + classAttributeInfo.getCodeLength());
                int temp = currentIndex;
                for (int m = 0; m < classAttributeInfo.getCodeLength(); m++) {
                    System.out.println(b[++temp]);
                }
                classAttributeInfo.setCode(new String(b, currentIndex + 1, classAttributeInfo.getCodeLength()));
                currentIndex += classAttributeInfo.getCodeLength();

                classAttributeInfo.setExceptionTableLength(readShort(currentIndex));
                classAttributeInfo.setExceptionTable(new String(b, currentIndex + 1, classAttributeInfo.getExceptionTableLength()));
                currentIndex += classAttributeInfo.getExceptionTableLength();

                classAttributeInfo.setAttributesInfo(parseClassAttributesInfo());
                classAttributesInfo.getAttributeInfos()[i] = classAttributeInfo;
            } else {
                ClassAttributeInfo classAttributeInfo = new ClassAttributeInfo();
                classAttributeInfo.setAttributeIndex(i);
                //attribute_name_index
                classAttributeInfo.setAttributeNameIndex(attributeNameIndex);
                //attribute_length u4
                classAttributeInfo.setAttributeLength(readInt(currentIndex));
                classAttributeInfo.setInfo(new String(b, currentIndex + 1, classAttributeInfo.getAttributeLength()));
                currentIndex += classAttributeInfo.getAttributeLength();
                classAttributesInfo.getAttributeInfos()[i] = classAttributeInfo;
            }
        }
        return classAttributesInfo;
    }

    private void parseFieldsInfoCount() throws IOException {
        ClassFieldsInfo classFieldsInfo = new ClassFieldsInfo();
        classFieldsInfo.setFieldsCount(readShort(currentIndex));
        classInfo.setFieldsInfo(classFieldsInfo);
    }

    private void parseInterfaces() throws IOException {
        parseInterfacesCount();
        parseInterfacesArray();
    }

    private void parseInterfacesArray() throws IOException {
        ClassInterfacesInfo classInterfacesInfo = classInfo.getInterfacesInfo();
        classInterfacesInfo.setInterfaceInfos(new ClassInterfaceInfo[classInterfacesInfo.getInterfacesCount()]);
        for (int i = 0; i < classInterfacesInfo.getInterfacesCount(); i++) {
            ClassInterfaceInfo classInterfaceInfo = new ClassInterfaceInfo();
            classInterfaceInfo.setInterfaceIndex(i);
            classInterfaceInfo.setConstantPoolIndex(readShort(currentIndex));
            classInterfacesInfo.getInterfaceInfos()[i] = classInterfaceInfo;
        }
    }

    private void parseInterfacesCount() throws IOException {
        ClassInterfacesInfo classInterfacesInfo = new ClassInterfacesInfo();
        classInterfacesInfo.setInterfacesCount(readShort(currentIndex));
        classInfo.setInterfacesInfo(classInterfacesInfo);
    }

    private void parseSuperClass() throws IOException {
        classInfo.setSuperClassIndex(readShort(currentIndex));
    }

    private void parseThisClass() throws IOException {
        classInfo.setThisClassIndex(readShort(currentIndex));
    }

    private void parseAccessFlags() throws IOException {
        short accessFlags = readShort(currentIndex);
        ClassAccessFlags classAccessFlags = new ClassAccessFlags();
        classAccessFlags.setAccPublic((accessFlags & 1) == 1);
        classAccessFlags.setAccFinal((accessFlags & 16) == 16);
        classAccessFlags.setAccSuper((accessFlags & 32) == 32);
        classAccessFlags.setAccInterface((accessFlags & 512) == 512);
        classAccessFlags.setAccAbstract((accessFlags & 1024) == 1024);
        classAccessFlags.setAccSynthetic((accessFlags & 4096) == 4096);
        classAccessFlags.setAccAnnotation((accessFlags & 8192) == 8192);
        classAccessFlags.setAccEnum((accessFlags & 16384) == 16384);
        classInfo.setAccessFlags(classAccessFlags);
    }

    private void parseConstantPool() throws IOException {
        parseConstantPoolCount();
        parseConstantPoolInfos();
    }

    private void parseConstantPoolCount() throws IOException {
        ClassConstantPoolInfo constantPoolInfo = new ClassConstantPoolInfo();
        constantPoolInfo.setConstantPoolCount(readShort(currentIndex));
        constantPoolInfo.setClassConstantInfos(new ClassConstantInfo[constantPoolInfo.getConstantPoolCount()]);
        classInfo.setConstantPoolInfo(constantPoolInfo);
    }

    private void parseConstantPoolInfos() throws IOException {
        for (int constantPoolIndex = 1; constantPoolIndex < classInfo.getConstantPoolInfo().getConstantPoolCount(); constantPoolIndex++) {
            parseConstantPoolInfo(constantPoolIndex);
        }
    }

    private void parseConstantPoolInfo(int constantPoolIndex) throws IOException {
        ClassConstantPoolInfo classConstantPoolInfo = classInfo.getConstantPoolInfo();
        ClassConstantTagEnum tagEnum = parseConstantPoolInfoTag();
        if (tagEnum == ClassConstantTagEnum.CONSTANT_Utf8) {
            int length = readShort(currentIndex);
            classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantUTF8Info(constantPoolIndex,
                    ClassConstantTagEnum.CONSTANT_Utf8,
                    new String(b, currentIndex + 1, length));
            currentIndex += length;
        } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Integer) {
            classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantNumberInfo(constantPoolIndex,
                    ClassConstantTagEnum.CONSTANT_Integer,
                    readInt(currentIndex));
        } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Float) {
            classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantNumberInfo(constantPoolIndex,
                    ClassConstantTagEnum.CONSTANT_Float,
                    readFloat(currentIndex));
        } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Long) {
            classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantNumberInfo(constantPoolIndex,
                    ClassConstantTagEnum.CONSTANT_Long,
                    readLong(currentIndex));
        } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Double) {
            classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantNumberInfo(constantPoolIndex,
                    ClassConstantTagEnum.CONSTANT_Double,
                    readDouble(currentIndex));
        } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Class) {
            classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantClassInfo(constantPoolIndex,
                    ClassConstantTagEnum.CONSTANT_Class,
                    readShort(currentIndex));
        } else if (tagEnum == ClassConstantTagEnum.CONSTANT_String) {
            classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantStringInfo(constantPoolIndex,
                    ClassConstantTagEnum.CONSTANT_String,
                    readShort(currentIndex));
            //string_index u2
        } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Fieldref
                || tagEnum == ClassConstantTagEnum.CONSTANT_Methodref
                || tagEnum == ClassConstantTagEnum.CONSTANT_InterfaceMethodref
                || tagEnum == ClassConstantTagEnum.CONSTANT_NameAndType) {
            short classIndex = readShort(currentIndex);
            //class_index u2
            short nameAndTypeIndex = readShort(currentIndex);
            //name_and_type_index u2

            classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantReferenceInfo(constantPoolIndex,
                    tagEnum,
                    classIndex,
                    nameAndTypeIndex);
        }
    }

    private ClassConstantTagEnum parseConstantPoolInfoTag() {
        currentIndex += 1; //tag u1
        return ClassConstantTagEnum.getClassConstantTagEnum(b[currentIndex]);
    }

    private void parseMagic() {
        StringBuilder magicNumberBuilder = new StringBuilder("0x");
        for (int i = 0; i < 4; i++) {
            magicNumberBuilder.append(Integer.toHexString(b[i]).substring(6).toUpperCase());
        }
        currentIndex += 3;
        classInfo.setMagic(magicNumberBuilder.toString());
    }

    private void parseMinorVersion() throws IOException {
        classInfo.setMinorVersion(readShort(currentIndex));
    }

    private void parseMajorVersion() throws IOException {
        classInfo.setMajorVersion(readShort(currentIndex));
    }

    private short readShort(int offset) throws IOException {
        currentIndex += 2;
        return getDataInputStream(offset + 1, 2).readShort();
    }

    private int readInt(int offset) throws IOException {
        currentIndex += 4;
        return getDataInputStream(offset + 1, 4).readInt();
    }

    private float readFloat(int offset) throws IOException {
        currentIndex += 4;
        return getDataInputStream(offset + 1, 4).readFloat();
    }

    private double readDouble(int offset) throws IOException {
        currentIndex += 8;
        return getDataInputStream(offset + 1, 8).readDouble();
    }

    private long readLong(int offset) throws IOException {
        currentIndex += 8;
        return getDataInputStream(offset + 1, 8).readLong();
    }

    private String readUTF(int offset, int length) throws IOException {
        currentIndex += length;
        return getDataInputStream(offset + 1, length).readUTF();
    }

    private DataInputStream getDataInputStream(int offset, int length) {
        return new DataInputStream(new ByteArrayInputStream(b, offset, length));
    }

}
