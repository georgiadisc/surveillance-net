package com.github.georgiadisc.net.utility;

public enum Pages {

    HOME("HomePage"), SUSPECT("SuspectPage"), NETWORK("NetworkPage");

    private final String id;

    Pages(String name) {
        id = name;
    }

    @Override
    public String toString() {
        return id;
    }

}
