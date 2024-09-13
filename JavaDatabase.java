/*
This is for creating the database for the project 
 */
//package investmentportfolio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JavaDatabase
{
  private String dbName;
  private Connection dbConn; 
  private ArrayList<ArrayList<String>> data;
  
  public JavaDatabase() // creating database
  {
    dbName = "";
    dbConn = null;
    data = null;
  }
  
  public JavaDatabase(String dbName) //
  {
    setDbName(dbName);
    setDbConn();
    data = null;
  }
  
  public String getDbName()
  {
    return dbName;
  }
  
  public void setDbName(String dbName)
  {
    this.dbName = dbName;
  }
  
  public Connection getDbConn()
  {
    return dbConn;
  }
  
  public void setDbConn()
  {
    // path to db 
    String connectionURL = "jdbc:derby:" + this.dbName;
    this.dbConn = null; // avoid warning from compiler 
    try
    {
      // find the driver 
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      // connect to database 
      this.dbConn = DriverManager.getConnection(connectionURL);
    }
    catch (ClassNotFoundException ex) // driver issue 
    {
      System.out.println("Driver not found, check Library");
    }
    catch (SQLException se) // connection issue 
    {
      System.out.println("SQL Connection error!");
    }
  }
  
  public ArrayList<ArrayList<String>> getData (String tableName, String[]
    tableHeaders)
  {
    int columnCount = tableHeaders.length;
    Statement s = null; // blank statement for SQL 
    ResultSet rs = null; // pointer to begging of data
    String dbQuery = "SELECT * FROM " + tableName; // to read data
    this.data = new ArrayList<>(); // construct data 
    
    // read the data 
    try
    {
      // send the query and recieve data
      s = this.dbConn.createStatement(); // create s 
      rs = s.executeQuery(dbQuery); // put the query in s and execute

      // read the data using rs and store in the ArrayList data
      while (rs.next()) // check if there is next, move there 
      // if there isn't, exit the loop
      {
        // row object to hold one row data 
        ArrayList<String> row = new ArrayList<>();
        // go through the row and read each cell 
        for (int i = 0; i < columnCount; i++)
        {
          // read cell i 
          // example: String cell = rs.getString("Name");
          // reads the cell in specified column name 
          // tableHeader = {"Name", "Age", "GradeLevel"}
          String cell = rs.getString(tableHeaders[i]);
          // add the cell to the row 
          // example row.add("Vinny");
          row.add(cell);
        }
        // add the row to the data 
        // example: data.add "Vinney", 15, 9
        this.data.add(row);
      } // end while
    } // end try
    catch (SQLException se)
    {
      System.out.println("SQL Error: Not able to get data");
    }
    return data;
  } // end method 
  
  public void setData(ArrayList<ArrayList<String>> data)
  {
    this.data = data;
  }
  
  public void closeDbConn()
  {
    try
    {
      this.dbConn.close();
    }
    catch (Exception err)
    {
      System.out.println("DB closing error.");
    }
  }
  
  public void createDb(String newDbName)
  {
    setDbName(newDbName); //asign new dbname to dbName
    // create a db if not existing 
    String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
    this.dbConn = null;
    try
    {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      this.dbConn = DriverManager.getConnection(connectionURL);
      System.out.println("New Database " + this.dbName + " created!");
    }
    catch (ClassNotFoundException ex)
    {
      System.out.println("Driver not found, check Library");
    }
    catch (SQLException se)
    {
      System.out.println("SQL Connection error, Db was not created!");
    }
  }
  
  public void createTable(String newTable, String dbName)
  {
    System.out.println(newTable); // for debugging sql statement 
    setDbName(dbName); // set the dbName for connection
    setDbConn(); // set the connection for above dbName
    Statement s; // to use from sql statement 
    try
    {
      // create the sql statement for the connection 
      s = this.dbConn.createStatement();
      // place the sql newTable into the statement s and execute 
      s.execute(newTable);
      System.out.println("New table created!");
      this.dbConn.close(); // done, close the connection 
    }
    catch (SQLException se)
    {
      System.out.println("Error creating table " + newTable);
    }
  }
  
// to convert a 2d arraylist to 2d array:
  public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
  {
    // a 2d array to recieve values from data
    Object[][] dataList = null;
    if (data.size() == 0) // empty data (from database)
    {
      dataList = new Object[0][0]; // empty dataList 
    }
    else // now we have data 
    {
      // get a row and use it to get the number of columns 
      int columnCount = data.get(0).size(); 
      // construct dataList based on size of data 
      dataList = new Object[data.size()][columnCount];
      // loop to read each cell
      for (int r = 0; r < data.size(); r++)
      {
        // get a row 
        ArrayList<String> row = data.get(r);
        // loop to read each member of the row 
        for (int c = 0; c < columnCount; c++)
        {
          // read the cell 
          dataList[r][c] = row.get(c);
        }
      }
    }
    return dataList;
  }
  
  public static void main(String[] args)
  {
    // db info
    String dbName = "Investment Portfolio";
    String tableName = "RebalanceMoney";
    String[] columnNames = {"Stock", "Bond", "Crypto", "Fixed", "Constant"};

    String dbQuery = "INSERT INTO RebalanceMoney VALUES (?,?,?,?,?)";

    // data to be entered into database
    double stock = 0;
    double bond = 0;
    double crypto = 0;
    double fixed = 0;
    int constant = 0;

    // connect to db
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();

    try
    {
      // prepare statement 
      PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
      // enter data into query
      ps.setDouble(1, stock);
      ps.setDouble(2, bond);
      ps.setDouble(3, crypto);
      ps.setDouble(4, fixed);
      ps.setInt(5, constant);
      // execute the query 
      ps.executeUpdate();
      System.out.println("Data inserted successfully");
    }
    catch (SQLException se)
    {
      System.out.println("Error inserting data");
    }
    // read the data from database
    ArrayList<ArrayList<String>> myData = objDb.getData(tableName, columnNames);
    System.out.println(myData);
  }
}
