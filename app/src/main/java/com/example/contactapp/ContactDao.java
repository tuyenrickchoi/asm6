package com.example.contactapp;

import androidx.room.Dao;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Insert
    void insert(Contact... contacts); // truyền được nhiều data


}