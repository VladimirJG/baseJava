package com.basejava.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<String> strings;

    public ListSection(List<String> sectionDescription) {
        Objects.requireNonNull(sectionDescription, "sectionDescription must not be null");
        this.strings = sectionDescription;
    }

    public ListSection(String... sectionDescription) {
        this(Arrays.asList(sectionDescription));
    }


    public List<String> getStrings() {
        return strings;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "strings=" + strings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }
}
