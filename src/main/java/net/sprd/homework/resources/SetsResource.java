package net.sprd.homework.resources;

import net.sprd.homework.db.LegoDAO;
import net.sprd.homework.entities.LegoSet;
import net.sprd.homework.request.SetFilterParams;
import net.sprd.homework.response.SetCountByYear;
import net.sprd.homework.response.SetsResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SetsResource {

    private final LegoDAO legoDAO;

    public SetsResource(final LegoDAO legoDAO) {
        this.legoDAO = legoDAO;
    }

    @GetMapping(value = "/sets", produces = MediaType.APPLICATION_JSON_VALUE)
    public SetsResponse getSets(SetFilterParams params) {
    	List<SetCountByYear> setCountByYear = legoDAO.getSetCountByYear(params);
    	List<LegoSet> legoSets = legoDAO.findSets(params);
    	
        return new SetsResponse(legoSets, setCountByYear);
    }

}
