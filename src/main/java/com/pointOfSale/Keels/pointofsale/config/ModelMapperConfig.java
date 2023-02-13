package com.pointOfSale.Keels.pointofsale.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration        // Bean ekak Container ekata vatenna ona vidiya liyana class ekak thamai @Configuration class eka     (To Add model mapper to Container)
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
