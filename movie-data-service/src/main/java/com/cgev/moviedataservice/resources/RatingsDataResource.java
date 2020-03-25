package com.cgev.moviedataservice.resources;

import com.cgev.moviedataservice.models.Rating;
import com.cgev.moviedataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rating_data")
public class RatingsDataResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/user/{userId}")
    public UserRating getListOfRatings(@PathVariable("userId") String userId) {
        List<Rating> list = Arrays.asList(
                new Rating("123", 4),
                new Rating("234" , 5)
        );
        UserRating userRating = new UserRating();
        userRating.setRatings(list);
        return userRating;
    }
}
