package com.cgev.moviecatalogservice.resources;

import com.cgev.moviecatalogservice.models.CatalogItem.CatalogItem;
import com.cgev.moviecatalogservice.models.CatalogItem.Movie;
import com.cgev.moviecatalogservice.models.CatalogItem.Rating;
import com.cgev.moviecatalogservice.models.CatalogItem.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/catalog")
public class MovieCatalogResource {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating forObject = restTemplate.getForObject("http://rating-data-service/rating_data/user/123", UserRating.class);
        return forObject.getRatings().stream().
                map(rating -> new CatalogItem(
        restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class)
                    .getName(),
                        "Test", rating.getRating()))
                .collect(Collectors.toList());
    }
}