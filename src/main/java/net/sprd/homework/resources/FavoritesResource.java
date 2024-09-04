package net.sprd.homework.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.sprd.homework.db.LegoDAO;
import net.sprd.homework.entities.LegoSet;
import net.sprd.homework.request.Favorite;

@RestController
public class FavoritesResource {
	
	private final LegoDAO legoDAO;

    public FavoritesResource(final LegoDAO legoDAO) {
        this.legoDAO = legoDAO;
    }
    
    @GetMapping(value = "/favorites", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LegoSet> getSets() {
        return legoDAO.listFavoriteSets();
    }
    
    @Transactional
    @PostMapping(value = "/favorites")
    @ResponseStatus(HttpStatus.CREATED)
    public void getFavorites(@RequestBody Favorite favorite) {
    	legoDAO.addFavorite(favorite);
    }
    
    @Transactional
    @DeleteMapping(value = "/favorites")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteFavorites(@RequestBody Favorite favorite) {
    	legoDAO.removeFavorite(favorite);
    }

}
