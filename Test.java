import java.sql.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","");
        Statement st = con.createStatement();
        int n = new Scanner(System.in).nextInt();
        ResultSet rs = st.executeQuery("select * from details");


        String s = null;
        int id = 0;
        String dep = null;
        String name = null;
        int sal = 0;

        while(rs.next()){
            if (rs.getInt(1) == n){
                s  = rs.getString(4);
                dep = rs.getString(5);
                name = rs.getString(2);
                id = rs.getInt(1);
                sal = rs.getInt(6) + rs.getInt(7) - rs.getInt(8);
                break;
            }
        }

        ResultSet rsd = st.executeQuery("select * from desig");

        while(rsd.next()){
            if (s.equals(rsd.getString(1))){
                System.out.println(id + "\t" + name + "\t" + dep + "\t" + rsd.getString(2)+ "\t" + (sal+rsd.getInt(3)));
                break;
            }
        }
    }
}

