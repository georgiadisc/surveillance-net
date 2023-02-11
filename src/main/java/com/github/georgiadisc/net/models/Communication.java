package com.github.georgiadisc.net.models;

public abstract class Communication {

    protected final String firstNumber;
    protected final String secondNumber;
    protected final int day;
    protected final int month;
    protected final int year;

    protected Communication(String firstNumber, String secondNumber, int day, int month, int year) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getFirstNumber() {
        return this.firstNumber;
    }

    public String getSecondNumber() {
        return this.secondNumber;
    }

    /**
     * Checks if the message or phone call was made between these two numbers.
     *
     * @param firstNumber  The first number
     * @param secondNumber The second number
     */
    public boolean consistsOf(String firstNumber, String secondNumber) {
        return (this.firstNumber.equals(firstNumber) && this.secondNumber.equals(secondNumber))
                || (this.firstNumber.equals(secondNumber) && this.secondNumber.equals(firstNumber));
    }

}
