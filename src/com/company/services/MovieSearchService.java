package com.company.services;

import com.company.exceptions.MovieNotFoundException;
import com.company.models.Director;
import com.company.models.Movie;
import com.company.repositories.MovieRepository;

import java.io.FileNotFoundException;
import java.util.List;


public class MovieSearchService {

    private  final MovieRepository movieRepository;

    public MovieSearchService() throws FileNotFoundException {
        movieRepository = new MovieRepository();
    }

    public List<Movie> findAllCatalog(){
        return movieRepository.getMovieList().stream().toList();
    }

    public Movie findMovieByName(String movieName){
      return movieRepository.getMovieList().stream().
                filter((m -> m.getName().equalsIgnoreCase(movieName)))
                .findFirst().orElseThrow(()->new MovieNotFoundException("" +
                       "movie with name "+ movieName + " not found"));
    }

    public List<Director> findByDirectorName(String directorsName) {
        return movieRepository.getMovieList().stream()
                .filter(movie -> movie.getDirector().getFullName().equalsIgnoreCase(directorsName))
                .map(Movie::getDirector)
                .toList();
    }
    public List<Movie> findMoviesByYear(int moviesYear){
        return (List<Movie>) movieRepository.getMovieList().stream()

                .filter(movie -> movie.getYear()==moviesYear).toList();
    }
    public List<String> findMoviesByActorsName(String fullName){
        return movieRepository.getMovieList().stream()
                .filter(movie -> movie.getCast().stream().anyMatch(cast -> cast.getFullName().equalsIgnoreCase(fullName)))
                .map(Movie::getName)
                .toList();
    }
    public List<Movie> findListAllMovies(String actorsName){
        return movieRepository.getMovieList().stream()
                .filter(movie -> movie.getCast().stream().anyMatch(cast -> cast.getFullName().equalsIgnoreCase(actorsName)))
                .toList();
    }
}
