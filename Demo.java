import javax.swing.tree.ExpandVetoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Scanner;

public class Demo {

    // username
    private static String username = "postgres";
    // password
    private static String password = "root";

    // url for postgresql
//    private static String url = "jdbc:postgresql://localhost:5432/testdb";
    private static String url = "jdbc:postgresql://localhost:5432/transactions";
    // query

        public static void main(String[] args)  {

                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


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
//            try(Connection con = DriverManager.getConnection(url,username,password)){
//
//
//                // prepare statement
//                String query = "INSERT INTO product(id,name,price) VALUES(?,?,?)";
//                PreparedStatement sts = con.prepareStatement(query);
//
//                System.out.println("Enter the current  id of the table");
//                int uniqueid = Integer.parseInt(bf.readLine()) + 1;
//
//                System.out.println("Enter name and price");
//                while(true){
//                    String name = bf.readLine();
//                    int price = Integer.parseInt(bf.readLine());
//                    System.out.print("More data?? Y/N");
//                    String choice = bf.readLine();
//                    sts.setInt(1,uniqueid);
//                    sts.setString(2,name);
//                    sts.setInt(3,price);
//                    System.out.println("query "+sts);
//                    sts.addBatch();
//                    uniqueid++;
//                    if(choice.toUpperCase().equals("N")) break;
//                }
//                int[] arr = sts.executeBatch();
//                System.out.println("Output for all query "+ Arrays.toString(arr));
//
//            }
//            catch (SQLException e){
//                System.out.println(e);
//            }
//            catch (IOException e){
//                System.out.println(e.getMessage());
//            }

            // Transaction handling


            // transactions
            try(Connection con = DriverManager.getConnection(url,username,password)){
                // disable auto commit'
                con.setAutoCommit(false);

                // user inputs
                System.out.println("enter amount to debit");
                double amount = Double.parseDouble(bf.readLine());
                System.out.println("Enter debit account number");
                int debit_acc_no = Integer.parseInt(bf.readLine());
                System.out.println("Enter debit account number");
                int credit_acc_no = Integer.parseInt(bf.readLine());

                String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_no = ?";
                String credit_query = "UPDATE accounts SET balance = balance +  ? WHERE account_no = ?";
                PreparedStatement debit_psts = con.prepareStatement(debit_query);
                PreparedStatement credit_psts = con.prepareStatement(credit_query);

                // if debit account does not have sufficient balance then return else proceed
                if(isSuffcient(con,debit_acc_no,amount) == false){
                    System.out.println("Insufficient amount");
                    return;
                }
                // if debit account has enough balance then commit the connection
                con.setAutoCommit(true);

                debit_psts.setDouble(1,amount);
                debit_psts.setInt(2,debit_acc_no);

                credit_psts.setDouble(1,amount);
                credit_psts.setInt(2,credit_acc_no);

                int rowsAffected = debit_psts.executeUpdate();
                int affectedRows  = credit_psts.executeUpdate();
                if(rowsAffected == 1 && affectedRows == 1) {
                    System.out.println("Transaction Successful");
                }

            }
            catch (SQLException e){
                System.out.println(e);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }


    }
    static boolean isSuffcient(Connection connection,int account_no,double amount) throws  SQLException{
            if(amount <= 0) return false;

            String query = "SELECT balance from accounts WHERE account_no = ?";
            PreparedStatement psts = connection.prepareStatement(query);
            psts.setInt(1,account_no);
            ResultSet result = psts.executeQuery();
            result.next();
            if(result.getDouble(1) < amount ) return false;
            return true;

    }
}