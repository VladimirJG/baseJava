package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MapStorageTest {
    private final MapStorage mapStorage = new MapStorage();
    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";
    private final String UUID_4 = "uuid4";
    private final Resume RESUME_1 = new Resume(UUID_1);
    private final Resume RESUME_2 = new Resume(UUID_2);
    private final Resume RESUME_3 = new Resume(UUID_3);
    private final Resume RESUME_4 = new Resume(UUID_4);
    private final String UUID_NOT_EXIST = "dummy";

    @BeforeEach
    void setUp() {
        mapStorage.save(RESUME_1);
        mapStorage.save(RESUME_2);
        mapStorage.save(RESUME_3);
    }

    @AfterEach
    void tearDown() {
        mapStorage.clear();
    }

    @Test
    void save() {
        mapStorage.save(RESUME_4);
        assertSize(4);
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> mapStorage.save(RESUME_1));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_4);
        assertGet(RESUME_3);
    }

    @Test
    void delete() {
        mapStorage.delete(RESUME_1.getUuid());
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> mapStorage.get(RESUME_1.getUuid()));
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> mapStorage.delete(RESUME_4.getUuid()));
    }

    @Test
    void update() {
        Resume testVariable = new Resume(UUID_1);
        mapStorage.update(testVariable);
        Assertions.assertSame(testVariable, mapStorage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> mapStorage.update(RESUME_4));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> mapStorage.get(UUID_NOT_EXIST));
    }

    @Test
    void doGet() {
    }

    @Test
    void doSave() {
    }

    @Test
    void doDelete() {
    }

    @Test
    void doUpdate() {
    }

    @Test
    void isExist() {
    }

    @Test
    void getSearchKey() {
    }

    @Test
    void clear() {
        mapStorage.clear();
        assertSize(0);
    }

    @Test
    void getAll() {
        Resume[] actual = mapStorage.getAll();
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void size() {
        assertSize(3);
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(mapStorage.get(resume.getUuid()), resume);
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, mapStorage.size());
    }
}