import model.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Decompiler {

    private static final int MAGIC_NUMBER_LENGTH = 4;


    public static void main(String[] args) throws IOException {
//        String classFilePath = "C:\\Users\\daiji\\Documents\\bsgit\\BS-capital-gateway\\capital-gateway-core\\target\\classes\\cn\\bs\\capital\\gateway\\core\\service\\RepaymentImpl.class";
        String classFilePath = "C:\\Users\\daiji\\Documents\\git\\poc\\decompiler\\target\\classes\\model\\ClassConstantTagEnum.class";
        FileInputStream fis = null;
        ClassInfo classInfo = new ClassInfo();
        try {
            fis = new FileInputStream(classFilePath);
            byte[] dataBytes = new byte[fis.available()];
            fis.read(dataBytes);
            if (dataBytes.length <= 0) {
                System.out.println("文件内容为空!");
                return;
            }

            //魔法数字
            classInfo.setMagic(getMagic(dataBytes));
            //最小版本
            classInfo.setMinorVersion(readShort(dataBytes, 4));
            //最大版本
            classInfo.setMajorVersion(readShort(dataBytes, 6));
            //常量池解析 开始
            ClassConstantPoolInfo classConstantPoolInfo = new ClassConstantPoolInfo();
            classConstantPoolInfo.setConstantPoolCount(readShort(dataBytes, 8));
            classConstantPoolInfo.setClassConstantInfos(new ClassConstantInfo[classConstantPoolInfo.getConstantPoolCount()]);
            classInfo.setConstantPoolInfo(classConstantPoolInfo);

            int dataBytesIndex = 9;
            for (int constantPoolIndex = 1; constantPoolIndex < classConstantPoolInfo.getConstantPoolCount(); constantPoolIndex++) {
                dataBytesIndex += 1; //tag u1
                ClassConstantTagEnum tagEnum = ClassConstantTagEnum.getClassConstantTagEnum(dataBytes[dataBytesIndex]);
                if (tagEnum == ClassConstantTagEnum.CONSTANT_Utf8) {
                    int length = readShort(dataBytes, dataBytesIndex + 1);
                    dataBytesIndex += 2; //length u2

                    classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantUTF8Info(constantPoolIndex,
                            ClassConstantTagEnum.CONSTANT_Utf8,
                            new String(dataBytes, dataBytesIndex + 1, length));

                    dataBytesIndex += length;
                } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Integer) {
                    classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantNumberInfo(constantPoolIndex,
                            ClassConstantTagEnum.CONSTANT_Integer,
                            readInt(dataBytes, dataBytesIndex + 1));

                    dataBytesIndex += 4;
                } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Float) {
                    classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantNumberInfo(constantPoolIndex,
                            ClassConstantTagEnum.CONSTANT_Float,
                            readFloat(dataBytes, dataBytesIndex + 1));

                    dataBytesIndex += 4;
                } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Long) {
                    classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantNumberInfo(constantPoolIndex,
                            ClassConstantTagEnum.CONSTANT_Long,
                            readLong(dataBytes, dataBytesIndex + 1));

                    dataBytesIndex += 8;
                } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Double) {
                    classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantNumberInfo(constantPoolIndex,
                            ClassConstantTagEnum.CONSTANT_Double,
                            readDouble(dataBytes, dataBytesIndex + 1));

                    dataBytesIndex += 8;
                } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Class) {
                    classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantClassInfo(constantPoolIndex,
                            ClassConstantTagEnum.CONSTANT_Class,
                            readShort(dataBytes, dataBytesIndex + 1));

                    dataBytesIndex += 2;
                } else if (tagEnum == ClassConstantTagEnum.CONSTANT_String) {
                    classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantStringInfo(constantPoolIndex,
                            ClassConstantTagEnum.CONSTANT_String,
                            readShort(dataBytes, dataBytesIndex + 1));

                    dataBytesIndex += 2; //string_index u2
                } else if (tagEnum == ClassConstantTagEnum.CONSTANT_Fieldref
                        || tagEnum == ClassConstantTagEnum.CONSTANT_Methodref
                        || tagEnum == ClassConstantTagEnum.CONSTANT_InterfaceMethodref
                        || tagEnum == ClassConstantTagEnum.CONSTANT_NameAndType) {
                    short classIndex = readShort(dataBytes, dataBytesIndex + 1);
                    dataBytesIndex += 2; //class_index u2
                    short nameAndTypeIndex = readShort(dataBytes, dataBytesIndex + 1);
                    dataBytesIndex += 2; //name_and_type_index u2

                    classConstantPoolInfo.getClassConstantInfos()[constantPoolIndex] = new ClassConstantReferenceInfo(constantPoolIndex,
                            tagEnum,
                            classIndex,
                            nameAndTypeIndex);
                }
            }
            //常量池解析 结束

            //access_flags解析
            short accessFlags = readShort(dataBytes, dataBytesIndex + 1);
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
            dataBytesIndex += 2;

            //this_class解析
            classInfo.setThisClassIndex(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;

            //super_class解析
            classInfo.setSuperClassIndex(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;

            //interfaces解析
            ClassInterfacesInfo classInterfacesInfo = new ClassInterfacesInfo();
            classInterfacesInfo.setInterfacesCount(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;
            classInterfacesInfo.setInterfaceInfos(new ClassInterfaceInfo[classInterfacesInfo.getInterfacesCount()]);
            for (int i = 0; i < classInterfacesInfo.getInterfacesCount(); i++) {
                ClassInterfaceInfo classInterfaceInfo = new ClassInterfaceInfo();
                classInterfaceInfo.setInterfaceIndex(i);
                classInterfaceInfo.setConstantPoolIndex(readShort(dataBytes, dataBytesIndex + 1));

                dataBytesIndex += 2;

                classInterfacesInfo.getInterfaceInfos()[i] = classInterfaceInfo;
            }
            classInfo.setInterfacesInfo(classInterfacesInfo);

            //fileds解析 开始
            ClassFieldsInfo classFieldsInfo = new ClassFieldsInfo();
            classFieldsInfo.setFieldsCount(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;
            classFieldsInfo.setFieldInfos(new ClassFieldInfo[classFieldsInfo.getFieldsCount()]);
            for (int i = 0; i < classFieldsInfo.getFieldsCount(); i++) {
                ClassFieldInfo classFieldInfo = new ClassFieldInfo();

                classFieldInfo.setFieldIndex(i);
                //access_flags解析
                short methodAccessFlags = readShort(dataBytes, dataBytesIndex + 1);
                classFieldInfo.setAccPublic((methodAccessFlags & 1) == 1);
                classFieldInfo.setAccPrivate((methodAccessFlags & 2) == 2);
                classFieldInfo.setAccProtected((methodAccessFlags & 4) == 4);
                classFieldInfo.setAccStatic((methodAccessFlags & 8) == 8);
                classFieldInfo.setAccFinal((methodAccessFlags & 16) == 16);
                classFieldInfo.setAccVolatile((methodAccessFlags & 64) == 64);
                classFieldInfo.setAccTransient((methodAccessFlags & 128) == 128);
                classFieldInfo.setAccSynthetic((methodAccessFlags & 4096) == 4096);
                classFieldInfo.setAccEnum((methodAccessFlags & 16384) == 16384);
                dataBytesIndex += 2;
                //name_index解析
                classFieldInfo.setNameIndex(readShort(dataBytes, dataBytesIndex + 1));
                dataBytesIndex += 2;
                //descriptor_index解析
                classFieldInfo.setDescriptorIndex(readShort(dataBytes, dataBytesIndex + 1));
                dataBytesIndex += 2;

                //attribute解析
                ClassAttributesInfo classAttributesInfo = new ClassAttributesInfo();
                //attribute_count解析
                classAttributesInfo.setAttributeCount(readShort(dataBytes, dataBytesIndex + 1));
                dataBytesIndex += 2;
                classAttributesInfo.setAttributeInfos(new ClassAttributeInfo[classAttributesInfo.getAttributeCount()]);

                for (int j = 0; j < classAttributesInfo.getAttributeCount(); j++) {
                    ClassAttributeInfo classAttributeInfo = new ClassAttributeInfo();
                    classAttributeInfo.setAttributeIndex(j);
                    //attribute_name_index
                    classAttributeInfo.setAttributeNameIndex(readShort(dataBytes, dataBytesIndex + 1));
                    dataBytesIndex += 2;
                    //attribute_length u4
                    classAttributeInfo.setAttributeLength(readInt(dataBytes, dataBytesIndex + 1));
                    dataBytesIndex += 4;
                    classAttributeInfo.setInfo(new String(dataBytes, dataBytesIndex + 1, classAttributeInfo.getAttributeLength()));
                    dataBytesIndex += classAttributeInfo.getAttributeLength();
                    classAttributesInfo.getAttributeInfos()[j] = classAttributeInfo;
                }
                classFieldInfo.setAttributesInfo(classAttributesInfo);
                classFieldsInfo.getFieldInfos()[i] = classFieldInfo;
            }

            classInfo.setFieldsInfo(classFieldsInfo);
            //fileds解析 结束

            //methods解析 开始
            ClassMethodsInfo classMethodsInfo = new ClassMethodsInfo();
            classMethodsInfo.setMethodsCount(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;
            classMethodsInfo.setMethodInfos(new ClassMethodInfo[classMethodsInfo.getMethodsCount()]);
            for (int i = 0; i < classMethodsInfo.getMethodsCount(); i++) {
                ClassMethodInfo classMethodInfo = new ClassMethodInfo();

                classMethodInfo.setMethodIndex(i);
                //access_flags解析
                short methodAccessFlags = readShort(dataBytes, dataBytesIndex + 1);
                classMethodInfo.setAccPublic((methodAccessFlags & 1) == 1);
                classMethodInfo.setAccPrivate((methodAccessFlags & 2) == 2);
                classMethodInfo.setAccProtected((methodAccessFlags & 4) == 4);
                classMethodInfo.setAccStatic((methodAccessFlags & 8) == 8);
                classMethodInfo.setAccFinal((methodAccessFlags & 16) == 16);
                classMethodInfo.setAccSynchronized((methodAccessFlags & 32) == 32);
                classMethodInfo.setAccNative((methodAccessFlags & 128) == 128);
                classMethodInfo.setAccAbstract((methodAccessFlags & 512) == 512);
                classMethodInfo.setAccStrict((methodAccessFlags & 2048) == 2048);
                dataBytesIndex += 2;
                //name_index解析
                classMethodInfo.setNameIndex(readShort(dataBytes, dataBytesIndex + 1));
                dataBytesIndex += 2;
                //descriptor_index解析
                classMethodInfo.setDescriptorIndex(readShort(dataBytes, dataBytesIndex + 1));
                dataBytesIndex += 2;

                //attribute解析
                ClassAttributesInfo classAttributesInfo = new ClassAttributesInfo();
                //attribute_count解析
                classAttributesInfo.setAttributeCount(readShort(dataBytes, dataBytesIndex + 1));
                dataBytesIndex += 2;
                classAttributesInfo.setAttributeInfos(new ClassAttributeInfo[classAttributesInfo.getAttributeCount()]);

                for (int j = 0; j < classAttributesInfo.getAttributeCount(); j++) {
                    ClassAttributeInfo classAttributeInfo = new ClassAttributeInfo();
                    classAttributeInfo.setAttributeIndex(j);
                    //attribute_name_index
                    classAttributeInfo.setAttributeNameIndex(readShort(dataBytes, dataBytesIndex + 1));
                    dataBytesIndex += 2;
                    //attribute_length u4
                    classAttributeInfo.setAttributeLength(readInt(dataBytes, dataBytesIndex + 1));
                    dataBytesIndex += 4;
                    classAttributeInfo.setInfo(new String(dataBytes, dataBytesIndex + 1, classAttributeInfo.getAttributeLength()));
                    dataBytesIndex += classAttributeInfo.getAttributeLength();
                    classAttributesInfo.getAttributeInfos()[j] = classAttributeInfo;
                }
                classMethodInfo.setAttributesInfo(classAttributesInfo);
                classMethodsInfo.getMethodInfos()[i] = classMethodInfo;
            }
            classInfo.setMethodsInfo(classMethodsInfo);
            //methods解析 结束

            //attributes解析 开始
            ClassAttributesInfo classAttributesInfo = new ClassAttributesInfo();
            //attribute_count解析
            classAttributesInfo.setAttributeCount(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;
            classAttributesInfo.setAttributeInfos(new ClassAttributeInfo[classAttributesInfo.getAttributeCount()]);

            for (int j = 0; j < classAttributesInfo.getAttributeCount(); j++) {
                ClassAttributeInfo classAttributeInfo = new ClassAttributeInfo();
                classAttributeInfo.setAttributeIndex(j);
                //attribute_name_index
                classAttributeInfo.setAttributeNameIndex(readShort(dataBytes, dataBytesIndex + 1));
                dataBytesIndex += 2;
                //attribute_length u4
                classAttributeInfo.setAttributeLength(readInt(dataBytes, dataBytesIndex + 1));
                dataBytesIndex += 4;
                classAttributeInfo.setInfo(new String(dataBytes, dataBytesIndex + 1, classAttributeInfo.getAttributeLength()));
                dataBytesIndex += classAttributeInfo.getAttributeLength();
                classAttributesInfo.getAttributeInfos()[j] = classAttributeInfo;
            }
            classInfo.setAttributesInfo(classAttributesInfo);
            //attributes解析 结束


        } finally {
            if (fis != null) {
                fis.close();
            }
            System.out.println("classInfo:" + classInfo.toString());
        }
    }

    private static String getMagic(byte[] b) {
        StringBuilder magicNumberBuilder = new StringBuilder("0x");
        for (int i = 0; i < MAGIC_NUMBER_LENGTH; i++) {
            magicNumberBuilder.append(Integer.toHexString(b[i]).substring(6).toUpperCase());
        }
        return magicNumberBuilder.toString();
    }


    private static short readShort(byte[] b, int offset) throws IOException {
        return getDataInputStream(b, offset, 2).readShort();
    }

    private static int readInt(byte[] b, int offset) throws IOException {
        return getDataInputStream(b, offset, 4).readInt();
    }

    private static float readFloat(byte[] b, int offset) throws IOException {
        return getDataInputStream(b, offset, 4).readFloat();
    }

    private static double readDouble(byte[] b, int offset) throws IOException {
        return getDataInputStream(b, offset, 8).readDouble();
    }

    private static long readLong(byte[] b, int offset) throws IOException {
        return getDataInputStream(b, offset, 8).readLong();
    }

    private static String readUTF(byte[] b, int offset, int length) throws IOException {
        return getDataInputStream(b, offset, length).readUTF();
    }

    private static DataInputStream getDataInputStream(byte[] b, int offset, int length) {
        return new DataInputStream(new ByteArrayInputStream(b, offset, length));
    }


}
