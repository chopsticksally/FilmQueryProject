package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	private void test() {
		Film film = db.getFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		System.out.println("Enter 1 to look up an film by ID");
		System.out.println("Enter 2 to look up an film by keyword");
		int key = input.nextInt();
		switch (key) {
		case 1:
			System.out.println("Enter a film ID: ");
			int filmId = input.nextInt();
			Film film = db.getFilmById(filmId);
			if (film == null) {
				System.out.println("That ID is not found");
				break;
			}
			
			System.out.println("Film title: " + film.getTitle());
			System.out.println("Year film released: " + film.getReleaseYear());
			System.out.println("Film rating: " + film.getRating());
			System.out.println("Description: " + film.getDescription());
			System.out.println("Cast list by Actor id:\n " +  film.getCast());
			System.out.println( db.getFilmsLanguage(film.getLanguageId()));
			break;

		case 2:
			System.out.println("Enter a keyword: ");
			String keyword = input.next();
			List<Film> films = db.getFilmByKeyword(keyword);
			
			for (Film film1 : films) {

				System.out.println("Film title: " + film1.getTitle());
				System.out.println("Year film released: " + film1.getReleaseYear());
				System.out.println("Film rating: " + film1.getRating());
				System.out.println("Description: " + film1.getDescription());
				System.out.println("Cast list by Actor id:\n " + film1.getCast());
				System.out.println(db.getFilmsLanguage(film1.getLanguageId()));
			
				

			}

		default:
			break;
		}

	}
//	System.out.println("Film title: " + film1.getTitle());
//	System.out.println("Year film released: " + film1.getReleaseYear());
//	System.out.println("Film rating: " + film1.getRating());
//	System.out.println("Description: " + film1.getDescription());
//	//System.out.println("Cast list by Actor id:\n " + film1.getCast());
//	System.out.println(db.getFilmsLanguage(film1.getLanguageId()));
//
//	 List<Actor> cast = db.getActorsByFilmId(filmId);
//        System.out.println("The actors who starred in this movie are:\n");
//        for (Actor actor : cast) {
//          System.out.println(actor.getFirstName() + " " + actor.getLastName());
//        }
	
}
