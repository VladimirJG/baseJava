package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Field;

abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size(), "List not cleared");
    }

    @org.junit.jupiter.api.Test
    void save() {
        Assertions.assertNotNull(UUID_1);
    }

    @org.junit.jupiter.api.Test
    void get() {

        Field[] fields = storage.getClass().getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            f.get(storage);
        }

    }

    @org.junit.jupiter.api.Test
    void delete() {
        Assertions.assertNotNull(UUID_3);
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        size();
    }

    @org.junit.jupiter.api.Test
    void size() {
        if (assertSize(storage.size())) {
            System.out.println("Equals");
        }
        System.out.println("Size does not match");
    }

    @org.junit.jupiter.api.Test
    void update() {
        Assertions.assertEquals("uuid1", UUID_1);
    }

    @org.junit.jupiter.api.Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @org.junit.jupiter.api.Test
    void saveOnOverflow() {
        storage.clear();
        Assertions.assertThrows(StorageException.class, () -> {
            try {
                storage.save(new Resume(UUID_1));
                storage.save(new Resume(UUID_2));
                storage.save(new Resume(UUID_3));
            } catch (StorageException se) {
                Assertions.fail(se.getMessage());
            }
        });
    }

    private boolean assertSize(int size) {
        return storage.size() == size;
    }

    private boolean assertGet(Resume resume) throws NoSuchFieldException, IllegalAccessException {
        Field value = resume.getClass().getDeclaredField(resume.getUuid());
        value.setAccessible(true);
        return value.get(resume).equals(storage.get(resume.getUuid()));
    }
}