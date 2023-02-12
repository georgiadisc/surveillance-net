package com.github.georgiadisc.net.utility;

import javax.swing.*;
import java.awt.*;

/**
 * The parent class of every page. Pages extending this class get their own id and can later be
 * added to the Router.
 */
public abstract class Page {

    private final String id;
    private final JFrame frame;
    private final Dimension dimension;

    protected Page(int width, int height) {
        this.id = getClass().getSimpleName();
        this.frame = new JFrame();
        this.dimension = new Dimension(width, height);
    }

    protected Page() {
        this(0, 0);
    }

    protected String getId() {
        return id;
    }

    protected JFrame getFrame() {
        return frame;
    }

    protected void open() {
        if (dimension.getWidth() < frame.getPreferredSize().getWidth()
                || dimension.getHeight() < frame.getPreferredSize().getHeight()) {
            frame.pack();
        } else {
            frame.setSize(dimension);
        }
        frame.setVisible(true);
    }

    protected void close() {
        frame.setVisible(false);
    }

    /**
     * Adds a new {@link Widget} to the Page's frame.
     *
     * @param widget The new widget
     */
    protected void addSubview(Widget widget) {
        frame.getContentPane().add(widget.getContainer());
    }

    protected void setPreferences(String title) {
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Builds the widgets of the {@link Page}.
     */
    public abstract void build();

    /**
     * Updates the widgets of the {@link Page}.
     */
    public abstract void update();

}
