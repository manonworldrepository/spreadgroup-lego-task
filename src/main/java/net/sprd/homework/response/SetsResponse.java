package net.sprd.homework.response;

import java.util.List;

import net.sprd.homework.entities.LegoSet;

public class SetsResponse {
	
	private List<LegoSet> sets;
	private List<SetCountByYear> setCountByYear;
	
	public SetsResponse(List<LegoSet> sets, List<SetCountByYear> setCountByYear) {
		this.sets = sets;
		this.setCountByYear = setCountByYear;
	}
	
	public List<LegoSet> getSets() {
		return sets;
	}
	
	public List<SetCountByYear> getSetCountByYear() {
		return setCountByYear;
	}

}
