package org.vitalii.fedyk.peex.controlflowstructures;

import ch.qos.logback.core.encoder.JsonEscapeUtil;

import java.util.List;
import java.util.function.*;

public class MethodReference {
    //This method works with different quantity of parameters
    private static void display(final int number) {
        System.out.println("Number is " + number);
    }

    static class Person {
        private int age;

        public Person(int age) {
            this.age = age;
        }

        public void sayAge() {
            System.out.println("My age is " + age);
        }

        public int getAge() {
            return age;
        }

        public int multiplyAgeByNumber(final int number) {
            return age * number;
        }
    }

    static class IntegerUtils {
        public boolean isLessThanThree(final int number) {
            return number < 3;
        }
    }

    @SafeVarargs
    public static <T> void execute(final T... numbers) {
        System.out.println("Quantity of parameters: " + numbers.length);
    }

    public static void main(String[] args) {
        //Static method reference
        final List<Integer> list = List.of(1, 2, 3, 4, 5);
        list.forEach(MethodReference::display);

        //Reference to an Instance Method of an Arbitrary Object of a Particular Type
        //Example 1
        System.out.println();
        List<Person> personList = List.of(new Person(1), new Person(2), new Person(3));
        personList.forEach(Person::sayAge);
        //Example 2
        BiFunction<Person, Integer, Integer> biFunction = Person::multiplyAgeByNumber;
        Consumer<Person> consumer = Person::sayAge;
        Function<Person, Integer> function = Person::getAge;

        //Reference to an Instance Method of a Particular Object
        System.out.println();
        final IntegerUtils integerUtils = new IntegerUtils();
        list.stream()
                .filter(integerUtils::isLessThanThree)
                .forEach(System.out::println);

        //Reference to a constructor
        System.out.println();
        list.stream().map(Person::new)
                .forEach(Person::sayAge);

        //Using a function which accepts different quantity of parameters
        final BiConsumer<Integer, Integer> function1 = MethodReference::execute;
        final Consumer<Integer> function2 = MethodReference::execute;
        final Runnable function3 = MethodReference::execute;
    }
}

@FunctionalInterface
interface FunctionalInterfaceUsage {
    String execute(String message);

    static String get(FunctionalInterfaceUsage interfaceUsage, String message) {
        return interfaceUsage.execute(message);
    }

    static void main(final String... args) {
        FunctionalInterfaceUsage functionalInterfaceUsage = String::toUpperCase;
        System.out.println(FunctionalInterfaceUsage.get(functionalInterfaceUsage, "hello world"));
    }
}

@FunctionalInterface
interface ExecuteOne {
    void execute(int number);

    default void minus(int number1, int number2) {
        System.out.println(number1 - number2);
    }
}

@FunctionalInterface
interface ExecuteTwo {
    void execute(int number);

    default void add(int number1, int number2) {
        System.out.println(number1 + number2);
    }
}

@FunctionalInterface
interface ExecuteMain extends ExecuteOne, ExecuteTwo {
    void execute(int number);
}

@FunctionalInterface
interface Foo {

    String method(String string);

    default void defaultMethod() {
    }
}

class UseFoo {
    private String value = "Enclosing scope value";

    public String scopeExperiment() {
        final Foo fooIC = new Foo() {
            String value = "Inner class value";

            @Override
            public String method(final String string) {
                return value;
            }
        };
        final String resultIC = fooIC.method("");

        final Foo fooLambda = parameter -> {
            final String value = "Lambda value";
            return this.value;
        };
        final String resultLambda = fooLambda.method("");

        return "Results: resultIC = " + resultIC + ", resultLambda = " + resultLambda;
    }

    public static void main(String[] args) {
        final UseFoo useFoo = new UseFoo();
        System.out.println(useFoo.scopeExperiment());
    }
}

//class EffectivelyFinal {
//
//    public void func() {
//        String variable = "Hello";
//        variable = "d";
//        final Foo foo = string -> {
//            System.out.println(variable);
//        };
//        final String result = foo.method("");
//        System.out.println(result);
//    }
//
//    public static void main(String[] args) {
//        final EffectivelyFinal effectivelyFinal = new EffectivelyFinal();
//        effectivelyFinal.func();
//    }
//}