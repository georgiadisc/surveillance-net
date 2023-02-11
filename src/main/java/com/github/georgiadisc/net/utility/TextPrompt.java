package com.github.georgiadisc.net.utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.Serial;

/**
 * A simple text prompt component, trying to imitate HTML forms.
 */
public final class TextPrompt extends JTextField {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String placeholder;

    public TextPrompt(String text) {
        super(text);
        this.placeholder = text;
        this.setOpacity(0.5f);
        this.setPreferredSize(getPreferredSize());
        this.addFocusListener(new TextPromptListener());
    }

    public boolean hasText() {
        return !(getText().isEmpty() || getText().equals(placeholder));
    }

    public void reset() {
        setOpacity(0.5f);
        setText(placeholder);
    }

    private void setOpacity(float alpha) {
        int a = (int) (alpha * 255);
        a = a > 255 ? 255 : Math.max(a, 0);
        Color fg = getForeground();
        int red = fg.getRed();
        int green = fg.getGreen();
        int blue = fg.getBlue();
        Color withAlpha = new Color(red, green, blue, a);
        setForeground(withAlpha);
    }

    private class TextPromptListener extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            if (getText().equals(placeholder)) {
                setText("");
                setOpacity(1f);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (getText().isEmpty()) {
                setOpacity(0.5f);
                setText(placeholder);
            }
        }

    }

}
