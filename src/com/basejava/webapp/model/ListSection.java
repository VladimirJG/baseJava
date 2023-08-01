package com.basejava.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> listOfTexts;

    public ListSection(List<String> sectionDescription) {
        this.listOfTexts = sectionDescription;
    }

    public List<String> getListOfTexts() {
        return listOfTexts;
    }
}
