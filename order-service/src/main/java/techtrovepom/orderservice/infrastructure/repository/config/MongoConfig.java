package techtrovepom.orderservice.infrastructure.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import techtrovepom.orderservice.infrastructure.repository.converter.ZonedDateTimeReadConverter;
import techtrovepom.orderservice.infrastructure.repository.converter.ZonedDateTimeWriteConverter;

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