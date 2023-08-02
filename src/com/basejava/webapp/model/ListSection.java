package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> listOfTexts;

    public ListSection(List<String> sectionDescription) {
        this.listOfTexts = sectionDescription;
    }

    public List<String> getListOfTexts() {
        return listOfTexts;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "listOfTexts=" + listOfTexts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(listOfTexts, that.listOfTexts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfTexts);
    }
}