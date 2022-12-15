package com.itemsmanagement.service;

import com.itemsmanagement.DTO.ItemDTO;
import com.itemsmanagement.enums.ItemStatus;
import com.itemsmanagement.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService extends ICRUD<Item, Integer>{

     List<Item> getByStatus (ItemStatus status) throws  Exception;

    void deleteItemById(Integer id) throws Exception;

    void enabledItemById(Integer id) throws Exception;

    void saveInsertItem (ItemDTO itemDto) throws Exception;

    void updateItem(ItemDTO itemDTO) throws Exception;
}
