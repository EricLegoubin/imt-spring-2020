package edu.imt.spring.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import edu.imt.spring.moviecatalogservice.models.CatalogItem;
import edu.imt.spring.moviecatalogservice.models.Movie;
import edu.imt.spring.moviecatalogservice.models.Rating;
import edu.imt.spring.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogueResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating userRating = (UserRating) restTemplate.getForObject("http://rating-data-service/ratings/users/" + userId, UserRating.class);
        List<Rating> ratings = userRating.getUserRating();

        return ratings.stream().map(rating -> {
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://movie-info-service:8081/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(movie.getName(), "desc", rating.getRating());
        }).collect((Collectors.toList()));
    }

    public List<CatalogItem> getFallbackCatalog(@PathVariable String userId) {
        return Arrays.asList(new CatalogItem("No movie", "", 0));
    }
}
