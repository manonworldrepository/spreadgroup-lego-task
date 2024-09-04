package net.sprd.homework.response;

public class SetCountByYear {
	
	private String key;
	private Integer count;
	
	public SetCountByYear(String key, Integer count) {
		this.key = key;
		this.count = count;
	}
	
	public String getKey() {
		return key;
	}
	
	public Integer getCount() {
		return count;
	}
}
