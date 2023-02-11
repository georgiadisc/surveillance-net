package com.github.georgiadisc.net.pages;

import com.github.georgiadisc.net.utility.Page;
import com.github.georgiadisc.net.widgets.Column;
import com.github.georgiadisc.net.widgets.DiameterView;
import com.github.georgiadisc.net.widgets.GraphView;
import com.github.georgiadisc.net.widgets.ReturnHome;

public final class NetworkPage extends Page {

    private final Column column;

    /**
     * The network page of our application, which includes a graph representing the
     * suspects' network and a widget with the graph's diameter.
     */
    public NetworkPage() {
        super.setPreferences("Suspects Network");
        this.column = new Column(
                new GraphView(),
                new DiameterView(),
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
