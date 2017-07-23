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
		int length;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT title, rating, description, length, id FROM film WHERE title LIKE?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + key + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				title = rs.getString(1);
				rating = rs.getString(2);
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
				} else {
					film = null;
				}
				System.out.println(film);

				stmt.close();
				// conn.commit(); //COMMIT TRANSACTION --this line of code ensures this film object gets added to mySQL database
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
	public void deleteFilm(Film film) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFilm(Film film) {
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
			stmt.setInt(10, 1);
			stmt.setInt(11, film.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					film.setId(keys.getInt(1));
				} else {
					film = null;
				}
				System.out.println(film);

				stmt.close();
				conn.commit(); // COMMIT TRANSACTION --this line of code ensures this film object gets added to
								// mySQL database
				conn.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film" + film);
		}
	}

	public List<Film> getAllFilms() {
		return films;
	}

}







   
