import java.sql.Connection;
import java.sql.DriverManager;

public class ForNameExample {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("Pqr");
//        Connection con = DriverManager.registerDriver();
    }
}
class Pqr{
    static {
        System.out.println("hello is static");
    }
    {
        System.out.println("instance");
    }
}
