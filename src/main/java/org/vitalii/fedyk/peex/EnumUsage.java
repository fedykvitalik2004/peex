package org.vitalii.fedyk.peex;

interface Sentence {
    void say();
}

enum Week implements Sentence {
    MONDAY(1) {
        @Override
        public void say() {
            System.out.println("It is Monday");
        }

        @Override
        boolean isWeekend() {
            return false;
        }
    },
    TUESDAY(2) {
        @Override
        public void say() {
            System.out.println("It is Tuesday");
        }

        @Override
        boolean isWeekend() {
            return false;
        }
    },
    WEDNESDAY(3){
        @Override
        public void say() {
            System.out.println("It is Wednesday");
        }

        @Override
        boolean isWeekend() {
            return false;
        }
    },
    THURSDAY(4) {
        @Override
        public void say() {
            System.out.println("It is Thursday");
        }

        @Override
        boolean isWeekend() {
            return false;
        }
    },
    FRIDAY(5) {
        @Override
        public void say() {
            System.out.println("It is Friday");
        }

        @Override
        boolean isWeekend() {
            return false;
        }
    },
    SATURDAY(6) {
        @Override
        public void say() {
            System.out.println("It is Saturday");
        }

        @Override
        boolean isWeekend() {
            return true;
        }
    },
    SUNDAY(7) {
        @Override
        public void say() {
            System.out.println("It is Sunday");
        }

        @Override
        boolean isWeekend() {
            return true;
        }
    };

    private final int number;

    Week(int number) {
        this.number = number;
    }

    abstract boolean isWeekend();
}

public class EnumUsage {
    public static void main(String[] args) {
        Week.THURSDAY.say();
    }
}
