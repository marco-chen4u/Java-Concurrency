package stream;

public class NullPointerExceptionDemo {

    public static void main(String[] args) {
        //String insuranceName = getInsuranceName(new Person());

        /*String result = getInsuranceNameByDeepDoubts(new Person());
        System.out.println(result);*/

        String result2 = getInsuranceNameByMultipleCheck(new Person());
        System.out.println(result2);

    }

    private static String getInsuranceNameByDeepDoubts(Person person) {

        if (person != null && person.getCar() != null && person.getCar().getInsurance() != null) {
            return person.getCar().getInsurance().getName();
        }

        return "UNKNOWN";
    }

    private static String getInsuranceNameByMultipleCheck(Person person) {
        final String defaultValue = "UNKNOWN";

        if (person == null || person.getCar() == null || person.getCar().getInsurance() == null) {
            return  defaultValue;
        }

        return person.getCar().getInsurance().getName();
    }

    private static String getInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }
}

// helper class
/*class Person {
    private Car car;

    public Car getCar() {
        return this.car;
    }
}

class Car {
    private Insurance insurance;

    public Insurance getInsurance() {
        return insurance;
    }
}

class Insurance {
    private String name;

    public String getName() {
        return name;
    }
}*/
