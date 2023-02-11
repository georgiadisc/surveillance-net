package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.utility.Widget;

import javax.swing.*;

public final class DiameterView extends Widget {

    private final JTextArea view;

    /**
     * A widget showing the diameter of the suspects' network.
     */
    public DiameterView() {
        this.view = new JTextArea();
    }

    @Override
    public void build() {
        setUpdateFrequency(Frequency.NEVER);
        view.append(String.format("Diameter = %d", db.getRegistry().getDiameter()));
        view.setEditable(false);
        add(view);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException();
    }

}
