package com.skilldistillery.film.data;

import java.util.List;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmDaoDbImpl implements FilmDao {
	private static String url = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";
	private List<Film> films = new ArrayList<>();
	private Film film; 

	@Override
	public String getFilmTitleById(int id) {
		String title = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "SELECT title FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				title = rs.getString(1);
			}
			rs.close();
			stmt.close();
			conn.commit();
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
		String title = null;
		String rating = null;
		String description = null;
		String specialfeatures = null;
		int length;
		int releaseyear;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			//String sql = "SELECT title, rating, description, length, id FROM film WHERE title LIKE?";
			String sql = "SELECT id, title, description, release_year, rating, length, special_features FROM film WHERE title LIKE?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + key + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmid = rs.getInt(1);
				title = rs.getString(2);
				description = rs.getString(3);
				releaseyear = rs.getInt(4);
				rating = rs.getString(5);
				length = rs.getInt(6);
				specialfeatures = rs.getString(7);
				System.out.println(rs.getInt(1));
				cast = getActorsByID(filmid);
				Film film = new Film(filmid, title, description, releaseyear, rating, length, specialfeatures, cast);
				//Film film = new Film(title, rating, description, length, cast);
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
		String firstname = null;
		String lastname = null;
		int id;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			// conn.setAutoCommit(false); // Start transaction
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id IN ( SELECT actor_id FROM film_actor WHERE film_id = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmid);
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

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return cast;
	}

	/**
	 * here I implement an addFilm() method that takes a Film object and insert it
	 * into the database. It should return the Film object, or null if the insert
	 * fails
	 **/

	// PreparedStatement stmt = conn.prepareStatement(sql,
	// Statement.RETURN_GENERATED_KEYS); use for upcoming weekend project.
	// MUST DELETE from film_actor before you remove actor from the database (all
	// dependencies for actor must be deleted first)
	@Override
	public void addFilm(Film film) {
		Connection conn = null; // here I instantiated a Connection object

		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO film (title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_features, language_id) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseyear());
			stmt.setInt(4, film.getRentalduration());
			stmt.setDouble(5, film.getRentalrate());
			//stmt.setDouble(5, 8.99);
			stmt.setInt(6, film.getLength());
			stmt.setDouble(7, film.getReplacementcost());
			stmt.setString(8, film.getRating());
			stmt.setString(9, film.getSpecialfeatures());
			stmt.setInt(10, 1);
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					film.setId(keys.getInt(1));
					films.add(film);  //load the Array List with films
				} else {
					film = null;
				}
				System.out.println(film);

				stmt.close();
				conn.commit(); //COMMIT TRANSACTION --this line of code ensures this film object gets added to mySQL database
				conn.close();
			  }
		}catch (SQLException sqle) {
				    sqle.printStackTrace();
				    if (conn != null) {
				      try { conn.rollback(); }
				      catch (SQLException sqle2) {
				        System.err.println("Error trying to rollback");
				      }
				    }
				    throw new RuntimeException("Error inserting film " + film);
		}
				}

	@Override
	public int deleteFilm(int id) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false); // START TRANSACTION
            String sql4 = "select id from inventory_item where film_id = ?";
            PreparedStatement stmt4 = conn.prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS);
            stmt4.setInt(1, id);
            stmt4.executeQuery();
            ResultSet keys = stmt4.executeQuery();
            while (keys.next()) {
                System.out.println("in first while");
                String sql5 = "select id from rental where inventory_id = ?";
                PreparedStatement stmt5 = conn.prepareStatement(sql5, Statement.RETURN_GENERATED_KEYS);
                stmt5.setInt(1, keys.getInt(1));
                stmt5.executeQuery();
                ResultSet keys2 = stmt5.getGeneratedKeys();
                while (keys2.next()) {
                    String sql6 = "Delete from payment where rental_id = ?";
                    PreparedStatement stmt6 = conn.prepareStatement(sql6, Statement.RETURN_GENERATED_KEYS);
                    stmt6.setInt(1, keys2.getInt(1));
                    stmt6.execute();
                }
                keys2.close();
                String sql7 = "delete from rental where inventory_id = ?";
                System.out.println(sql7 + " " + keys.getInt(1));
                PreparedStatement stmt7 = conn.prepareStatement(sql7, Statement.RETURN_GENERATED_KEYS);
                stmt7.setInt(1, keys.getInt(1));
                stmt7.execute();
            }
            keys.close();
            
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"); //TEST to see if reached code
            String sql3 = "Delete From inventory_item WHERE  film_id = ?";
            PreparedStatement stmt3 = conn.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
            stmt3.setInt(1, id);
            stmt3.execute();
            String sql8 = "Delete From film_category WHERE  film_id = ?";
            PreparedStatement stmt8 = conn.prepareStatement(sql8, Statement.RETURN_GENERATED_KEYS);
            stmt8.setInt(1, id);
            stmt8.execute();
            String sql2 = "Delete From Film_actor WHERE  film_id = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            stmt2.setInt(1, id);
            stmt2.execute();
            String sql = "Delete From Film WHERE  id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.execute();

            stmt.close();
            conn.commit(); // COMMIT TRANSACTION
            conn.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException sqle2) {
                    System.err.println("Error trying to rollback");
                }
            }
            throw new RuntimeException("Error could not delete film with id " + id);
        }
        return id;
    }

	@Override
	public boolean updateFilm(Film film) {
            Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE film SET title=?, description=?, release_year=?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ?, language_id = ? WHERE id=?";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseyear());
			stmt.setInt(4, film.getRentalduration());
			// stmt.setDouble(5, film.getRentalrate());
			stmt.setDouble(5, 8.99);  //TEST to see if film line updates and hardcode film's rental_rate to 8.99
			stmt.setInt(6, film.getLength());
			stmt.setDouble(7, film.getReplacementcost());
			stmt.setString(8, film.getRating());
			stmt.setString(9, film.getSpecialfeatures());
			stmt.setInt(10, 1);   //hardcoded default language_id to 1 (English)
			stmt.setInt(11, film.getId());
			
			
		     // Replace film's film with updated film by id
		    //  sql = "DELETE FROM film WHERE id = ?";
		     // stmt = conn.prepareStatement(sql);
		    //  stmt.setInt(1, film.getId());
		    //  int updateCount = stmt.executeUpdate();
		    //  sql = "INSERT INTO film(title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_features, language_id)"
			//	+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
		   //   stmt = conn.prepareStatement(sql);
		  //    for (Film filmmovie : films) {
			//        stmt.setInt(2, actor.getId());
			        
			//    	stmt.setString(1, film.getTitle());
			//		stmt.setString(2, film.getDescription());
			//		stmt.setInt(3, film.getReleaseyear());
			//		stmt.setInt(4, film.getRentalduration());
					// stmt.setDouble(5, film.getRentalrate());
			//		stmt.setDouble(5, 8.99);  //TEST to see if film line updates and hardcode film's rental_rate to 8.99
			//		stmt.setInt(6, film.getLength());
			//		stmt.setDouble(7, film.getReplacementcost());
			//		stmt.setString(8, film.getRating());
			//		stmt.setString(9, film.getSpecialfeatures());
			//		stmt.setInt(10, 1);   //hardcoded default language_id to 1 (English)
			//		stmt.setInt(11, film.getId());    
					
					
					
			        int updateCount = stmt.executeUpdate();
				
			
		     
                stmt.close();
				conn.commit(); // COMMIT TRANSACTION --this line of code ensures this film object gets added to
								// mySQL database
				conn.close();
			}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	public List<Film> getAllFilms() {

		List<Actor> cast = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, title, description, release_year, rating, length, special_features FROM film LIMIT 10";
			PreparedStatement stmt = conn.prepareStatement(sql);
			//stmt.setString(1, "%" + key + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String description = rs.getString(3);
				int releaseyear = rs.getInt(4);
				String rating = rs.getString(5);
				int length = rs.getInt(6);
				System.out.println(rs.getInt(6));
				String specialfeatures = rs.getString(7);
				cast = getActorsByID(id);
				Film film = new Film(id, title, description, releaseyear, rating, length, specialfeatures, cast);
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
	

}





