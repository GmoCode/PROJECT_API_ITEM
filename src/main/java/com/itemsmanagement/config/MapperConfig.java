package com.itemsmanagement.config;

import com.itemsmanagement.DTO.ItemDTO;
import com.itemsmanagement.model.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("itemMapper")
    public ModelMapper itemMapper(){

        return new ModelMapper();
    }


}
