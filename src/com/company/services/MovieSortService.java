package com.company.services;

import com.company.Enums.SortType;
import com.company.models.Director;
import com.company.models.Movie;
import com.company.repositories.MovieRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.company.models.Cast;

public class MovieSortService {
    private  final MovieRepository movieRepository;

    public MovieSortService() throws FileNotFoundException {
        this.movieRepository=new MovieRepository();
    }

    public List<String> sortMoviesByName(SortType sortType){
        if(sortType.equals(SortType.ASC)){
            return movieRepository.getMovieList().stream()
                    .sorted(Comparator.comparing(Movie:: getName))
                    .map(Movie::getName)
                    .toList();
        }else{
            return movieRepository.getMovieList().stream()
                    .sorted(Comparator.comparing(Movie:: getName).reversed())
                    .map(Movie::getName)
                    .toList();
        }
    }

    public List<Integer> sortMoviesByYear(SortType sortType){
        if (sortType.equals(SortType.ASC)){
            return movieRepository.getMovieList().stream().sorted(Comparator.comparing(Movie::getYear))
                    .map(Movie::getYear)
                    .toList();
        }
        else {
            return movieRepository.getMovieList().stream().sorted(Comparator.comparing(Movie::getYear))
                    .map(Movie::getYear)
                    .toList();
        }
    }

    public List<Director> sortByDirectorName(){
            return movieRepository.getMovieList().stream()
                    .sorted(Comparator.comparing(o->o.getDirector().getFullName()))
                    .map(Movie::getDirector)
                    .toList();

    }
    public List<Cast> sortByActorName(){
        List<Cast>allcasts = new ArrayList<>();
        movieRepository.getMovieList().stream().forEach(m->allcasts.addAll(m.getCast()));
        allcasts.stream().sorted(Comparator.comparing(Cast::getFullName)).forEach(System.out::println);
        return allcasts;
    }
}
