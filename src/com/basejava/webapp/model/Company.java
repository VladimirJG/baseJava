package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Company {
    private final Link homePage;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final String title;
    private final String description;

    public Company(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must be not null");
        Objects.requireNonNull(endDate, "endDate must be not null");
        Objects.requireNonNull(title, "title must be not null");
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) && Objects.equals(startDate, company.startDate) && Objects.equals(endDate, company.endDate) && Objects.equals(title, company.title) && Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + homePage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
