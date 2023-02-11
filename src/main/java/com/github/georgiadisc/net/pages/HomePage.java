package com.github.georgiadisc.net.pages;

import com.github.georgiadisc.net.utility.Page;
import com.github.georgiadisc.net.widgets.Column;
import com.github.georgiadisc.net.widgets.SearchSuspect;
import com.github.georgiadisc.net.widgets.VisualizeNetwork;

public final class HomePage extends Page {

    private final Column column;

    /**
     * The home page of our application, which includes a search bar for the suspects saved in the
     * Registry.
     */
    public HomePage() {
        super(300, 200);
        super.setPreferences("Find Suspect");
        this.column = new Column(new SearchSuspect(), new VisualizeNetwork());
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
