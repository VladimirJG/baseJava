package com.basejava.webapp.util;

import com.basejava.webapp.model.*;

import java.time.Month;
import java.util.UUID;


public class ResumeTestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final String FULL_NAME_1 = "Name1";
    public static final String FULL_NAME_2 = "Name2";
    public static final String FULL_NAME_3 = "Name3";
    public static final String FULL_NAME_4 = "Name4";
    public static final Resume RESUME_1;
    public static final Resume RESUME_2;
    public static final Resume RESUME_3;
    public static final Resume RESUME_4;
    public static final String UUID_NOT_EXIST;

    /*private final Resume RESUME_1 = ResumeTestData.createResume(UUID_1, FULL_NAME_1);
    private final Resume RESUME_2 = ResumeTestData.createResume(UUID_2, FULL_NAME_2);
    private final Resume RESUME_3 = ResumeTestData.createResume(UUID_3, FULL_NAME_3);
    private final Resume RESUME_4 = ResumeTestData.createResume(UUID_4, FULL_NAME_4);*/
    static {
        RESUME_1 = ResumeTestData.createResume(UUID_1, FULL_NAME_1);
        RESUME_2 = ResumeTestData.createResume(UUID_2, FULL_NAME_2);
        RESUME_3 = ResumeTestData.createResume(UUID_3, FULL_NAME_3);
        RESUME_4 = ResumeTestData.createResume(UUID_4, FULL_NAME_4);
        UUID_NOT_EXIST = "dummy";
    }

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

    public static Resume createRII(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContacts(ContactType.SKYPE, "565897444");
        resume.addContacts(ContactType.GITHUB, "HOB");
        return resume;
    }

    private static void printResume(Resume resume) {
        System.out.println(resume);
    }
}
