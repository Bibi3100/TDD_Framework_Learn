package config.databases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectDatabase {
    //Secret.properties file
    //jdbc drive
    //jdbc url
    //jdbc userName
    //jdbc password
    // MySQl query
    public static Connection connection=null;
    public static Statement statement= null;
    public static PreparedStatement preparedStatement=null;
    public static ResultSet resultSet =null;
    public static void main(String[] args) {
        //Properties prop=loadProperties("configProperty/Scret.properties");
        //System.out.println(properties2.getProperty("MySql_User_Name"));
        //  getDatabaseConnection();
        // closeDatabaseConnection();
         String query="select*from studentsinfo;";
        //directDatabaseQueryExecution(query,"fiestName");

      //  readDatabaseTableColumn("studentsinfo","address");
        //insertDataFromStringToTable("204","employees","employee_id");
        //insertDataFromStringToTable("Rabu","employees","employee_firstName");
      //  insertDataFromStringToTableMultipleColum("205","Rabu","employees","employee_id", "employee_firstName");
        List<String>listData1=new ArrayList<>();
        listData1.add("101");
        listData1.add("102");

        List<String>listData2=new ArrayList<>();
        listData2.add("Jhon");
        listData2.add("Jack2");


        //insertDataFromMultipleColumFromListString("employees","employee_id","employee_firstName",listData1,listData2);
        readUserProfileFromSLTable("movie");
       // System.out.println( readUserProfileFromSLTable("movie"));
    }

    //load properties file
    public static Properties loadProperties(String filePath) {
        Properties properties= new Properties();
        try {
            InputStream inputStream= new FileInputStream(filePath);
            properties.load(inputStream);
        } catch ( IOException e) {
            System.out.println("Exception-- IOException: "+e.getMessage());
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     * this method develop connection with database
     * @return
     */
    public static Connection getDatabaseConnection(){
        Properties prop=loadProperties("configProperty/Scret.properties");
        String driverClass=prop.getProperty("MYSQLJDBC.DRIVER");
        String url= prop.getProperty("MYSQLJDBC.URL");
        String user= prop.getProperty("MySql_User_Name");
        String password= prop.getProperty("MySQL_Password");
        try {
            Class.forName(driverClass);
            connection= DriverManager.getConnection(url,user,password);
            System.out.println("Database connection successfully");
        } catch (ClassNotFoundException |SQLException e) {
            System.out.println("Exception-- classNotFoundException| SQLException: "+e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * this method close database
     */
    public static void closeDatabaseConnection(){
        try {
            assert resultSet!=null;
            resultSet.close();
            assert statement !=null;
            statement.close();
            assert connection!=null;
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection closed."+e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static List<String>getResultSetData(ResultSet resultSet,String columName) throws SQLException {
        List<String>dataList= new ArrayList<>();
        while(resultSet.next()){
            String items=resultSet.getNString(columName);
            dataList.add(items);
        }
        return dataList;
    }
    public static List<String>directDatabaseQueryExecution( String  query, String columName){
        List<String>data=new ArrayList<>();
       ConnectDatabase.getDatabaseConnection(); //first connect databse
        try {
            statement= connection.createStatement();
            resultSet =statement.executeQuery(query);
            data= getResultSetData(resultSet,columName);
            System.out.println("Data value: "+data);
            for (String dt:data) {
                System.out.println(dt);

            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: "+e.getMessage());
            //throw new RuntimeException(e);
        }finally {
            closeDatabaseConnection();
        }
        return data;
    }
    public static  List<String > readDatabaseTableColumn(String tableName, String columName){
        List<String>data=new ArrayList<>();

        ConnectDatabase.getDatabaseConnection(); //first connect databse
        try {
            statement= connection.createStatement();
            String query="select* from "+tableName;
            //String query= "select*from studentsinfo;";
            resultSet =statement.executeQuery(query);
            data= getResultSetData(resultSet,columName);
            System.out.println("Data value: "+data);
           // for (String dt:data) {
             //   System.out.println(dt);

            //}
        } catch (SQLException e) {
            System.out.println("SQL Exception: "+e.getMessage());
            //throw new RuntimeException(e);
        }finally {
           closeDatabaseConnection();
        }
        return data;
    }

public static void insertDataFromStringToTable(String arrayDate,String tableName, String columName){
        //first get connection
        getDatabaseConnection();
        //INSERT INTO `demo-db`.`employees` (`employee_id`, `employee_firstName`, `employee_lastName`) VALUES ('202', 'Jack', 'Cohen');
    try {
        String query="Insert into " + tableName+"("+columName+") values(?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,arrayDate);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    public static void insertDataFromStringToTableMultipleColum(String arrayDate1,String arrayDate2,String tableName, String columName1,String columName2){
        //first get connection
        getDatabaseConnection();
        try {
            String query="Insert into " + tableName+"("+columName1+","+columName2+") values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,arrayDate1);
            preparedStatement.setString(2,arrayDate2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void insertDataFromMultipleColumFromListString(String tableName,String columName1,String columName2,List<String>data1,List<String>data2){
        //first get connection
        getDatabaseConnection();
        try {
            String query="Insert into " + tableName+"("+columName1+","+columName2+") values(?,?)";
            preparedStatement = connection.prepareStatement(query);
           for(int i=0;i<data1.size();i++){
               preparedStatement.setString(1,data1.get(i));
               preparedStatement.setString(2,data2.get(i));
               preparedStatement.executeUpdate();
           }
        } catch (SQLException e) {
            System.out.println("SQLException: "+e.getMessage());
        }
    }

    public static List<Movie>readUserProfileFromSLTable(String tableName){
        List<Movie>movieList=new ArrayList<>();
        Movie movie;
        Connection connection= getDatabaseConnection();
        try{
            Statement statement=connection.createStatement();
            String query="select* from "+tableName;
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                int id=resultSet.getInt("moviId");
                String tile=resultSet.getString("moviTitle");
                int releaseYear=resultSet.getInt("releaseYear");
                String genre= resultSet.getString("movieGenre");
                String rating= resultSet.getString("movieRating");
                System.out.format("%s,%s,%s,%s,%s\n",id,tile,releaseYear,genre,rating);

                movie=new Movie(id,tile,releaseYear,genre,rating);
                movieList.add(movie);
            }
           // closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }


}