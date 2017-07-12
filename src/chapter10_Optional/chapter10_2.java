package chapter10_Optional;

import java.util.Optional;

/**
 * Optional其实就是Stream
 * Created by yupenglei on 17/7/11.
 */
public class chapter10_2 {
    public static void main(String[] args) { }

    /**
     * 10.3.3
     * 使用flatMap链接Optional对象
     */
    public static String getCarInsuranceName(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    /**
     * 10.3.5
     * 两个Optional对象的组合
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static Optional<Insurance> nullSafeFindCheapestInsurance(
            Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> get(p, c)));
    }

    public static Optional<Insurance> nullSagefindCheapestInsurance(Person person, Car car) {
        return Optional.ofNullable(person)
                .flatMap(p -> Optional.ofNullable(car).map(c -> get(p, c)));
    }

    private static Insurance get(Person person, Car car) {
        return null;
    }

}
