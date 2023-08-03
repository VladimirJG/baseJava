package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.Month;


public class ResumeTestData {
    public static void main(String[] args) {
        Resume RESUME = createResume("11", "Dad V.V.");

        RESUME.addContacts(ContactType.TELEPHONE, "1544-1544");
        RESUME.addContacts(ContactType.MAIL, "@mail.com");
        RESUME.addContacts(ContactType.LINKEDIN, "InDinLink");
        RESUME.addContacts(ContactType.HOMEPAGE, "tratata@55.ru");
        RESUME.addContacts(ContactType.SKYPE, "565897444");
        RESUME.addContacts(ContactType.GITHUB, "HOB");

        RESUME.addSections(SectionType.EXPERIENCE, new CompanySection(new Company("GDA", "https//:@gda.ru",
                new Company.Position(2018, Month.MAY, "Lawyer", "contracts"),
                new Company.Position(2013, Month.AUGUST, 2018, Month.MAY, "Lawyer", "pre-trial execution"))));
        RESUME.addSections(SectionType.PERSONAL, new ListSection("ListSection", "Strong logic", "Creativity"));
        RESUME.addSections(SectionType.EDUCATION, new TextSection("Higher"));
        RESUME.addSections(SectionType.ACHIEVEMENT, new ListSection("Ate 7 cutlets in 5 minutes", "Wrote several standalone programs"));
        RESUME.addSections(SectionType.OBJECTIVE, new TextSection("Programmer"));
        RESUME.addSections(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));

        printResume(RESUME);
    }

    public static Resume createResume(String uuid, String fullName) {
        return new Resume(uuid, fullName);
    }

    public static void printResume(Resume resume) {
        System.out.println(resume);
    }
}
