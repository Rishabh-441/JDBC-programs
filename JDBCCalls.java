import javax.swing.plaf.nimbus.State;
/*
Develop a jdbc program containing main method, which should instantiate a class called DAOClass, which should contain methods called insert, delete, modify and display. Description of what each of these methods are expected to do is given below. Necessary details required for executing these methods, are passed from command line argument. For e.g. If the name of the class containing the main method is JDBCCalls, then if you want to insert a record, you will execute this class as java JDBCCalls 1 101 “Ajit” “IV” “20-Nov-2001” 4000
Where 1 is the option for inserting the record and all other details are the values for the columns in each row of the student table. The structure of student table is given below. Similarly, for deleting a record, you have to execute the code as
java JDBCCalls 2 101
where 2 is the option for deleting a record and 101 is the rollno of the student, whose record has to be deleted.
For modifying a record, you will use
java JDBCCalls 3 101 4500, where 3 is the option for modifying a record and the 4500 is the new fee which needs to replace the old fee value.
For Displaying records, if the main class is executed as follows
java JDBCCalls 4 101
it should display only one record, that of the student with roll no. 101. 4 option is for displaying the record.
If the main class is executed as
java JDBCCalls 4 (without specifying the rollno.), it means that details of all the students should be displayed.
 */
import java.sql.*;

class DAO {
    public static void insert(PreparedStatement p, int sno, String name, String cls, String d, int fees) throws SQLException {
        p.setInt(1,sno);
        p.setString(2,name);
        p.setString(3,cls);
        p.setString(4,d);
        p.setInt(5,fees);
        p.executeUpdate();
    }
    public static void delete(PreparedStatement p, int sno) throws SQLException {
        p.setInt(1,sno);
        p.executeUpdate();
    }
    public static void modify(PreparedStatement p, int sno, int fees) throws SQLException {
        p.setInt(1,sno);
        p.setInt(2,fees);
    }
    public static void display(Statement st, int sno) throws SQLException {
        ResultSet rs = st.executeQuery("select * from details");
        while(rs.next()){
            if (rs.getInt(1) == sno){
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getInt(5));
            }
        }
    }
}
public class JDBCCalls{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dao","root","");
        int check = Integer.parseInt(args[0]);
        PreparedStatement p = null;
        if (check == 1){
            String query = "insert into details values (?,?,?,?,?)";
            p = con.prepareStatement(query);
            DAO.insert(p, Integer.parseInt(args[1]), args[2], args[3], args[4], Integer.parseInt(args[5]));
        }
        else if (check == 2){
            int del = Integer.parseInt(args[1]);
            p = con.prepareStatement("delete from details where sno = ?");
            DAO.delete(p,del);
        }
        else if (check == 3){
            int sno = Integer.parseInt(args[1]);
            int fees = Integer.parseInt(args[2]);
            PreparedStatement st = con.prepareStatement("update details set fees = ? where sno = ?");
            DAO.modify(st, sno, fees);
        }
        else if (check == 4){
            Statement st = con.createStatement();
            int d = Integer.parseInt(args[1]);
            DAO.display(st, d);
        }
        else{
            System.out.println("Invalid entry!!");
        }
    }
}
