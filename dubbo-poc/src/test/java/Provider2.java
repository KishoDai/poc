import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider2 {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider2.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}