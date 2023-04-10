package config.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnectionDirectly {
    public static void main(String[] args) {
        DatabaseConnectionDirectly.directdatabaseConnection();

    }
    public static void directdatabaseConnection(){
        //Database connection: JDBC connection
        String url="jdbc:mysql://Localhost:3306/demo-db?serverTimezone=UTC&useSSL=false";
        String user="root";
        String password="Baruch@9701";
        String driver="com.mysql.cj.jdbc.Driver";
        //Create connection
        Connection connection=null;
        Statement statement = null;
        /**
         * Movie class s using to insert data
         */
        Movie titanic= new Movie(10,"Titanic",1997,"romantic","PG30");
        Movie topGun1986= new Movie(11,"topGun1986",1990,"horror","PG31");
        Movie noTimeToDie= new Movie(12,"Titanic",1967,"romantic","PG32");
        Movie trueSpirit= new Movie(13,"noTimeToDie",2000,"action","PG33");
        Movie catchTheCar= new Movie(14,"catchTheCar",1967,"romantic","PG34");
        System.out.println(titanic.getMovieTitle());
        //ArrayList
        ArrayList<Movie> movies= new ArrayList<>();
        movies.add(titanic);
        movies.add(noTimeToDie);
        movies.add(trueSpirit);
        movies.add(catchTheCar);
        movies.add(topGun1986);

        for (Movie mo:movies) {
            System.out.println(mo);
        }
        
        
        try {
            Class.forName(driver);
        }catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }try {
            connection = DriverManager.getConnection(url, user, password);
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }try {
            statement = connection.createStatement();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("DB connection successful");
        //SQL query:
        String query= "SELECT*FROM studentsInfo;";
        try{
            statement.execute(query);
            System.out.println(statement.execute(query));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        try{
            assert statement!=null;
            statement.close();
            connection.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        //return connection;
    }

}
