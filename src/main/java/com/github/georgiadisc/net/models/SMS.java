package com.github.georgiadisc.net.models;

public class SMS extends Communication {

    private final String content;

    public SMS(String number1, String number2, int day, int month, int year, String content) {
        super(number1, number2, day, month, year);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    /**
     * Checks for specific malicious keywords in the content of the message
     *
     * @return boolean
     */
    public boolean isMalicious() {
        final String[] maliciousWords = { "Bomb", "Attack", "Explosives", "Gun" };
        for (String word : maliciousWords) {
            if (content.contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.join(
                "\n",
                "This SMS has the following info",
                String.format("Between %s --- %s", firstNumber, secondNumber),
                String.format("on %d/%d/%d", year, month, day),
                String.format("Text: %s", content));
    }

}
