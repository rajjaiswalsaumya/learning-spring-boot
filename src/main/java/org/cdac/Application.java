package org.cdac;

/**
 * Created by raj on 10/09/14.
 */

import org.cdac.configs.DirectoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DirectoryConfiguration.class)
public class Application implements CommandLineRunner {

    @Autowired
    private DirectoryConfiguration configuration;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(this.configuration.getErrorDir());
        System.out.println(this.configuration.getIndexedDir());
        System.out.println(this.configuration.getUnIndexedDir());
    }
}