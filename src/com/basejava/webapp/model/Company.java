package com.basejava.webapp.model;

import com.basejava.webapp.util.JsonLocalDateAdapter;
import com.basejava.webapp.util.LocalDateAdapter;
import com.google.gson.annotations.JsonAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.basejava.webapp.util.DateUtil.NOW;
import static com.basejava.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final Company EMPTY= new Company("","", Position.EMPTY);
    private Link homePage;
    private List<Position> positions = new ArrayList<>();

    public Company() {
    }

    public Company(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Company(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) && Objects.equals(positions, company.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    @Override
    public String toString() {
        return "Company{" + homePage + positions + '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        public static final Position EMPTY = new Position();
        @JsonAdapter(JsonLocalDateAdapter.class)
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @JsonAdapter(JsonLocalDateAdapter.class)
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Position() {
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must be not null");
            Objects.requireNonNull(endDate, "endDate must be not null");
            Objects.requireNonNull(title, "title must be not null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description == null ? "" : description;
        }

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(endDate, position.endDate) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return "Position(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
        }
    }
}
