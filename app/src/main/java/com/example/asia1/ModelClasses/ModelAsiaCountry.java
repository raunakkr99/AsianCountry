package com.example.asia1.ModelClasses;

public class ModelAsiaCountry {

    private String name,capital,region,subregion,population,flag,border,languages;




    public ModelAsiaCountry(String name, String capital, String region, String subregion, String population, String flag, String border, String languages) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.flag = flag;
        this.border = border;
        this.languages = languages;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }
}
