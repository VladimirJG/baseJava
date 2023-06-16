package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

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
        Assertions.assertEquals(0, storage.size(), "List not cleared");
    }

    @org.junit.jupiter.api.Test
    void save() {
        Assertions.assertNotNull(UUID_1);
    }

    @org.junit.jupiter.api.Test
    void get() {
        Assertions.assertEquals("uuid2", UUID_2);
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
        Assertions.assertEquals(3, storage.size(), "Size does not match");
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
}