package com.movies.movies.repositories;

import com.movies.movies.models.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findMovieByImdbId(String imdbId);
    @Query("{$or : [{'title': { $regex: ?0, $options:'i' }}, {'genres': { $regex: ?0, $options:'i' }}]}")
    Optional<List<Movie>> searchMovies(String regex);
}
