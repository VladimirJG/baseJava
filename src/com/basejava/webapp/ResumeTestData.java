package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.Month;


public class ResumeTestData {
    public static void main(String[] args) {
        Resume RESUME = createResume("11", "Dad V.V.");

        RESUME.setContacts(ContactType.TELEPHONE, "1544-1544");
        RESUME.setContacts(ContactType.MAIL, "@mail.com");
        RESUME.setContacts(ContactType.LINKEDIN, "InDinLink");
        RESUME.setContacts(ContactType.HOMEPAGE, "tratata@55.ru");
        RESUME.setContacts(ContactType.SKYPE, "565897444");
        RESUME.setContacts(ContactType.GITHUB, "HOB");

        RESUME.setSections(SectionType.EXPERIENCE, new CompanySection(new Company("GDA", "https//:@gda.ru",
                new Company.Position(2018, Month.MAY, "Lawyer", "contracts"),
                new Company.Position(2013, Month.AUGUST, 2018, Month.MAY, "Lawyer", "pre-trial execution"))));
        RESUME.setSections(SectionType.PERSONAL, new ListSection("ListSection", "Strong logic", "Creativity"));
        RESUME.setSections(SectionType.EDUCATION, new TextSection("Higher"));
        RESUME.setSections(SectionType.ACHIEVEMENT, new ListSection("Ate 7 cutlets in 5 minutes", "Wrote several standalone programs"));
        RESUME.setSections(SectionType.OBJECTIVE, new TextSection("Programmer"));
        RESUME.setSections(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));

        System.out.println(RESUME.getSection(SectionType.EXPERIENCE));
    }

    public static Resume createResume(String uuid, String fullName) {
        return new Resume(uuid, fullName);
    }

    public static void printResume(Resume resume) {
        System.out.println(resume);
    }
}
