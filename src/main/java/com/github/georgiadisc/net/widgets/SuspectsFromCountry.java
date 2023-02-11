package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.models.Suspect;
import com.github.georgiadisc.net.utility.Widget;

import javax.swing.*;
import java.util.List;

public final class SuspectsFromCountry extends Widget {

    private final JLabel label;
    private final JTextArea view;
    private final JScrollPane pane;

    /**
     * A widget showing the suspects coming from the current {@link Suspect}'s home country.
     */
    public SuspectsFromCountry() {
        this.label = new JLabel();
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
        final List<Suspect> suspectsFromCountry =
                db.getRegistry().getSuspectsFromCountry(db.getSuspect().getCountry());
        final String country = db.getSuspect().getCountry();
        view.setText("");
        label.setText(String.format("Suspects coming from %s", country));
        if (suspectsFromCountry.isEmpty()) {
            view.setRows(Row.MD.getSize());
            view.setColumns(Column.SM.getSize());
        } else {
            for (int i = 0; i < suspectsFromCountry.size() - 1; i++) {
                view.append(suspectsFromCountry.get(i).getName());
                view.append("\n");
            }
            view.append(suspectsFromCountry.get(suspectsFromCountry.size() - 1).getName());
            view.setRows(Math.min(view.getLineCount(), Row.MD.getSize()));
        }
        setPreferences();
    }

}
