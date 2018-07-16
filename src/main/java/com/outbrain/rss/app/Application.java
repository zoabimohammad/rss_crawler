package com.outbrain.rss.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.outbrain.rss.controller","com.outbrain.rss.service", "com.outbrain.rss.dao"})
@EntityScan("com.outbrain.rss.entity")
public class Application implements CommandLineRunner {

    public static void main(String[] args)  {
        // Run spring
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
