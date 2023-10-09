package com.techtrove.userservice.infrastructure.repository.config;

import com.techtrove.userservice.infrastructure.repository.converter.ZonedDateTimeReadConverter;
import com.techtrove.userservice.infrastructure.repository.converter.ZonedDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import static java.util.Arrays.asList;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {

        return new MongoCustomConversions(asList(
                new ZonedDateTimeReadConverter(),
                new ZonedDateTimeWriteConverter()
        ));

    }

}