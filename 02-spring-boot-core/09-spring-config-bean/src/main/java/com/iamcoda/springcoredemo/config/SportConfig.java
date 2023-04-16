package com.iamcoda.springcoredemo.config;

import com.iamcoda.springcoredemo.common.Coach;
import com.iamcoda.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
