package org.tony.stockquotes;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.stereotype.Component;

@Configuration
public class RedisStreamConfig {


    @Bean
    public StreamMessageListenerContainer<String, MapRecord<String, String,String>> createStreamContainer(RedisConnectionFactory connectionFactory){

        StreamMessageListenerContainer container = StreamMessageListenerContainer.create(connectionFactory);
        container.start();
        return container;
    }

}
