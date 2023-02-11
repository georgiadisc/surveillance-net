package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.utility.Widget;

import javax.swing.*;
import java.awt.*;

public final class Column extends Widget {

    private final Widget[] children;

    /**
     * A widget which places its children along the vertical axis.
     */
    public Column(Widget... children) {
        this.children = children;
        this.getContainer().setLayout(new BoxLayout(this.getContainer(), BoxLayout.Y_AXIS));
    }

    @Override
    public void build() {
        add(Box.createRigidArea(new Dimension(5, 5)));
        for (Widget child : children) {
            child.build();
            add(child);
            add(Box.createRigidArea(new Dimension(5, 5)));
        }
    }

    @Override
    public void update() {
        for (Widget child : children) {
            if (child.shouldUpdate()) {
                child.update();
            }
        }
    }

}
