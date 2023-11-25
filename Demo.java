import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Scanner;

public class Demo {

    // username
    private static String username = "postgres";
    // password
    private static String password = "root";

    // url for postgresql
    private static String url = "jdbc:postgresql://localhost:5432/testdb";
    // query

        public static void main(String[] args)  {

            try{
                // connect with db
                Connection con = DriverManager.getConnection(url, username, password);
                // using create Statement
                // create statement
                Statement sts = con.createStatement();


                // Find Query
                // execute sql query and store result in result set
//                String sqlQuery1 = "select name,price from product";

//                ResultSet res =sts.executeQuery(sqlQuery1);
                // move pointer from one row to another
//                while (res.next()){
//                  String name = res.getString(1);
//                  int value = res.getInt(2);
//                  System.out.println("Name of product is "+name+" value of "+value);
//                }

                int rowsAffected;
                // Insert query
//                String sqlQuery2 = String.format("INSERT INTO product(id,name,price) VALUES(%d,'%s',%d)",7,"XBox",43999);
//                rowsAffected =sts.executeUpdate(sqlQuery2);
//                System.out.println("rows affected "+rowsAffected);

                // Update query
//                String sqlQuery3 = String.format("UPDATE product SET price = %d Where name = 'Play Station'",54000);
//                 rowsAffected =sts.executeUpdate(sqlQuery3);
//                System.out.println("rows affected "+rowsAffected);

                // Delete query
//                String sqlQuery4 = "DELETE  from product Where name = 'XBox'";
//                rowsAffected =sts.executeUpdate(sqlQuery4);
//                System.out.println("rows affected "+rowsAffected);

            }
            catch (SQLException e){
                System.out.println(e);
            }

            // Prepared statement
//            try{
//                Connection con = DriverManager.getConnection(url, username, password);
////                 using prepare statement
////                 prepare statement
//                String insertQuery = "INSERT INTO product(id,name,price) VALUES(?,?,?)";
//                PreparedStatement psts = con.prepareStatement(insertQuery);
//                psts.setInt(1,9);
//                psts.setString(2,"Laptop");
//                psts.setInt(3,48999);
//                int affectedRows = psts.executeUpdate();
//                System.out.println("Rows affected "+affectedRows);


//                String retriveQuery = "SELECT * FROM product WHERE id = ?";
//                psts = con.prepareStatement(retriveQuery);
//                psts.setInt(1,9);
//                ResultSet res = psts.executeQuery();
//                res.next();
//                System.out.printf("Item %s has value %d",res.getString(2),res.getInt(3));

//                String updateQuery = "UPDATE product SET price = ? WHERE id = ?";
//                psts  = con.prepareStatement(updateQuery);
//                psts.setInt(1,50000);
//                psts.setInt(2,6);
//                int rowsAffected  = psts.executeUpdate();
//                System.out.println("Rows affected "+rowsAffected);


//                String findQuery = "SELECT name FROM product WHERE id = ?";
//                String findQuery = "SELECT price FROM product WHERE id = ?";
//                psts = con.prepareStatement(findQuery);
//                psts.setInt(1,8);
//                ResultSet res = psts.executeQuery();
//                res.next();
//                System.out.println("Price : "+res.getString(1));

//                String deleteQuery = "DELETE FROM product WHERE id = ?";
//                psts = con.prepareStatement(deleteQuery);
//                psts.setInt(1,9);
//                int rows = psts.executeUpdate();
//                System.out.println("Rowes affected "+rows);

//            }
//            catch (SQLException e){
//                System.out.println(e);
//            }

            // Batch Processing
            try(Connection con = DriverManager.getConnection(url,username,password)){

                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

                // prepare statement
                String query = "INSERT INTO product(id,name,price) VALUES(?,?,?)";
                PreparedStatement sts = con.prepareStatement(query);

                System.out.println("Enter the current  id of the table");
                int uniqueid = Integer.parseInt(bf.readLine()) + 1;

                System.out.println("Enter name and price");
                while(true){
                    String name = bf.readLine();
                    int price = Integer.parseInt(bf.readLine());
                    System.out.print("More data?? Y/N");
                    String choice = bf.readLine();
                    sts.setInt(1,uniqueid);
                    sts.setString(2,name);
                    sts.setInt(3,price);
                    System.out.println("query "+sts);
                    sts.addBatch();
                    uniqueid++;
                    if(choice.toUpperCase().equals("N")) break;
                }
                int[] arr = sts.executeBatch();
                System.out.println("Output for all query "+ Arrays.toString(arr));

            }
            catch (SQLException e){
                System.out.println(e);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }

    }
}
/*
 */