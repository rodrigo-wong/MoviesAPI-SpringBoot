package com.movies.movies.services;

import com.movies.movies.models.Movie;
import com.movies.movies.models.Review;
import com.movies.movies.repositories.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(String reviewBody, String imdbId){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
    public Optional<Review> modifyReview(String reviewBody, String id){
        ObjectId objectId = new ObjectId(id);
        Optional<Review> review = reviewRepository.findById(objectId);
        mongoTemplate.update(Review.class).matching(Criteria.where("_id").is(objectId))
                .apply(new Update().set("body", reviewBody))
                .first();
        return review;
    }
}
