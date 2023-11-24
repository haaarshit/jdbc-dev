import java.sql.*;

public class Demo {

    // username
    private static String username = "postgres";
    // password
    private static String password = "root";

    // url for postgresql
    private static String url = "jdbc:postgresql://localhost:5432/testdb";
    // query
    private static String sqlQuery = "select name,price from product";
        public static void main(String[] args)  {
            try{
                // connect with db
                Connection con = DriverManager.getConnection(url, username, password);
                // create statement
                Statement sts = con.createStatement();
                // execute sql query and store result in result set
                ResultSet res =sts.executeQuery(sqlQuery);
                // move pointer from one row to another
                while (res.next()){
                  String name = res.getString(1);
                  int value = res.getInt(2);
                  System.out.println("Name of product is "+name+" value of "+value);
                }

            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
    }
}