/*
 * package com.skilldistillery.film.data;
 * 
 * import java.sql.Connection; import java.sql.DriverManager; import
 * java.sql.PreparedStatement; import java.sql.ResultSet; import
 * java.sql.SQLException; import java.util.ArrayList; import java.util.List;
 * 
 * public class FilmDaoDbImpl implements FilmDao { private static String url =
 * "jdbc:mysql://localhost:3306/sdvid"; private String user = "student"; private
 * String pass = "student"; private List<Film> titles=new ArrayList<>();
 * 
 * //constructor for my FilmDaoDbImpl public FilmDaoDbImpl() { try {
 * Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException e) {
 * e.printStackTrace(); System.err.println("Error loading MySQL Driver!!!"); } }
 * 
 * @Override public String getFilmTitleById(int id) { String title = null; try {
 * Connection conn = DriverManager.getConnection(url, user, pass); String sql =
 * "SELECT title FROM film WHERE id = ?"; PreparedStatement stmt =
 * conn.prepareStatement(sql); stmt.setInt(1, id); ResultSet rs =
 * stmt.executeQuery(); if (rs.next()) { //NOTICE THE IF instead of the WHILE
 * here in case id is invalid or larger than 1000 title = rs.getString(1); }
 * rs.close(); stmt.close(); conn.close(); } catch (SQLException e) {
 * e.printStackTrace(); } return title;
 * 
 * }
 * 
 * @Override public List<Film> getFilmTitleByKeyword(String key) {
 * titles.clear(); String title = null; try { Connection conn =
 * DriverManager.getConnection(url, user, pass); //String sql =
 * "SELECT title FROM film WHERE title LIKE '%" + key + "%'"; //String sql =
 * "SELECT title FROM film WHERE title LIKE ?"; String sql =
 * "SELECT id, first_name, last_name FROM actor WHERE id IN ( SELECT actor_id FROM film_actor WHERE film_id = ?)"
 * ; PreparedStatement stmt = conn.prepareStatement(sql); stmt.setString(1, "%"
 * + key +"%"); ResultSet rs = stmt.executeQuery(); int id = 1; while
 * (rs.next()) { //NOTICE THE IF instead of the WHILE here in case id is invalid
 * or larger than 1000 // title = id + ". " + rs.getString(1); //this is the way
 * to get the number before each element title = rs.getString(1);
 * titles.add(title); id++; } System.out.println(id + " results"); rs.close();
 * stmt.close(); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
 * return titles; }
 * 
 * 
 * 
 * // SELECT id, first_name, last_name FROM actor WHERE id IN ( SELECT actor_id
 * FROM // film_actor WHERE film_id = ?)
 * 
 * 
 * 
 * 
 * 
 *    <c:choose>  POTENTIAL TO USE in JSPafterAddedFilm
  <c:when test="${film != null}">
     <ul>

    <li>FilmId = ${film.id}</li>
    <li>${film.title}</li>
    <li>${film.description}</li>
    <li>${film.releaseyear}</li>
    <li>${film.rentalduration}</li>
    <li>${film.rentalrate}</li>
    <li>${film.length}</li>
    <li>${film.replacementcost}</li>
    <li>${film.rating}</li>
    <li>${film.specialfeatures}</li>

    </ul>
    <br><br>
    
    Film ID: <input type="hidden" name="filmId" value="${edit.filmId}">${edit.filmId}<br>
    Film ID: <input type="text" name="filmId" value="${edit.filmId}">${edit.filmId}<br>

    
  </c:when>
  </c:choose>
 * }
 *******/


   
