package com.itemsmanagement.repo;


import com.itemsmanagement.DTO.ItemDTO;
import com.itemsmanagement.enums.ItemStatus;
import com.itemsmanagement.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IItemRepo extends IGenericRepo<Item,Integer> {


    @Query("FROM Item  WHERE itemStatus LIKE ?1 ORDER BY itemId")
    List<Item> getByStatus(ItemStatus status );


    @Modifying
    @Query(value = "CALL remove_item(:item_id)", nativeQuery = true)
    void deleteItemById (@Param("item_id") Integer item_id);


    @Modifying
    @Query(value = "CALL enabled_item(:item_id)", nativeQuery = true)
    void enabledItemById (@Param("item_id") Integer item_id);


    @Modifying
    @Query(value = "CALL insert_item(:item_name, :item_description)", nativeQuery = true)
    void saveInsertItem (@Param("item_name") String item_name, @Param("item_description") String item_description );

    @Modifying
    @Query(value = "CALL update_item(:item_id, :item_name, :item_description, :item_code)", nativeQuery = true)
    void updateItem (@Param("item_id")Integer item_id, @Param("item_name") String item_name, @Param("item_description") String item_description,
                     @Param("item_code") String item_code);


}
