package com.movie.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movie.models.Movie;
import com.movie.repository.MovieRepo;

@Service
@Transactional
public class MovieServiceImpl implements IMovieService {

	// Dependency Injection
	@Autowired
	MovieRepo mrepo;
	@Autowired
	ServletContext ctx;

	// Add new movie to database
	@Override
	public void saveMovie(Movie movie, MultipartFile photo, MultipartFile banner) {
		try {
			// getOriginalFilename() return the original file name
			String ext = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
			System.out.println(ext);
			String filename = UUID.randomUUID().toString() + ext;
			System.out.println(filename);
			movie.setPoster("posters/" + filename);
			Files.copy(photo.getInputStream(), Paths.get(ctx.getRealPath("/posters/"), filename),
					StandardCopyOption.REPLACE_EXISTING);

			ext = banner.getOriginalFilename().substring(banner.getOriginalFilename().lastIndexOf("."));
			filename = UUID.randomUUID().toString() + ext;
			movie.setBanner("banners/" + filename);
			Files.copy(banner.getInputStream(), Paths.get(ctx.getRealPath("/banners/"), filename),
					StandardCopyOption.REPLACE_EXISTING);

			// saving a movie object into data base
			mrepo.save(movie);

		} catch (Exception ex) {
			System.err.println("Error " + ex.getMessage());
		}
	}

	// finding a particular movies by using movieId.
	@Override
	public Movie findMovieDetails(int mid) {
		return mrepo.getById(mid);
	}

	// getting list of all movies recent movie first
	@Override
	public List<Movie> allMovies() {
		return mrepo.findAll(Sort.by(Direction.DESC, "mid"));
	}

	// Method to edit a movie details
	@Override
	public void editMovie(Movie movie) {
		mrepo.save(movie);
	}

	// Delete movie from the dataBase
	@Override
	public void deleteMovie(int mid) {
		mrepo.deleteById(mid);
	}

	@Override
	public void editMovie(int mid, Movie movie) {
		mrepo.editMovie(movie.getActor(), movie.getActress(), movie.getDescr(), movie.getDirector(), movie.getMname(),
				movie.getReldate(), movie.getTrailer(), mid);

	}

}
