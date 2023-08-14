package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.Month;


public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContacts(ContactType.TELEPHONE, "1544-1544");
        resume.addContacts(ContactType.MAIL, "@mail.com");
        resume.addContacts(ContactType.LINKEDIN, "InDinLink");
        resume.addContacts(ContactType.HOMEPAGE, "tratata@55.ru");
        resume.addContacts(ContactType.SKYPE, "565897444");
        resume.addContacts(ContactType.GITHUB, "HOB");

        resume.addSections(SectionType.EXPERIENCE, new CompanySection(new Company("GDA", "https://@gda.ru",
                new Company.Position(2018, Month.MAY, "Lawyer", "contracts"),
                new Company.Position(2013, Month.AUGUST, 2018, Month.MAY, "Lawyer", "pre-trial execution"))));
        resume.addSections(SectionType.PERSONAL, new TextSection("Personal data"));
        resume.addSections(SectionType.EDUCATION, new CompanySection(
                new Company("Institute", null,
                        new Company.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                        new Company.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                new Company("Organization12", "http://Organization12.ru")));
        resume.addSections(SectionType.ACHIEVEMENT, new ListSection("Ate 7 cutlets in 5 minutes", "Wrote several standalone programs"));
        resume.addSections(SectionType.OBJECTIVE, new TextSection("Programmer"));
        resume.addSections(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));

        return resume;
    }

    private static void printResume(Resume resume) {
        System.out.println(resume);
    }
}
