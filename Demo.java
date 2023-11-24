import java.sql.*;

public class Demo {

    // username
    private static String username = "postgres";
    // password
    private static String password = "root";

    // url for postgresql
    private static String url = "jdbc:postgresql://localhost:5432/testdb";
    // query
    private static String sqlQuery = "select name,price from product where id=4";
        public static void main(String[] args)  {
            try{
                // connect with db
                Connection con = DriverManager.getConnection(url, username, password);
                // create statement
                Statement sts = con.createStatement();
                // execute sql query ans store result in result set
                ResultSet res =sts.executeQuery(sqlQuery);
                // move pointer to first row
                res.next();
                // get data from colum 1 at row 0
                String name = res.getString(1);
                System.out.println("Name is "+name);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
    }
}