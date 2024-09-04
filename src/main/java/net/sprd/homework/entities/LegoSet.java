package net.sprd.homework.entities;

public class LegoSet {

    private final String num;
    private final String name;
    private final int year;
    private final int themeId;
    private final String themeName;
    private final int numParts;

    public LegoSet(String num, String name, int year, int themeId, String themeName, int numParts) {
        this.num = num;
        this.name = name;
        this.year = year;
        this.themeId = themeId;
        this.themeName = themeName;
        this.numParts = numParts;
    }

    public String getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getThemeId() {
        return themeId;
    }
    
    public String getThemeName() {
    	return themeName;
    }

    public int getNumParts() {
        return numParts;
    }

}
