####Film Query Homework Project

###What I did:
1. Create a Scanner in FilmQueryApp to input user choices.

2. Create a method that displays a menu for the user to lookup a film by either a film id, a keyword, or quit. 

3. Use the method already created in the DatabaseAccesorObject to query the database for the film info based on the film id entered by the user. If the film id is not found return to the main menu. 

4. Display the film's title, year, rating, and description when the corresponding film id is entered. The print lines should be located in the FilmQueryApp.

5. If user decides to lookup a film by keyword, create a method that iterates through all films in the database and adds them to a list if the keyword is found in the title or description of the film. If the keyword is not found print "keyword not found" and return to the main menu.

6. Create a method that gets the film's language via the language id. Query the database, add the method to the DatabaseAccessor class ( where all methods that query the database live)

7. Add the print line to the language method in FilmQueryApp that displays the films info.

8. Create an actor class POJO.

9. Create a method that retrieves all actors/cast, from the database, by film id and add them to an array list.

10. Add the Actor array list to Film class.

11. Add the print line to the method that displays the film's info. Instead of pulling the cast from the database pull the cast from the Actor list using the getters in the Actor class.

12. Since each menu selection prints the same information, create a method that prints the results and displays the film's info.

13. Put all the above code into a while loop with a boolean that returns the user to the main menu unless the user chooses to quit the program. If user chooses to quit, set the boolean to false to exit the while loop and end the program.






###What I learned:
1. Originally I started my menu with an if/else statement. If a number was entered I would handle it one way and if text was entered handle it another. I realized that a switch statement would be a lot easier to read and follow and changed my direction.

2. There was talk of exceptions because someone had run into a movie without a cast.... had I pulled the actor info from the database I would have gotten a null pointer exception on those movies.

3. The requirements for Stretch goal 1 were a little confusing.... I had a hard time not looking at this from a consumer's standpoint... In the case of looking up a film by keyword, I wanted to display a movie, ask if the user wanted more information about it and then prompt the user to either go to the next movie possessing that keyword or exit the menu... What if the user got the info they were looking for and didn't want to go through the entire list of movies with that same keyword? 
