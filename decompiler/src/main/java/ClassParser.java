import java.io.IOException;
import java.io.InputStream;

public class ClassParser {

    private int parsedIndex;

    public ClassStructInfo parse(InputStream inputStream) throws IOException {
        byte[] b = new byte[inputStream.available()];
        inputStream.read(b);
        return parse(b);
    }

    public ClassStructInfo parse(byte[] b) {
        //TODO
        return null;
    }
}
