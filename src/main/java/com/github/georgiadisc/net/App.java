package com.github.georgiadisc.net;

import com.github.georgiadisc.net.models.*;
import com.github.georgiadisc.net.pages.HomePage;
import com.github.georgiadisc.net.pages.NetworkPage;
import com.github.georgiadisc.net.pages.SuspectPage;
import com.github.georgiadisc.net.utility.Database;
import com.github.georgiadisc.net.utility.Pages;
import com.github.georgiadisc.net.utility.Routes;

public class App {

    public static void main(String[] args) {

        Suspect s1 = new Suspect("John Dow", "Sleepy Dog", "Spain", "Barcelona");
        s1.addNumber("00496955444444");
        s1.addNumber("00496955333333");

        Suspect s2 = new Suspect("Danny Rust", "Rusty Knife", "UK", "London");
        s2.addNumber("00446999888888");

        Suspect s3 = new Suspect("Bob Robson", "Frozen Bear", "Spain", "Oslo");
        s3.addNumber("00478484777777");
        s3.addNumber("00478484666666");
        s3.addNumber("00478484222222");

        Suspect s4 = new Suspect("John Papas", "Quick knife", "Greece", "Athens");
        s4.addNumber("0030210567888");

        Communication[] catalogue = new Communication[15];

        catalogue[0] = new PhoneCall("00496955444444", "00478484777777", 15, 10, 2022, 127);
        catalogue[1] = new PhoneCall("00496955444444", "00478484777777", 16, 10, 2022, 240);
        catalogue[2] = new PhoneCall("00446999888888", "00496955333333", 17, 10, 2022, 52);
        catalogue[3] = new PhoneCall("00446999888888", "00478484777777", 18, 10, 2022, 180);
        catalogue[4] = new PhoneCall("00478484666666", "00496955333333", 19, 10, 2022, 305);
        catalogue[5] = new PhoneCall("00496955444444", "00478484222222", 20, 10, 2022, 247);
        catalogue[6] = new PhoneCall("00478484222222", "00496955333333", 21, 10, 2022, 32);

        catalogue[7] = new SMS("00496955444444", "00478484777777", 10, 10, 2022, "fancy a drink tonight?");
        catalogue[8] = new SMS("00496955333333", "00446999888888", 11, 10, 2022, "Nitro Bomb prepared");
        catalogue[9] = new SMS("00446999888888", "00496955444444", 12, 10, 2022, "flying to Berlin tomorrow");
        catalogue[10] = new SMS("00478484777777", "00446999888888", 13, 10, 2022, "No internet connection today");
        catalogue[11] = new SMS("00478484777777", "00446999888888", 14, 10, 2022, "Gun Received from Rusty Knife");
        catalogue[12] = new SMS("00478484777777", "00446999888888", 15, 10, 2022, "Metro Attack ready");
        catalogue[13] = new SMS("00478484666666", "00446999888888", 16, 10, 2022, "Explosives downtown have been placed");
        catalogue[14] = new SMS("0030210567888", "00478484222222", 22, 10, 2022, "Meet you at Oslo");

        Registry registry = new Registry();

        registry.addSuspect(s1);
        registry.addSuspect(s2);
        registry.addSuspect(s3);
        registry.addSuspect(s4);

        for (int i = 0; i < 15; i++) {
            registry.addCommunication(catalogue[i]);
        }

        registry.buildGraph();

        Database db = Database.getInstance();
        db.setRegistry(registry);

        Routes.addPage(new HomePage());
        Routes.addPage(new SuspectPage());
        Routes.addPage(new NetworkPage());
        Routes.pushPage(Pages.HOME);

    }
}
