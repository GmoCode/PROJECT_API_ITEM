package com.itemsmanagement.service.impl;

import com.itemsmanagement.DTO.ItemDTO;
import com.itemsmanagement.enums.ItemStatus;
import com.itemsmanagement.model.Item;
import com.itemsmanagement.repo.IGenericRepo;
import com.itemsmanagement.repo.IItemRepo;
import com.itemsmanagement.service.IItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends CRUDImpl<Item, Integer> implements IItemService {


    private final IItemRepo repo;

    public ItemServiceImpl(final IItemRepo repo) {
        this.repo = repo;
    }


    @Override
    protected IGenericRepo<Item, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Item> readAll() throws Exception {
        return repo.findAll().stream()
                .sorted(Comparator.comparingInt(Item::getItemId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getByStatus (ItemStatus itemStatus) {

        return repo.getByStatus(itemStatus);
    }


    @Transactional
    @Override
    public void deleteItemById(Integer item_id) throws Exception {
        repo.deleteItemById(item_id) ;
    }

    @Transactional
    @Override
    public void enabledItemById(Integer id) throws Exception {
         repo.enabledItemById(id);
    }


    @Transactional
    @Override
    public void saveInsertItem(ItemDTO itemDTO) throws Exception {
        repo.saveInsertItem(itemDTO.getItemName(),itemDTO.getItemDescription());
    }

    @Transactional
    @Override
    public void updateItem(ItemDTO itemDTO) throws Exception {
        repo.updateItem(itemDTO.getItemId(), itemDTO.getItemName(), itemDTO.getItemDescription(), itemDTO.getItemCode());

    }

}
