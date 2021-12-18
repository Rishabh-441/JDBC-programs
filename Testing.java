import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

class CheckReg{
    public static boolean check(String str){
        return Pattern.matches("[a-zA-z]*(j|J)", str);
    }
}
class Operate{
    public static void insert(PreparedStatement p, ArrayList<Student> s) throws SQLException {
        for (var x: s) {
            if (CheckReg.check(x.name)){
                p.setInt(1,x.roll);
                p.setString(2,x.name);
                p.setInt(3,x.marks);
                p.executeUpdate();
            }
        }
    }
    public static void delete(PreparedStatement p, int roll) throws SQLException {
        p.setInt(1,roll);
        p.executeUpdate();
    }
    public static void select(Statement st, int roll) throws SQLException {
       ResultSet rs = st.executeQuery("select * from student");
       while (rs.next()){
           if (rs.getInt(1) == roll){
               System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3));
               break;
           }
       }
    }
}
class Student{
    int roll = 0;
    String name = null;
    int marks = 0;

    public Student(int roll, String name, int marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }
}
public class Testing {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ArrayList<Student> arr = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("----> enter 1 to insert");
            System.out.println("----> enter 2 to delete");
            System.out.println("----> enter 3 to select");
            System.out.println("----> enter 4 to exit");
            int m = sc.nextInt();
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atest", "root", "")){
                if (m == 1) {
                    System.out.println("enter the number of entries: ");

                    int n = sc.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.println("Roll no : ");
                        int roll = sc.nextInt();

                        System.out.println("Name : ");
                        String name = sc.next();

                        System.out.println("Marks : ");
                        int marks = sc.nextInt();

                        Student s = new Student(roll, name, marks);
                        arr.add(s);
                    }
                    PreparedStatement p = con.prepareStatement("insert into student values (?,?,?)");
                    Operate.insert(p,arr);
                }
                else if (m == 2) {
                    System.out.println("enter the roll no to delete: ");
                    PreparedStatement p = con.prepareStatement("delete from student where roll = ?");
                    Operate.delete(p, sc.nextInt());
                    p.close();
                }
                else if (m == 3) {
                    try(Statement st = con.createStatement()){
                        System.out.println("enter the roll to find: ");
                        int r = sc.nextInt();
                        Operate.select(st, r);
                    }
                }
                else if (m == 4){
                    break;
                }
                else {
                    System.out.println("invalid entry!!");
                }
            }
        }
    }
}
