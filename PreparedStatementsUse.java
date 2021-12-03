import java.sql.*;
public class PreparedStatementsUse {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employees_new","root","");
        String userName = "Rishabh";
        int userId = 12;
        String query = "insert into Details values (?,?)";
        PreparedStatement st = con.prepareStatement(query);
        try(st;con;) {
            st.setInt(1, userId);
            st.setString(2, userName);
            int count = st.executeUpdate();
            System.out.println("no.of rows updated:" + count);
            ResultSet rs = st.executeQuery("select * from Details");
            rs.next();
            System.out.println(rs.getInt(1) + "  " + rs.getString(2));
        }
    }
}
