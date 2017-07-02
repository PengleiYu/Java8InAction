package chapter5_5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JJBOOM on 2017/7/3.
 */
class Hello {
    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Cambridge");
    private static List<Transaction> mTransactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        action4();
    }

    private static void action8() {
//        mTransactions.stream()
//                .filter(transaction -> transaction.getValue() == (mTransactions.stream()
//                        .map(Transaction::getValue).reduce(Integer::min).get()))
//                .findAny()
//                .ifPresent(System.out::println);
        mTransactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .ifPresent(System.out::println);
    }

    private static void action7() {
        mTransactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }

    private static void action6() {
        mTransactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    private static void action5() {
        mTransactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .findAny()
                .ifPresent(System.out::println);
    }

    private static void action4() {
        mTransactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce((s1,s2)->s1+s2)
                .ifPresent(System.out::println);
    }

    private static void action3() {
        mTransactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
    }

    private static void action2() {
        mTransactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct().forEach(System.out::println);
    }

    private static void action1() {
        mTransactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
    }

    static class Trader {
        private final String mName;
        private final String mCity;

        public Trader(String name, String city) {
            mName = name;
            mCity = city;
        }

        public String getName() {
            return mName;
        }

        public String getCity() {
            return mCity;
        }

        @Override
        public String toString() {
            return "Trader:" + mName + '\'' +
                    ", in '" + mCity + '\'' +
                    '}';
        }
    }

    static class Transaction {
        private final Trader mTrader;
        private final int mYear;
        private final int mValue;

        public Transaction(Trader trader, int year, int value) {
            mTrader = trader;
            mYear = year;
            mValue = value;
        }

        public Trader getTrader() {
            return mTrader;
        }

        public int getYear() {
            return mYear;
        }

        public int getValue() {
            return mValue;
        }

        @Override
        public String toString() {
            return "{" +
                    "mTrader=" + mTrader +
                    ", mYear=" + mYear +
                    ", mValue=" + mValue +
                    '}';
        }
    }
}
