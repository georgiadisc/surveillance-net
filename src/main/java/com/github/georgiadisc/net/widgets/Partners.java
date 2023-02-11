package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.models.Suspect;
import com.github.georgiadisc.net.utility.Widget;

import javax.swing.*;
import java.util.List;

public final class Partners extends Widget {

    private final JLabel label;
    private final JTextArea view;
    private final JScrollPane pane;

    /**
     * A widget showing the {@link Suspect}'s current partners.
     */
    public Partners() {
        this.label = new JLabel("Partners");
        this.view = new JTextArea();
        this.pane = new JScrollPane(view);
    }

    @Override
    public void build() {
        addBorder();
        setUpdateFrequency(Frequency.CHANGE_OF_STATE);
        view.setEditable(false);
        add(label);
        add(pane);
    }

    @Override
    public void update() {
        final List<Suspect> partners = db.getSuspect().getPartners();
        view.setText("");
        if (partners.isEmpty()) {
            view.setRows(Row.MD.getSize());
            view.setColumns(Column.SM.getSize());
        } else {
            for (int i = 0; i < partners.size() - 1; i++) {
                view.append(String.format("%s, %s", partners.get(i).getName(), partners.get(i).getCodeName()));
                view.append("\n");
            }
            view.append(String.format("%s, %s", partners.get(partners.size() - 1).getName(),
                    partners.get(partners.size() - 1).getCodeName()));
            view.setRows(Math.min(view.getLineCount(), Row.MD.getSize()));
        }
        setPreferences();
    }

}
