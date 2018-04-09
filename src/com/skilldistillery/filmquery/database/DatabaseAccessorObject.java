package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static String user = "student";
	private static String pass = "student";
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> cast = new ArrayList<>();
		String sql = "select id, first_name, last_name from actor join film_actor"
				+ " on actor.id = film_actor.actor_id where film_id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String fn = rs.getString(2);
				String ln = rs.getString(3);
				Actor actor = new Actor(id, fn, ln);
				cast.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cast;
	}

	@Override
	public Film getFilmById(int filmId) {
		Film film = null;
		String sql = "select id, title, description, release_year, language_id, rental_duration,"
				+ "rental_rate, length, replacement_cost, rating, special_features " + "from film where id = ?";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String description = rs.getString(3);
				int releaseYear = rs.getInt(4);
				int languageId = rs.getInt(5);
				int rentalDuration = rs.getInt(6);
				double rentalRate = rs.getDouble(7);
				int length = rs.getInt(8);
				double replacementCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String specialFeatures = rs.getString(11);
				List<Actor> cast = getActorsByFilmId(id);
				Language languages = getFilmsLanguage(filmId);
				film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, cast);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return film;

	}

	public Actor getActorById(int actorId) {
		Actor actor = null;
		String sql = "select id, first_name, last_name from actor where id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String fn = rs.getString(2);
				String ln = rs.getString(3);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Film> getFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		String sql = "SELECT id, title, description, release_year, language_id,"
				+ " rental_duration, rental_rate, length, replacement_cost, rating,"
				+ " special_features FROM film WHERE title like ? OR description LIKE ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String description = rs.getString(3);
				int releaseYear = rs.getInt(4);
				int languageId = rs.getInt(5);
				int rentalDuration = rs.getInt(6);
				double rentalRate = rs.getDouble(7);
				int length = rs.getInt(8);
				double replacementCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String specialFeatures = rs.getString(11);
				List<Actor> cast = getActorsByFilmId(id);
				Language language = getFilmsLanguage(languageId);

				Film film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						length, replacementCost, rating, specialFeatures, cast);
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

	public Language getFilmsLanguage(int languageId) {
		Language language = null;
		String sql = "select language.name from language where language.id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, languageId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				String name = rs.getString(1);
				language = new Language(name);

			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;

	}
	

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

}
