package org.vitalii.fedyk.peex.dependecyinjection;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = "org.vitalii.fedyk.peex.dependecyinjection")
public class Container {
    @Bean
    Book book() {
        return new Book("Never Let Me Go", "Kazuo Ishiguro");
    }

    @Bean
    Library library(Book book) {
        return new Library(book);
    }

    @Bean
    MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("greetings");
        return messageSource;
    }
}
