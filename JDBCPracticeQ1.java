import java.sql.*;

public class JDBCPracticeQ1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/King","root","");
            System.out.println("Connection established successfully");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Pens");
            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }
            rs.close();
            st.close();
        }
        catch (Exception e) {
            System.out.println("Connection could not be established successfully");
            System.out.println(e + " " + e.getMessage());
        }

    }
}
