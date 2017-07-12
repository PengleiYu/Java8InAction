package chapter10_Optional;

import java.util.Optional;

/**
 * Created by yupenglei on 17/7/11.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
class Car {
    //    private Insurance mInsurance;
//
//    Optional<Insurance> getInsurance() {
//        return Optional.ofNullable(mInsurance);
//    }
    private Optional<Insurance> mInsurance;

    Optional<Insurance> getInsurance() {
        return mInsurance;
    }
}
