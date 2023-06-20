package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.util.Arrays;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];

        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new resume");
        Class field1 = resume.getClass();
        System.out.println(Arrays.toString(field1.getDeclaredFields()));
        System.out.println(Arrays.toString(new Field[]{field}));
        System.out.println(resume);
    }
}