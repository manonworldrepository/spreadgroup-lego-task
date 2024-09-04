package net.sprd.homework.request;

import java.util.HashMap;
import java.util.Map;

public class SetFilterParams {
	
	private Integer year;
	private String setName;
	private String themeName;
	private Integer limit;
	private Integer offset;
	
	public SetFilterParams(Integer year, String setName, String themeName) {
		this.year = year;
		this.setName = setName;
		this.themeName = themeName;
	}
	
	public Map<String, Object> getFilterBoundMap() {
		Map<String, Object> filter = new HashMap<>();
		
    	filter.put("set_year", (year == null) ? 0 : year);
    	filter.put("set_name", (setName == null) ? "" : setName );
    	filter.put("theme_name", (themeName == null) ? "" : themeName);
    	filter.put("limit", (limit == null) ? 50 : limit);
    	filter.put("offset", (offset == null) ? 0 : offset);
    	
    	return filter;
	}
	
}
