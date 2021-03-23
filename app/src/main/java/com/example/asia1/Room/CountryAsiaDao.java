package com.example.asia1.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryAsiaDao {

    @Query("SELECT * FROM Information")
    List<Information> getAllNew();

    @Insert
    void insert(Information information);

}

