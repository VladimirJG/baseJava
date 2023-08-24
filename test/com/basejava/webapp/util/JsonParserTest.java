package com.basejava.webapp.util;

import com.basejava.webapp.model.AbstractSection;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.model.TextSection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.basejava.webapp.util.ResumeTestData.RESUME_1;

class JsonParserTest {

    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assertions.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write() throws Exception {
        AbstractSection section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        Assertions.assertEquals(section1, section2);
    }
}