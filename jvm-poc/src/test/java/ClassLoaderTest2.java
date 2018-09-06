public class ClassLoaderTest2 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ClassLoaderTest2 test2 = new ClassLoaderTest2();
        System.out.println(test2.getClass().getClassLoader());
        System.out.println(test2.getClass().getClassLoader().getParent());
        System.out.println(test2.getClass().getClassLoader().getParent().getParent());
//        Foo[] fooArr = new Foo[2];
//        System.out.println(Foo.class.newInstance(
//    <modelVersion>4.0.0</modelVersion>
//    <groupId>cn.BS</groupId>
//    <artifactId>capital-gateway-facade</artifactId>
//    <packaging>jar</packaging>
//    <version>1.0-SNAPSHOT</version>
//    <name>capital-gateway-facade</name>
//    <url>http://www.BS.cn</url>
//
//    <dependencies>
//        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
//        <dependency>
//            <groupId>org.projectlombok</groupId>
//            <artifactId>lombok</artifactId>
//            <version>1.16.18</version>
//            <scope>provided</scope>
//        </dependency>
//    </dependencies>
//
//    <distributionManagement>
//        <repository>
//            <id>nexus</id>
//            <name>Releases</name>
//            <url>http://bsnexus.immd.cn/nexus/content/repositories/releases/</url>
//        </repository>
//        <snapshotRepository>
//            <id>nexus</id>
//            <name>Snapshots</name>
//            <url>http://bsnexus.immd.cn/nexus/content/repositories/snapshots/</url>
//        </snapshotRepository>
//    </distributionManagement>
//
//    <build>
//        <defaultGoal>install</defaultGoal>
//        <plugins>
//            <plugin>
//                <artifactId>maven-compiler-plugin</artifactId>
//                <version>2.5.1</version>
//                <configuration>
//                    <source>1.7</source>
//                    <target>1.7</target>
//                    <encoding>UTF-8</encoding>
//                </configuration>
//            </plugin>
//            <plugin>
//                <groupId>org.apache.maven.plugins</groupId>
//                <artifactId>maven-resources-plugin</artifactId>
//                <version>2.5</version>
//                <configuration>
//                    <encoding>UTF-8</encoding>
//                </configuration>
//            </plugin>
//            <plugin>
//                <artifactId>maven-source-plugin</artifactId>
//                <version>3.0.0</version>
//                <configuration>
//                    <attach>true</attach>
//                </configuration>
//                <executions>
//                    <execution>
//                        <phase>compile</phase>
//                        <goals>
//                            <goal>jar</goal>
//                        </goals>
//                    </execution>
//                </executions>
//            </plugin>
//        </plugins>
//
//        <resources>
//            <resource>
//                <directory>src/main/resources</directory>
//                <filtering>true</filtering>
//            </resource>
//            <resource>
//                <directory>src/main/java</directory>
//                <includes>
//                    <include>**/*.xml</include>
//                </includes>
//            </resource>
//        </resources>
//        <testResources>
//            <testResource>
//                <directory>src/test/resources</directory>
//                <filtering>true</filtering>
//            </testResource>
//        </testResources>
//
//    </build>
//
//    <reporting>
//        <plugins>
//            <plugin>
//                <groupId>org.apache.maven.plugins</groupId>
//                <artifactId>maven-javadoc-plugin</artifactId>
//                <version>2.10.3</version>
//            </plugin>
//            <plugin>
//                <groupId>org.codehaus.mojo</groupId>
//                <artifactId>cobertura-maven-plugin</artifactId>
//                <version>2.7</version>
//                <configuration>
//                    <check>true</check>
//                    <formats>
//                        <format>xml</format>
//                    </formats>
//                </configuration>
//            </plugin>
//        </plugins>
//    </reporting>));
//        System.out.println(fooArr);
//        Foo foo = null;
//        System.out.println(Foo.A);
        System.out.println(Foo.B);
    }
}
