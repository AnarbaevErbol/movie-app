package com.company;


import com.company.Enums.SortType;
import com.company.exceptions.BadInputException;
import com.company.exceptions.MovieNotFoundException;
import com.company.models.Movie;
import com.company.services.MovieSearchService;
import com.company.services.MovieSortService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        MovieSearchService movieSearchService = new MovieSearchService();
        MovieSortService movieSortService  =new MovieSortService();
        Scanner scanner = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        List<Movie>movies;

        commands();
        while (true) {
            System.out.println("\n---Commandalardan biroosun tandanyz----> ");
            int commandNumber = scanner.nextInt();

            if (commandNumber == 1) {
                movieSearchService.findAllCatalog().forEach(System.out::println);

            }else if(commandNumber==2){
                try {
                    System.out.println("Which Movie do you want to search: ");
                    String search = sc.nextLine();
                    Movie searchMovie = movieSearchService.findMovieByName(search);
                    System.out.println(searchMovie);
                }catch (MovieNotFoundException movieNotFoundException){
                    System.out.println(movieNotFoundException.getMessage());
                }

            }else if(commandNumber==3){
                movieSortService.sortMoviesByYear(SortType.ASC).forEach(System.out::println);

            }else if(commandNumber==4){
                movieSortService.sortMoviesByName(SortType.ASC).forEach(System.out::println);

            }else if(commandNumber==5){
                movieSortService.sortByDirectorName().forEach(System.out::println);

            }else if (commandNumber==6){
                System.out.println("Write actor's name: ");
                String actorName = sc.nextLine();
                System.out.println(movieSearchService.findMoviesByActorsName(actorName));

            }else if (commandNumber==7){
                System.out.println("Which Director do you want to search: ");
                String directorsName = sc.nextLine();
                movieSearchService.findByDirectorName(directorsName).forEach(System.out::println);

            }else if (commandNumber==8){
                try {
                    System.out.println("Which year's movie would you like to watch: ");
                    int movieYear = sc.nextInt();
                    movieSearchService.findMoviesByYear(movieYear).forEach(System.out::println);
                }catch (MovieNotFoundException movieNotFoundException){
                    System.out.println(movieNotFoundException.getMessage());
                }

            }else if(commandNumber==9){
                System.out.println("Actor's name: ");
                String actName = sc.nextLine();
                movieSearchService.findListAllMovies(actName).forEach(System.out::println);

            }else if (commandNumber==10){
                movieSortService.sortByActorName();
            }

        }

    }
    public static void commands(){
        System.out.println("------------All Commands------------");
        System.out.println("Press 1 to print catalog");
        System.out.println("Press 2 to Find a Movie by full or part name");
        System.out.println("Press 3 to sort by year");
        System.out.println("Press 4 to sort by name");
        System.out.println("Press 5 to sort by director");
        System.out.println("Press 6 to find movies by actor's name");
        System.out.println("Press 7 to find movies by director's name");
        System.out.println("Press 8 to find movies by year");
        System.out.println("Press 9 to List all movies and roles by actor's name");
        System.out.println("Press 10 to sorted List of all actors with his roles");
        System.out.println("---------------------------------------------");
    }
}
