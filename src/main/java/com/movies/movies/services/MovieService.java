package com.movies.movies.services;

import com.movies.movies.models.Movie;
import com.movies.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> allMovies() {
        System.out.println("all movies");
        return movieRepository.findAll();
    }
    public Optional<Movie> singleMovie(String imdId){
        return movieRepository.findMovieByImdbId(imdId);
    }
    public Optional<List<Movie>> searchMovies(String regex){return movieRepository.searchMovies(regex);}

}
