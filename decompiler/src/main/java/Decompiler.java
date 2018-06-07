import java.io.FileInputStream;
import java.io.IOException;

public class Decompiler {

    private static final int MAGIC_NUMBER_LENGTH = 4;


    public static void main(String[] args) throws IOException {
        String classFilePath = "C:\\Users\\daiji\\Documents\\bsgit\\BS-capital-gateway\\capital-gateway-core\\target\\classes\\cn\\bs\\capital\\gateway\\core\\service\\RepaymentImpl.class";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(classFilePath);
            byte[] tempBytes = new byte[fis.available()];
            System.out.println(fis.read(tempBytes));
            if (tempBytes.length > 0) {
                int currentDataIndex = 0;
                System.out.println("魔数(magic):" + getMagicNumber(tempBytes));
                System.out.println("minor_version:" + getMinorVersion(tempBytes));
                System.out.println("major_version:" + getMajorVersion(tempBytes));
                System.out.println("cp_pool_count:" + getCpPoolCount(tempBytes));
                currentDataIndex += MAGIC_NUMBER_LENGTH;


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

    private static int getCpPoolCount(byte[] b) {
        return (((int) b[8]) << 8) + b[9];
    }
}
