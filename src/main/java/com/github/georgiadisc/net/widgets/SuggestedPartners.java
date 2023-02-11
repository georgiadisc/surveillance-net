package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.models.Suspect;
import com.github.georgiadisc.net.utility.Widget;

import javax.swing.*;
import java.util.List;

public final class SuggestedPartners extends Widget {

    private final JLabel label;
    private final JTextArea view;
    private final JScrollPane pane;

    /**
     * A widget showing the {@link Suspect}'s suggested partners.
     */
    public SuggestedPartners() {
        this.label = new JLabel("Suggested Partners ----->");
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
        final List<Suspect> suggestedPartners = db.getSuspect().getSuggestedPartners();
        view.setText("");
        if (suggestedPartners.isEmpty()) {
            view.setRows(Row.MD.getSize());
            view.setColumns(Column.SM.getSize());
        } else {
            for (int i = 0; i < suggestedPartners.size() - 1; i++) {
                view.append(suggestedPartners.get(i).getName());
                view.append("\n");
            }
            view.append(suggestedPartners.get(suggestedPartners.size() - 1).getName());
            view.setRows(Math.min(view.getLineCount(), Row.MD.getSize()));
        }
        setPreferences();
    }

}
