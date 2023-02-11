package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.models.SMS;
import com.github.georgiadisc.net.utility.Widget;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class MaliciousMessages extends Widget {

    private final JTextField field;
    private final JTextArea view;
    private final JScrollPane pane;
    private final JButton button;
    private final HashSet<SMS> malicious;
    private String currNumber;

    /**
     * A widget showing the malicious messages exchanged between the suspect and a specified number.
     */
    public MaliciousMessages() {
        this.field = new JTextField();
        this.view = new JTextArea();
        this.pane = new JScrollPane(view);
        this.button = new JButton("Find SMS");
        this.malicious = new HashSet<>();
    }

    @Override
    public void build() {
        addBorder();
        setUpdateFrequency(Frequency.ALWAYS);
        field.addKeyListener(new KeyboardListener());
        button.addActionListener(new ButtonListener());
        field.setColumns(Column.SM.getSize());
        view.setRows(Row.MD.getSize());
        view.setColumns(Column.MD.getSize());
        view.setLineWrap(true);
        view.setEditable(false);
        add(field);
        add(pane);
        add(button);
    }

    @Override
    public void update() {
        currNumber = "";
        field.setText("");
        view.setText("");
        setPreferences();
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(field.getText().isEmpty() || field.getText().equals(currNumber))) {
                currNumber = field.getText();
                malicious.clear();
                view.setText("");
                for (String number : db.getSuspect().getNumbers()) {
                    malicious.addAll(db.getRegistry().getMessagesBetween(field.getText(), number));
                }
                final List<SMS> messages = new ArrayList<>(malicious);
                if (!messages.isEmpty()) {
                    for (int i = 0; i < messages.size() - 1; i++) {
                        view.append(messages.get(i).getContent());
                        view.append("\n");
                    }
                    view.append(messages.get(messages.size() - 1).getContent());
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
