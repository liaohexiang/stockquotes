package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class StockQuotesApplication
{
    public static void main(String[] args) {
        SpringApplication.run(StockQuotesApplication.class, args);
        /*new SpringApplicationBuilder()
                .sources(StockQuotesApplication.class)
                .applicationStartup(new BufferingApplicationStartup(2048))
                .run(args);*/
    }
}
