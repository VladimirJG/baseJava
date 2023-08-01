package com.basejava.webapp.model;

public enum ContactType {
    TELEPHONE("Number"),
    SKYPE("Skype"),
    MAIL("Mail"),
    LINKEDIN("Linkedin"),
    GITHUB("Github"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("Homepage");
    private final String name;

    public String getName() {
        return name;
    }

    ContactType(String name) {
        this.name = name;
    }

}
