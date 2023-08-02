package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


public class ResumeTestData {
    public static void main(String[] args) {
        List<Company> company = new ArrayList<>();
        company.add(new Company("Basa", "Claiton",
                LocalDate.of(2010, 5, 21),
                LocalDate.of(2020, 11, 30),
                "Bigger poke buttons",
                "Проектирование и разработка онлайн платформы управления проектами " +
                        "Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));

        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.TELEPHONE, "1544-1544");
        contacts.put(ContactType.MAIL, "@mail.com");
        contacts.put(ContactType.LINKEDIN, "InDinLink");
        contacts.put(ContactType.HOMEPAGE, "tratata@55.ru");
        contacts.put(ContactType.SKYPE, "565897444");
        contacts.put(ContactType.GITHUB, "HOB");

        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
        sections.put(SectionType.EXPERIENCE, new CompanySection(company));
        sections.put(SectionType.EDUCATION, new TextSection("Higher"));
        sections.put(SectionType.ACHIEVEMENT, new TextSection("Ate 7 cutlets in 5 minutes"));
        sections.put(SectionType.OBJECTIVE, new TextSection("Above"));
        sections.put(SectionType.QUALIFICATIONS, new TextSection("poke buttons"));
        Resume resume = createResume("17", "Panda", contacts, sections);

        printResume(resume);
    }

    public static Resume createResume(String uuid, String fullName, Map<ContactType, String> contacts, Map<SectionType, AbstractSection> sections) {
        return new Resume(uuid, fullName, contacts, sections);
    }

    public static void printResume(Resume resume) {
        System.out.println(resume);
    }
}
