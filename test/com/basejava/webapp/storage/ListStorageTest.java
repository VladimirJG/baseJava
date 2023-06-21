package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListStorageTest {
    private final ListStorage listStorage;
    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";
    private final Resume RESUME_1 = new Resume(UUID_1);
    private final Resume RESUME_2 = new Resume(UUID_2);
    private final Resume RESUME_3 = new Resume(UUID_3);
    ListStorageTest(ListStorage listStorage) {
        this.listStorage = listStorage;
    }

    @BeforeEach
    public void setUp() {
        listStorage.clear();
        listStorage.save(RESUME_1);
        listStorage.save(RESUME_2);
        listStorage.save(RESUME_3);

    }

    @Test
    void clear() {
        listStorage.clear();
        Assertions.assertEquals(0, listStorage.size());
    }

    @Test
    void save() {
        Assertions.assertEquals(3, listStorage.size());
    }

    @Test
    void get() {

    }

    @Test
    void delete() {
        listStorage.delete(UUID_3);
        Assertions.assertThrows(NotExistStorageException.class,() -> listStorage.get(UUID_3));
    }

    @Test
    void getAll() {
        listStorage.getAll();
    }

    @Test
    void size() {
    }

    @Test
    void update() {
    }

    @Test
    void getIndex() {
    }
}