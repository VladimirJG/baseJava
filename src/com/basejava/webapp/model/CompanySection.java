package com.basejava.webapp.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    private final String nameCompany;
    private final String jobTitle;
    private final Date workPeriod;
    private final List<String> jobDescription;

    public CompanySection(String nameCompany, String jobTitle, Date workPeriod, List<String> jobDescription) {
        this.nameCompany = nameCompany;
        this.jobTitle = jobTitle;
        this.workPeriod = workPeriod;
        this.jobDescription = jobDescription;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Date getWorkPeriod() {
        return workPeriod;
    }

    public List<String> getJobDescription() {
        return jobDescription;
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "nameCompany='" + nameCompany + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", workPeriod=" + workPeriod +
                ", jobDescription=" + jobDescription +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(nameCompany, that.nameCompany) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(workPeriod, that.workPeriod) && Objects.equals(jobDescription, that.jobDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCompany, jobTitle, workPeriod, jobDescription);
    }
}
