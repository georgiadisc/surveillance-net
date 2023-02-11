package com.github.georgiadisc.net.pages;

import com.github.georgiadisc.net.utility.Page;
import com.github.georgiadisc.net.widgets.*;

public final class SuspectPage extends Page {

    private final Column column;

    /**
     * The suspect page of our application, which includes the suspect's details, malicious messages
     * exchanged with a specified number, their current and suggested partners, and the suspects
     * coming from their home country.
     */
    public SuspectPage() {
        super(600, 500);
        super.setPreferences("Suspect Page");
        this.column = new Column(
                new SuspectDetails(),
                new MaliciousMessages(),
                new Partners(),
                new SuggestedPartners(),
                new SuspectsFromCountry(),
                new SuspectsFromCity(),
                new ReturnHome());
    }

    @Override
    public void build() {
        column.build();
        addSubview(column);
    }

    @Override
    public void update() {
        column.update();
    }

}
