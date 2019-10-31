package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionsDemo {
    public static void main(String[] args) {
        List<Transaction> transactions = getTransactions();

        //[1]Find all transactions in the year 2011 and sort them by value(small to high)
        List<Transaction> result1 = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                                                            .sorted(Comparator.comparing(Transaction::getValue))
                                                            .collect(Collectors.toList());
        System.out.println("result 1 = " + result1);
        getPrintlnBorder();

        //[2]What are all the unique cities where the trader work?
        System.out.println("result 2 = Cities where the trader works: ");
        transactions.stream()
                        .map(transaction -> transaction.getTrader())
                        .map(trader -> trader.getCity())
                        .distinct()
                        .forEach(System.out::println);
        getPrintlnBorder();

        //[3]Find all traders from Cambridge and sort them by name.
        System.out.println("result 3 = All traders from Cambridge and sort them by name: ");
        transactions.stream().map(transaction -> transaction.getTrader())
                                .filter(trader -> trader.getCity().equals("Cambridge"))
                                .distinct()
                                .sorted(Comparator.comparing(Trader::getName))
                                .forEach(System.out::println);
        getPrintlnBorder();

        //[4]Return a string of all trader's name sorted by alphabetically.
        System.out.println("result 4 = Return a string of all trader's name sorted by alphabetically:");
        String result4 = transactions.stream().map(transaction -> transaction.getTrader().getName())
                                                .distinct()
                                                .sorted()
                                                .reduce("", (name1, name2) -> name1 + name2);
        System.out.println(result4);
        getPrintlnBorder();

        //[5]Are there any traders based in Milan?
        System.out.println("result 5 = Are there any traders based in Milan?");
        //transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        boolean result5 = transactions.stream().map(transaction -> transaction.getTrader())
                                                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(result5);
        getPrintlnBorder();

        //[6]Print all transactions' values from the traders living in Cambridge.
        System.out.println("result 6 = Print all transactions' values from the traders living in Cambridge.");
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                                .map(transaction -> transaction.getValue())
                                .forEach(System.out::println);
        getPrintlnBorder();

        //[7]What's the highest value of all transactions?
        System.out.println("result 7 = What's the highest value of all transactions");
        Optional<Integer> result7 = transactions.stream().map(transaction -> transaction.getValue())
                                                            .reduce(Integer::max);
        System.out.println(result7.get());
        getPrintlnBorder();

        //[8]Find the transaction with the smallest value.
        System.out.println("result 8 = Find the transaction with the smallest value.");
        //Optional<Transaction> result8 = transactions.stream().reduce((a, b) -> a.getValue() < b.getValue() ? a : b);
        //System.out.println(result8.get());
        Transaction result8 = transactions.stream().min(Comparator.comparing(Transaction::getValue)).orElse(null);
        System.out.println(result8);
        getPrintlnBorder();

    }

    private static List<Transaction> getTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    private static void getPrintlnBorder() {
        System.out.println("-------------------------------------------------------------");;
    }
}

class Transaction {
    // fields
    private final Trader trader;
    private final int year;
    private final int value;

    // constructor
    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}

class Trader {
    // fields
    private String name;
    private String city;

    // constructor
    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader: " +
                name +
                " in " + city;
    }
}
