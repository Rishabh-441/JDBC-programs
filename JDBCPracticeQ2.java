import java.sql.*;

public class JDBCPracticeQ2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abcd","root","");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from employees");
        while(rs.next()){
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
        }

        System.out.println("\n\nhaving salary less than 2000 and more than 1000:\n");
        rs = st.executeQuery("select * from employees where salary > 1000 and salary < 2000");
        while (rs.next()){
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
        }
        rs.close();
        st.close();
    }
}
