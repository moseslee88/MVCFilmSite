package com.skilldistillery.film.data;

import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmDaoDbImpl implements FilmDao{
    private static String url = "jdbc:mysql://localhost:3306/sdvid";
    private String user = "student";
    private String pass = "student";
    private List<Film> films = new ArrayList<>();
    
    
    @Override
    public String getFilmTitleById(int id) {
      String title = null;
      try {
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "SELECT title FROM film WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
          title = rs.getString(1);
        }
        rs.close();
        stmt.close();
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return title;
    }

    public FilmDaoDbImpl() {
          try {
            Class.forName("com.mysql.jdbc.Driver");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error loading MySQL Driver!!!");
          }
        }

    @Override
    public List<Film> getFilmTitleByKeyword(String key) {
        films.clear();
        List<Actor> cast = new ArrayList<>();
        String title= null;
        String rating= null;
        String description= null;
        int length;  
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT title, rating, description, length, id FROM film WHERE title LIKE?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + key +"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
              title = rs.getString(1);
              rating    = rs.getString(2);
              description = rs.getString(3);
              length = rs.getInt(4);
              System.out.println(rs.getInt(5));
              int filmid = rs.getInt(5);
              cast = getActorsByID(filmid);
              Film film = new Film(title, rating, description, length, cast);
              films.add(film);
            }
            rs.close();
            stmt.close();
            conn.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
          return films;
        }
    public List<Actor> getActorsByID(int filmid) {
        
        List<Actor> cast = new ArrayList<>();
        String firstname=null;
        String lastname= null;
        int id;
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            //conn.setAutoCommit(false); // Start transaction
            String sql = "SELECT id, first_name, last_name FROM actor WHERE id IN ( SELECT actor_id FROM film_actor WHERE film_id = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            //  PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);    use for wknd proj
                                     //MUST DELETE from film_actor before you remove actor from the database (all dependencies for actor must be deleted first) 
            stmt.setInt(1,filmid);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt(1);
                firstname = rs.getString(2);
                lastname = rs.getString(3);
                Actor acter = new Actor(id, firstname, lastname);
                		cast.add(acter);
            }
            rs.close();
            stmt.close();
            conn.close();
          
        } catch(SQLException sqle)  {
        	           sqle.printStackTrace();
        }
        return cast;  
    }
    
    
    
    
}

















/*package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoDbImpl implements FilmDao {
	private static String url = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";
	private List<Film> titles=new ArrayList<>();
	
	//constructor for my FilmDaoDbImpl
	public FilmDaoDbImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public String getFilmTitleById(int id) {
		  String title = null;
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    String sql = "SELECT title FROM film WHERE id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, id);
		    ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {   //NOTICE THE IF instead of the WHILE here in case id is invalid or larger than 1000 
		      title = rs.getString(1);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return title;   
		
	}

	@Override
	public List<Film> getFilmTitleByKeyword(String key) {
		  titles.clear();
		  String title = null;
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    //String sql = "SELECT title FROM film WHERE title LIKE '%" + key + "%'";
		    //String sql = "SELECT title FROM film WHERE title LIKE ?";
		    String sql = "SELECT id, first_name, last_name FROM actor WHERE id IN ( SELECT actor_id FROM film_actor WHERE film_id = ?)";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setString(1, "%" + key +"%");  
		    ResultSet rs = stmt.executeQuery();
		    int id = 1;
		    while (rs.next()) {   //NOTICE THE IF instead of the WHILE here in case id is invalid or larger than 1000 
		   //	title = id + ". " + rs.getString(1); //this is the way to get the number before each element
		    	title = rs.getString(1);
		      titles.add(title);
		      id++;
		    }
		    System.out.println(id + " results");
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return titles; 
	}
	
	
	
	// SELECT id, first_name, last_name FROM actor WHERE id IN ( SELECT actor_id FROM
	 // film_actor WHERE film_id = ?)


}      *******/
