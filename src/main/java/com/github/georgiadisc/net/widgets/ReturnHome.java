package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.utility.Pages;
import com.github.georgiadisc.net.utility.Routes;
import com.github.georgiadisc.net.utility.Widget;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ReturnHome extends Widget {

    private final JButton button;

    /**
     * A widget showing a return home button.
     */
    public ReturnHome() {
        this.button = new JButton("Return to Search Screen");
    }

    @Override
    public void build() {
        setUpdateFrequency(Frequency.NEVER);
        button.addActionListener(new ButtonListener());
        add(button);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException();
    }

    private static class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Routes.pushPage(Pages.HOME);
        }

    }

}
