import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Decompiler {

    private static final int MAGIC_NUMBER_LENGTH = 4;


    public static void main(String[] args) throws IOException {
        String classFilePath = "C:\\Users\\daiji\\Documents\\bsgit\\BS-capital-gateway\\capital-gateway-core\\target\\classes\\cn\\bs\\capital\\gateway\\core\\service\\RepaymentImpl.class";
//        String classFilePath = "C:\\Users\\daiji\\Documents\\git\\poc\\decompiler\\target\\classes\\ClassConstantTagEnum.class";
        FileInputStream fis = null;
        ClassStructInfo classStructInfo = new ClassStructInfo();
        try {
            fis = new FileInputStream(classFilePath);
            byte[] dataBytes = new byte[fis.available()];
            fis.read(dataBytes);
            if (dataBytes.length <= 0) {
                System.out.println("文件内容为空!");
                return;
            }

            //魔法数字
            classStructInfo.setMagic(getMagic(dataBytes));
            //最小版本
            classStructInfo.setMinorVersion(readShort(dataBytes, 4));
            //最大版本
            classStructInfo.setMajorVersion(readShort(dataBytes, 6));
            //常量池解析 开始
            ClassConstantPoolInfo classConstantPoolInfo = new ClassConstantPoolInfo();
            classConstantPoolInfo.setConstantPoolCount(readShort(dataBytes, 8));
            classConstantPoolInfo.setClassConstantInfos(new ClassConstantInfo[classConstantPoolInfo.getConstantPoolCount()]);
            classStructInfo.setConstantPoolInfo(classConstantPoolInfo);

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
            classStructInfo.setAccessFlags(classAccessFlags);
            dataBytesIndex += 2;

            //this_class解析
            classStructInfo.setThisClassIndex(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;

            //super_class解析
            classStructInfo.setSuperClassIndex(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;

            //interfaces解析
            ClassInterfacesInfo classInterfacesInfo = new ClassInterfacesInfo();
            classInterfacesInfo.setInterfaceCount(readShort(dataBytes, dataBytesIndex + 1));
            dataBytesIndex += 2;
            classInterfacesInfo.setInterfaceInfos(new ClassInterfaceInfo[classInterfacesInfo.getInterfaceCount()]);
            for (int i = 0; i < classInterfacesInfo.getInterfaceCount(); i++) {
                ClassInterfaceInfo classInterfaceInfo = new ClassInterfaceInfo();
                classInterfaceInfo.setInterfaceIndex(i);
                classInterfaceInfo.setConstantPoolIndex(readShort(dataBytes, dataBytesIndex + 1));

                dataBytesIndex += 2;

                classInterfacesInfo.getInterfaceInfos()[i] = classInterfaceInfo;
            }
            classStructInfo.setInterfacesInfo(classInterfacesInfo);
            dataBytesIndex += 2;

            System.out.println("classStructInfo:" + classStructInfo.toString());
        } finally {
            if (fis != null) {
                fis.close();
            }
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
