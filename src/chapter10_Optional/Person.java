package chapter10_Optional;

import java.util.Optional;

/**
 * Created by yupenglei on 17/7/11.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
class Person {
    //    private Car mCar;
//
//    Optional<Car> getCar() {
//        return Optional.ofNullable(mCar);
//    }
    private Optional<Car> mCar;

    Optional<Car> getCar() {
        return mCar;
    }
}
