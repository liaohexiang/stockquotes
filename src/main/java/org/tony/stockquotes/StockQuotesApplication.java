package org.tony.stockquotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class StockQuotesApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockQuotesApplication.class, args);
        /*new SpringApplicationBuilder()
                .sources(StockQuotesApplication.class)
                .applicationStartup(new BufferingApplicationStartup(2048))
                .run(args);*/

        List list = new ArrayList<String>();
        list.stream().map(i -> i + "i").collect(Collectors.toList());
    }
}
