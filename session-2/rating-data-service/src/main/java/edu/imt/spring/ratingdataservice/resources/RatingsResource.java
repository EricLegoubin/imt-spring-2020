package edu.imt.spring.ratingdataservice.resources;

import edu.imt.spring.ratingdataservice.models.Rating;
import edu.imt.spring.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserRating(Arrays.asList(
                new Rating("Tron", 4),
                new Rating("Matrix", 4)
        ));
        return userRating;
    }
}
