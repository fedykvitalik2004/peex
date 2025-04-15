package org.vitalii.fedyk.peex.controlflowstructures;

public class SwitchStatementUsage {
    private enum Season {
        WINTER,
        SPRING,
        SUMMER,
        AUTUMN;

        public static Season getSeason(final String name) {
            return switch (name.toLowerCase()) {
                case "winter" -> WINTER;
                case "spring" -> SPRING;
                case "summer" -> SUMMER;
                case "autumn" -> AUTUMN;
                default -> throw new IllegalStateException("Unexpected value: " + name.toLowerCase());
            };
        }
    }

    public static void main(String[] args) {
        final Season season = Season.getSeason("Winter");

        switch (season) {
            case WINTER -> {
                System.out.println("It is winter");
                break;
            }
            case SPRING, SUMMER, AUTUMN -> {
                System.out.println("It is not winter");
                break;
            }
        }
    }
}
