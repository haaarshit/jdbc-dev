# JDBC (Java Database Connectivity)

**JDBC is a Java API for connecting and interacting with databases. JDBC Drivers are software components that provides necessary functionality to connect java application with databases**

### There are four type of JDBC Drivers

- Type 1: JDBC-ODBC Driver
- Type 2: Native-API partly java Driver (partially java driver)
- Type 3: Network protocol Pure Java Driver (fully java driver)
- Type 4: Thin Driver(also known as Direct to Database Pure Java Driver || fully java driver)

Type 1: Oldest driver written in c. Out dated. Has some performance issues.

Type 2: Provided by Database vendors. They provide APIs to connect with their databases. 

Type 3: Written in Java, far better than type1 and type2 in the term of performance.
             
Type 4: Written in Java. And is the most efficient driver.

### JDBC Components
In addition to the JDBC drivers, there are several other component that make up JDBC API, including:
 
- DriverManager Class
- Connection interface
- Statement and PreparedStatement interfaces
- ResultSet interface

These components work together to provide a powerful and flexible API for working
with database in Java.

#### Program flow
Connect ide with database using connector(jar files) -> Load Drivers -> Create Connection -> Create Statement -> Execute Query
