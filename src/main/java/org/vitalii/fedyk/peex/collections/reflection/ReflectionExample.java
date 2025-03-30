package org.vitalii.fedyk.peex.collections.reflection;

import org.vitalii.fedyk.peex.sorting.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        final Class<Person> c = Person.class;
        System.out.println("Package name is " + c.getPackageName());

        final Person person = new Person(20);

        Field field = c.getDeclaredField("age");
        field.setAccessible(true);

        final int age = (Integer) field.get(person);
        System.out.println("The age is " + age);

        Method[] methods = c.getDeclaredMethods();

        for (Method method: methods) {
            System.out.println(method.getName() + " and " + method.getReturnType());
        }
    }
}
