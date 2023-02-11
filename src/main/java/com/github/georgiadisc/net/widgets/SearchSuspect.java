package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.models.Suspect;
import com.github.georgiadisc.net.utility.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class SearchSuspect extends Widget {

    private final SizedBox box;
    private final TextPrompt prompt;
    private final JButton button;

    /**
     * A widget showing a text field and a button. Used for searching the suspect's information.
     */
    public SearchSuspect() {
        this.box = new SizedBox(0, 0);
        this.prompt = new TextPrompt("Please enter suspect's name");
        this.button = new JButton("Find");
    }

    @Override
    public void build() {
        setUpdateFrequency(Frequency.ALWAYS);
        prompt.addKeyListener(new KeyboardListener());
        button.addActionListener(new ButtonListener());
        add(box);
        add(prompt);
        add(button);
    }

    @Override
    public void update() {
        if (prompt.hasText()) {
            prompt.reset();
        }
        box.requestFocus();
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (prompt.hasText()) {
                final String name = prompt.getText();
                Suspect s = db.getRegistry().getSuspectByName(name);
                if (s == null) {
                    JOptionPane.showMessageDialog(Routes.getCurrFrame(),
                            String.format("Suspect %s Not Found", name));
                } else {
                    db.setSuspect(s);
                    Routes.pushPage(Pages.SUSPECT);
                }
            }
        }

    }

    private class KeyboardListener extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                button.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

}
