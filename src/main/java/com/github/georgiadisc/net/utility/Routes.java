package com.github.georgiadisc.net.utility;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Router is a helper class which stores the added pages, including an instance of the current page.
 * Router handles navigation inside the application, like opening and closing pages.
 */
public final class Routes {

    private static final Map<String, Page> pages = new HashMap<>();
    private static Page currPage;

    private Routes() {}

    /**
     * Adds the specified page to the router.
     *
     * @param page The page to be added
     */
    public static void addPage(Page page) {
        pages.put(page.getId(), page);
        page.build();
    }

    /**
     * Opens the next page, closes the current one, and sets the new current page.
     *
     * @param nextPage The page to be shown
     */
    public static void pushPage(Pages nextPage) {
        Page p = pages.get(nextPage.toString());
        if (p == null) {
            JOptionPane.showMessageDialog(getCurrFrame(), String.format("%s Not Found", nextPage));
        } else {
            p.update();
            p.open();
            if (currPage != null) {
                currPage.close();
            }
            currPage = p;
        }
    }

    public static JFrame getCurrFrame() {
        return currPage.getFrame();
    }

}
