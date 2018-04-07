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
		boolean displayMenu = true;
		while (displayMenu) {
			System.out.println("Enter 1 to look up an film by ID: ");
			System.out.println("Enter 2 to look up an film by keyword: ");
			System.out.println("Press any other key to Quit:");
			int key = input.nextInt();
			switch (key) {
			case 1:
				System.out.println("Enter a film ID: ");
				int filmId = input.nextInt();
				Film film = db.getFilmById(filmId);
				if (film == null) {
					System.out.println("That ID is not found\n");
					break;
				}
				displayFilmInfo(film);
				System.out.println("");
				break;

			case 2:
				System.out.println("Enter a keyword: ");
				String keyword = input.next();
				List<Film> films = db.getFilmByKeyword(keyword);
				if (films.size() == 0) {
					System.out.println("Nothing matches the keyword entered \n");
					break;
				}
				for (Film film1 : films) {

					displayFilmInfo(film1);
					System.out.println("");

				}
				break;

			default:
				System.out.println("Goodbye");
				displayMenu = false;
				break;
			}
		}
	}

	public void displayFilmInfo(Film film) {

		System.out.println("Film title: " + film.getTitle());
		System.out.println("Year film released: " + film.getReleaseYear());
		System.out.println("Film rating: " + film.getRating());
		System.out.println("Description: " + film.getDescription());
		System.out.println(db.getFilmsLanguage(film.getLanguageId()));
		List<Actor> cast = film.getCast();
		System.out.println("Cast:");
		for (Actor actor : cast) {
			System.out.println(actor.getFirstName() + " " + actor.getLastName());

		}

	}

}
