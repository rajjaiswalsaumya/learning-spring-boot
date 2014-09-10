package org.cdac.config;

/**
 * Created by raj on 10/09/14.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(DirectoryConfiguration.class)
@ComponentScan
public class Application implements CommandLineRunner {

    @Autowired
    private DirectoryConfiguration configuration;

    @Override
    public void run(String... args) {
        System.out.println(this.configuration.getErrorDir());
        System.out.println(this.configuration.getIndexedDir());
        System.out.println(this.configuration.getUnIndexedDir());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}