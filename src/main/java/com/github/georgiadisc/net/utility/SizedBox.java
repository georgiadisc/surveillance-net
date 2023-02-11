package com.github.georgiadisc.net.utility;

import javax.swing.*;
import java.io.Serial;

/**
 * A simple focusable box component. Draws "attention" to itself allowing other components, like
 * text fields, to keep their default state.
 */
public final class SizedBox extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    public SizedBox(int width, int height) {
        this.setSize(width, height);
        this.setFocusable(true);
    }

}
