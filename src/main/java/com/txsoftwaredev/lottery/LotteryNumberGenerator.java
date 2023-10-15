package com.txsoftwaredev.lottery;

import java.util.*;

public class LotteryNumberGenerator {

    private static final int WHITE_BALLS_RANGE = 69;
    private static final int POWERBALL_RANGE = 26;
    private static final int NUMBER_WHITE_BALLS = 5;
    private final Random random = new Random();
    private final List<Integer> whiteBalls = new ArrayList<>();
    private final List<Integer> excludedNumbers = new ArrayList<>();
    private final List<Integer> alwaysInclude = new ArrayList<>();


    public static void main(String[] args) {

        LotteryNumberGenerator generator = new LotteryNumberGenerator();
        generator.run();
    }

    private void run() {

        addIncludedNumbers();
        addExcludedNumbers();

        //generate an evenly distributed random white ball, 5 times
        for (int i = 0; i < NUMBER_WHITE_BALLS; i++) {

            int number;
            do {
                //if a list of numbers to always include is not empty, use them!
                if (!alwaysInclude.isEmpty()) {
                    number = getIncludedNumber();
                } else {
                    //otherwise, generate a random one
                    number = random.nextInt(1, WHITE_BALLS_RANGE + 1);
                }
            } while (excludedNumbers.contains(number));

            //add the number to the result list
            whiteBalls.add(number);
        }

        //generate the power ball
        int powerBall = random.nextInt(1, POWERBALL_RANGE + 1);

        //sort the results ascending
        Collections.sort(whiteBalls);

        //display the result
        System.out.println("Picks: " + whiteBalls + "; Power Ball: " + powerBall);
    }

    private void addIncludedNumbers() {
        alwaysInclude.add(10);
        alwaysInclude.add(20);
        alwaysInclude.add(30);
        alwaysInclude.add(12);
    }

    private void addExcludedNumbers() {
        excludedNumbers.add(7);
        excludedNumbers.add(10);
    }

    private int getIncludedNumber() {
        int index = random.nextInt(alwaysInclude.size());
        int number = alwaysInclude.get(index);
        alwaysInclude.remove(index);
        return number;
    }
}
