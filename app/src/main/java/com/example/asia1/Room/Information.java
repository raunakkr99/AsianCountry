package com.example.asia1.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Information implements Serializable {


    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "capital")
    String capital;

    @ColumnInfo(name = "region")
    String region;

    @ColumnInfo(name = "subregion")
    String subregion;

    @ColumnInfo(name = "population")
    String population;

    @ColumnInfo(name = "flag")
    String flag;

    @ColumnInfo(name = "border")
    String border;

    @ColumnInfo(name = "languages")
    String languages;

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }






    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


}
