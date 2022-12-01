package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovieService(Movie movie) {
        movieRepository.addMovieToDB(movie);
    }

    public void addDirectorService(Director director) {
        movieRepository.addDirectorToDB(director);
    }

    public void mapMovieAndDirectorService(String movieName, String DirectorName) {
        movieRepository.pairMovieAndDirector(movieName, DirectorName);
    }

    public Movie getMovieByName(String movieName) {
        return movieRepository.findByMovieName(movieName);
    }

    public Director getDirectorByName(String DirectorName) {
        return movieRepository.findByDirectorName(DirectorName);
    }

    public List<String> getAllMovieNamesOfDirectorService(String directorName) {
        return movieRepository.getAllMoviesOfDirector(directorName);
    }

    public List<String> getAllMovieNames() {
        return movieRepository.getAllMovies();
    }

    public void deleteDirectorAndItsMovies(String directorName) {
        movieRepository.deleteDirectorAndItsMoviesFromDB(directorName);
    }

    public void deleteNonMappedRecord() {
        movieRepository.DeleteAllDirectorRecordFromDB();
    }

}