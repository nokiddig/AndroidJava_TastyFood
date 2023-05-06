package com.example.tastyfoods.mvvm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tastyfoods.mvvm.model.CartDetail;

import java.util.List;

@Dao
public interface CartDAO {
     @Query("SELECT * FROM cartDetail")
    List<CartDetail> getAllItems();
    @Query("SELECT * FROM cartDetail WHERE cartDetailId = :id")
    CartDetail getItemById(int id);

    @Update
    void updateItem(CartDetail item);

    @Delete
    void deleteItem(CartDetail item);
}