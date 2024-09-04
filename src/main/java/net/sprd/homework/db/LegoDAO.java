package net.sprd.homework.db;

import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import net.sprd.homework.entities.LegoSet;
import net.sprd.homework.request.Favorite;
import net.sprd.homework.request.SetFilterParams;
import net.sprd.homework.response.SetCountByYear;

@Repository
public class LegoDAO {

    private final Jdbi jdbi;

    public LegoDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<LegoSet> findSets(SetFilterParams params) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT"
            		+ " sets.set_num, sets.name AS set_name, sets.year, sets.theme_id,"
            		+ " sets.num_parts, themes.name AS theme_name FROM sets"
            		+ " INNER JOIN themes ON sets.theme_id = themes.id"
            		+ " WHERE (sets.year IS NULL OR sets.year LIKE '%' || :set_year || '%')"
            		+ " AND (sets.name IS NULL OR sets.name LIKE '%' || :set_name || '%')"
            		+ " AND (themes.name IS NULL OR themes.name LIKE '%' || :theme_name || '%')"
            		+ " ORDER BY set_num LIMIT :limit OFFSET :offset")
            		.bindMap(params.getFilterBoundMap())
                    .map(new LegoSetMapper())
                    .list();
        }
    }
    
    public List<LegoSet> listFavoriteSets() {
    	try (Handle handle = jdbi.open()) {
    		return handle.createQuery("SELECT"
    				+ " sets.set_num, sets.name AS set_name, sets.year, sets.theme_id,"
    				+ " sets.num_parts, themes.name AS theme_name FROM sets"
    				+ " INNER JOIN themes ON sets.theme_id = themes.id"
    				+ " INNER JOIN favorites ON sets.set_num = favorites.set_id")
    				.map(new LegoSetMapper())
    				.list();
    	}
    }
    
    public List<SetCountByYear> getSetCountByYear(SetFilterParams params) {
    	try (Handle handle = jdbi.open()) {
    		return handle.createQuery("SELECT COUNT(a.set_num) AS count, a.year AS key FROM (SELECT"
    				+ " sets.set_num, sets.year FROM sets"
    				+ " INNER JOIN themes ON sets.theme_id = themes.id"
    				+ " WHERE (sets.year IS NULL OR sets.year LIKE '%' || :set_year || '%')"
    				+ " AND (sets.name IS NULL OR sets.name LIKE '%' || :set_name || '%')"
    				+ " AND (themes.name IS NULL OR themes.name LIKE '%' || :theme_name || '%')"
    				+ " LIMIT :limit OFFSET :offset) AS a GROUP BY a.year")
    				.bindMap(params.getFilterBoundMap())
					.map(new SetCountByCountryMapper())
					.list();
    	}
    }
    
    public void addFavorite(Favorite favorite) {
    	try (Handle handle = jdbi.open()) {
    		handle.createUpdate("INSERT INTO favorites (id, set_id) VALUES (:id, :set_id)")
    			.bind("id", favorite.getId())
    			.bind("set_id", favorite.getSetId())
    			.execute();
    	}
    }
    
    public void removeFavorite(Favorite favorite) {
    	try (Handle handle = jdbi.open()) {
    		handle.createUpdate("DELETE FROM favorites WHERE set_id = :set_id")
    			.bind("set_id", favorite.getSetId())
    			.execute();
    	}
    }

}
