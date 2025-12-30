package com.workflex.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

record Transaction(Trader trader, int year, int value) {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // Listing 5.1. Finds all transactions in 2011 and sort by value (small to high)
        /*
        Time complexity
        Filter: O(n)
        Sort: O(n log n)
        Overall: O(n log n)
         */
        List<Transaction> t1 =
                transactions.stream()
                        .filter(t -> t.year() == 2011)
                        .sorted(comparing(Transaction::value))
                        .toList();


        // Listing 5.2. What are all the unique cities where the traders work?
        /*
        Time & space complexity
        Time: O(n) average
        Space: O(n) (internally uses a Set)
         */
        List<String> uniqueCities =
                transactions.stream()
                        .map(t -> t.trader().city())
                        .distinct()
                        .toList(); // ["Cambridge", "Milan"]


        // Listing 5.3. Finds all traders from Cambridge and sort them by name
        // this version is WRONG WRONG WRONG
//        List<Trader> allTraders =
//                transactions.stream()
//                        .filter(e-> e.trader().city().equals("Cambridge"))
//                        .sorted(comparing(e -> e.trader().name()))
//                        .map(Transaction::trader)
//                        .toList();


        List<Trader> allTraders =
                transactions.stream()
                        .map(Transaction::trader)
                        .filter(t -> t.city().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::name))
                        .toList();


        // Listing 5.4. Returns a string of all traders’ names sorted alphabetically
        List<String> listAllTradersName =
                transactions.stream()
                        .map(Transaction::trader)
                        .map(Trader::name)
                        .distinct()
                        .sorted()
                        .toList();

        String allTradersName =
                transactions.stream()
                        .map(Transaction::trader)
                        .map(Trader::name)
                        .distinct()
                        .sorted()
                        .collect(Collectors.joining());
//                        .collect(Collectors.joining(", "));


//        Optional<Trader> anyTraderInMilan =
//                transactions.stream()
//                        .map(Transaction::trader)
//                        .filter(t -> t.city().equals("Milan"))
//                        .findAny();

        boolean anyTraderInMilan =
                transactions.stream()
                        .anyMatch(t -> t.trader().city().equals("Milan"));


//        Listing 5.6. Prints all transactions’ values from the traders living in Cambridge

//        transactions.stream()
//                .filter(t -> t.trader().city().equals("Cambridge"))
//                .map(Transaction::value)
//                .forEach(System.out::println);


        // Listing 5.7. What’s the highest value of all the transactions?
//        int highestTran =
//                transactions.stream()
//                        .map(Transaction::value)
//                        .max(Integer::compareTo)
//                        .orElse(0);
//
//        int highestTran =
//                transactions.stream()
//                        .mapToInt(Transaction::value)
//                        .max()
//                        .orElse(0);

        Optional<Integer> highestValue =
                transactions.stream()
                        .map(Transaction::value)
                        .reduce(Integer::min);


        System.out.println(highestValue);


    }


}


