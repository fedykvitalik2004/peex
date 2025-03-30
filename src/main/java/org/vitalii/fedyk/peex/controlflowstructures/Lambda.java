package org.vitalii.fedyk.peex.controlflowstructures;

import java.util.List;

@FunctionalInterface
interface Sentence {
    void say();
}

interface Operation {
    Integer execute();
}

class SumOfTwoLambdas {
    static Integer sum(final Operation operation1, final Operation operation2) {
        return operation1.execute() + operation2.execute();
    }

    public static void main(String[] args) {
        final Operation operation1 = () -> 1 + 3;
        final Operation operation2 = () -> -2;

        System.out.println(sum(operation1, operation2));
    }
}

class LambdaInListStream {
    public static void main(String[] args) {
        final List<Integer> list = List.of(1, 2, 3, 4, 5);
        list.stream()
                .map(o -> {
                    final String s = o.toString();
                    return s + s;
                })
                .forEach(System.out::println);
    }
}

class SentenceUsage {
    public static void main(String[] args) {
        final Sentence sentence = () -> System.out.println("You use lambda");
        sentence.say();
    }
}
