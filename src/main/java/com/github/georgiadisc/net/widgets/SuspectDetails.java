package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.models.Suspect;
import com.github.georgiadisc.net.utility.Widget;

import javax.swing.*;
import java.util.List;

public final class SuspectDetails extends Widget {

    private final JTextArea name;
    private final JTextArea codeName;
    private final JTextArea view;
    private final JScrollPane pane;

    /**
     * A widget showing the {@link Suspect}'s personal information, such as name, codename, and
     * phone numbers in use.
     */
    public SuspectDetails() {
        this.name = new JTextArea();
        this.codeName = new JTextArea();
        this.view = new JTextArea();
        this.pane = new JScrollPane(view);
    }

    @Override
    public void build() {
        addBorder();
        setUpdateFrequency(Frequency.CHANGE_OF_STATE);
        name.setEditable(false);
        codeName.setEditable(false);
        view.setEditable(false);
        add(name);
        add(codeName);
        add(pane);
    }

    @Override
    public void update() {
        final Suspect suspect = db.getSuspect();
        final List<String> numbers = suspect.getNumbers();
        name.setText(suspect.getName());
        codeName.setText(suspect.getCodeName());
        view.setText("");
        if (numbers.isEmpty()) {
            view.setRows(Row.SM.getSize());
            view.setColumns(Column.SM.getSize());
        } else {
            for (int i = 0; i < numbers.size() - 1; i++) {
                view.append(suspect.getNumbers().get(i));
                view.append("\n");
            }
            view.append(suspect.getNumbers().get(numbers.size() - 1));
            view.setRows(Math.min(view.getLineCount(), Row.MD.getSize()));
        }
        setPreferences();
    }

}
