package com.github.georgiadisc.net.models;

public class PhoneCall extends Communication {

    private final int duration;

    public PhoneCall(String number1, String number2, int day, int month, int year, int duration) {
        super(number1, number2, day, month, year);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.join(
                "\n",
                "This phone call has the following info",
                String.format("Between %s --- %s", firstNumber, secondNumber),
                String.format("on %d/%d/%d", year, month, day),
                String.format("Duration: %d", duration));
    }

}
