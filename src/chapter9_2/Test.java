package chapter9_2;

/**
 * Created by yupenglei on 17/7/11.
 */
class Test {
    public static void main(String[] args) {
        new D().hello();
    }

    interface A {
        default void hello() {
            System.out.println("Hello A!");
        }
    }

    interface B extends A {
//        @Override
//        default void hello() {
//            System.out.println("Hello B!");
//        }
    }

    interface C extends A {
//        @Override
//        default void hello() {
//            System.out.println("Hello C!");
//        }
    }

    private static class D implements B, C {

//        @Override
//        public void hello() {
//            B.super.hello();
//            C.super.hello();
//        }
    }
}
