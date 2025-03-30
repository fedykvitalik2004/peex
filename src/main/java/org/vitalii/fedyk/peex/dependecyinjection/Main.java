package org.vitalii.fedyk.peex.dependecyinjection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Container.class);
        final Student student = context.getBean(Student.class);
        System.out.println(student);
        if (context.containsBean("library")) {
            System.out.println("The bean library is available");
            System.out.println(context.getBean("library"));
        }
        System.out.println(context.getMessage("greeting", null, Locale.of("en")));
        context.close();
    }
}
