import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        Pattern pattern  = Pattern.compile("12(\\w*1)(2\\w*)");
        Matcher matcher = pattern.matcher("12xxx12xxx");
        System.out.println(matcher.matches());
        System.out.println(matcher.groupCount());
        System.out.println(matcher.start(1));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
    }
}
