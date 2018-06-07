import java.io.FileInputStream;
import java.io.IOException;

public class Decompiler {

    private static final int MAGIC_NUMBER_LENGTH = 4;


    public static void main(String[] args) throws IOException {
        String classFilePath = "C:\\Users\\daiji\\Documents\\bsgit\\BS-capital-gateway\\capital-gateway-core\\target\\classes\\cn\\bs\\capital\\gateway\\core\\service\\RepaymentImpl.class";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(classFilePath);
            byte[] dataBytes = new byte[fis.available()];
            System.out.println(fis.read(dataBytes));
            if (dataBytes.length > 0) {

                System.out.println("魔数(magic):" + getMagicNumber(dataBytes));
                System.out.println("minor_version:" + getMinorVersion(dataBytes));
                System.out.println("major_version:" + getMajorVersion(dataBytes));
                int constantPoolCount = getConstantPoolCount(dataBytes);
                System.out.println("constant_pool_count:" + constantPoolCount);

                int dataBytesIndex = 10;
                for (int constantPoolIndex = 1; constantPoolIndex <= constantPoolCount; constantPoolIndex++) {
                    int tag = (int) dataBytes[dataBytesIndex];
                    System.out.println("tag:" + tag);
                    break;
                }

            }
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    private static String getMagicNumber(byte[] b) {
        StringBuilder magicNumberBuilder = new StringBuilder("0x");
        for (int i = 0; i < MAGIC_NUMBER_LENGTH; i++) {
            magicNumberBuilder.append(Integer.toHexString(b[i]).substring(6).toUpperCase());
        }
        return magicNumberBuilder.toString();
    }

    private static int getMinorVersion(byte[] b) {
        return (((int) b[4]) << 8) + b[5];
    }

    private static int getMajorVersion(byte[] b) {
        return (((int) b[6]) << 8) + b[7];
    }

    private static int getConstantPoolCount(byte[] b) {
        return (((int) b[8]) << 8) + b[9];
    }

}
