package com.driver;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    /**
     * Add a movie: POST /movies/add-movie
     * Pass the Movie object as request body
     * Return success message wrapped in a ResponseEntity object
     * Controller Name - addMovie
     */
    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovieService(movie);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    /**
     *
     * Add a director: POST /movies/add-director
     * Pass the Director object as request body
     * Return success message wrapped in a ResponseEntity object
     * Controller Name - addDirector
     */

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirectorService(director);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    /**
     *
     * Pair an existing movie and director: PUT /movies/add-movie-director-pair
     * Pass movie name and director name as request parameters
     * Return success message wrapped in a ResponseEntity object
     * Controller Name - addMovieDirectorPairs
     *
     * @param movieName
     * @param directorName
     * @return sucess message
     */

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie name") String movieName,
                                                       @RequestParam("director name") String directorName) {
        movieService.mapMovieAndDirectorService(movieName, directorName);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    /**
     * Get Movie by movie name: GET /movies/get-movie-by-name/{name}
     * Pass movie name as path parameter
     * Return Movie object wrapped in a ResponseEntity object
     * Controller Name - getMovieByName
     *
     * @param movieName
     * @return Movie object (model)
     */

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName) {
        return new ResponseEntity<>(movieService.getMovieByName(movieName), HttpStatus.ACCEPTED);
    }

    /**
     * 5.Get Director by director name: GET /movies/get-director-by-name/{name}
     * Pass director name as path parameter
     * Return Director object wrapped in a ResponseEntity object
     * Controller Name - getDirectorByName
     *
     */

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName) {
        return new ResponseEntity<Director>(movieService.getDirectorByName(directorName), HttpStatus.ACCEPTED);
    }

    /**
     * 6.Get List of movies name for a given director name: GET
     * /movies/get-movies-by-director-name/{director}
     * Pass director name as path parameter
     * Return List of movies name(List()) wrapped in a ResponseEntity object
     * Controller Name - getMoviesByDirectorName
     */
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName) {
        return new ResponseEntity<>(movieService.getAllMovieNamesOfDirectorService(directorName), HttpStatus.ACCEPTED);

    }

    /**
     * 7..Get List of all movies added: GET /movies/get-all-movies
     * No params or body required
     * Return List of movies name(List()) wrapped in a ResponseEntity object
     * Controller Name - findAllMovies
     */

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        return new ResponseEntity<List<String>>(movieService.getAllMovieNames(), HttpStatus.ACCEPTED);
    }

    /**
     * 8..Delete a director and its movies from the records: DELETE
     * /movies/delete-director-by-name
     * Pass director’s name as request parameter
     * Return success message wrapped in a ResponseEntity object
     * Controller Name - deleteDirectorByName
     */

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director's name") String directorName) {
        movieService.deleteDirectorAndItsMovies(directorName);
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }

    /**
     * 9..Delete all directors and all movies by them from the records: DELETE
     * /movies/delete-all-directors
     * No params or body required
     * Return success message wrapped in a ResponseEntity object
     * Controller Name - deleteAllDirectors
     * (Note that there can be some movies on your watchlist that aren’t mapped to
     * any of the director. Make sure you do not remove them.)
     */

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteNonMappedRecord();
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }

}