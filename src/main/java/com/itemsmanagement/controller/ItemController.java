package com.itemsmanagement.controller;

import com.itemsmanagement.DTO.ItemDTO;
import com.itemsmanagement.enums.ItemStatus;
import com.itemsmanagement.exception.ModelNotFoundException;
import com.itemsmanagement.model.Item;
import com.itemsmanagement.service.IItemService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.itemsmanagement.commons.GlobalConstant.API_ITEM;


@RestController
@RequestMapping(API_ITEM)
public class ItemController {


    private final IItemService service;

    @Qualifier("itemMapper")
    private final ModelMapper mapper;

    public ItemController(final IItemService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ItemDTO item) throws Exception{
        service.saveInsertItem(item);
        return new ResponseEntity<>("Guardado Exitosamente", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody ItemDTO dto) throws Exception{

        Item obj = service.readById(dto.getItemId());
            if(obj == null){
                throw  new ModelNotFoundException("ID NOT FOUND: " + dto.getItemId());
            }

        service.updateItem(dto);
        return new ResponseEntity<>("Actualizado Exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("/remove-item/{item_id}")
    public ResponseEntity<?> deleteItemById(@Valid @PathVariable Integer item_id) throws Exception{
        Item obj = service.readById(item_id);
        if(obj == null){
            throw  new ModelNotFoundException("ID NOT FOUND: " + item_id);
        }

        service.deleteItemById(item_id);
        return new ResponseEntity<>("Eliminado Exitosamente", HttpStatus.ACCEPTED);

    }


    @GetMapping("/find-all")
    public ResponseEntity<List<ItemDTO>> readAll() throws Exception{
        List<ItemDTO> list = service.readAll().stream().map(item -> mapper.map(item, ItemDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/find-items")
    public ResponseEntity<List<ItemDTO>> getByStatus() throws Exception{
        List<ItemDTO> list = service.getByStatus(ItemStatus.A).stream().map(item -> mapper.map(item, ItemDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PutMapping("/enabled-item/{id_item}")
    public ResponseEntity<?> enabledItemById(@Valid @PathVariable Integer id_item) throws Exception{
        Item obj = service.readById(id_item);
        if(obj == null){
            throw  new ModelNotFoundException("ID NOT FOUND: " + id_item);
        }
        service.enabledItemById(id_item);
        return new ResponseEntity<>("Habilitado Exitosamente",HttpStatus.ACCEPTED);
    }





}
