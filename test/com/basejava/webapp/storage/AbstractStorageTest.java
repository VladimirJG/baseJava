package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.*;

import java.util.*;

abstract class AbstractStorageTest {
    private final Storage storage;
    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";
    private final String UUID_4 = "uuid4";
    private final Resume RESUME_1 = new Resume(UUID_1);
    private final Resume RESUME_2 = new Resume(UUID_2);
    private final Resume RESUME_3 = new Resume(UUID_3);
    private final Resume RESUME_4 = new Resume(UUID_4);
    private final String UUID_NOT_EXIST = "dummy";

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @AfterEach
    void tearDown() {
        storage.clear();
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void delete() {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(RESUME_1.getUuid()));
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(RESUME_4.getUuid()));
    }

    @Test
    void size() {
        assertSize(3);
    }


    @Test
    void update() {
        Resume testVariable = new Resume(UUID_1);
        storage.update(testVariable);
        Assertions.assertSame(testVariable, storage.get(UUID_1));
    }

    @Test
    void updateNotSame() {
        Resume testVariable = new Resume(UUID_1);
        storage.update(testVariable);
        Assertions.assertNotSame(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    void getAllSorted() {
        List<Resume> actual = storage.getAllSorted();
        List<Resume> expected = List.of(RESUME_1, RESUME_2, RESUME_3);
        if (storage.getClass() == MapStorage.class) {
            Collections.sort(actual);
        }
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void saveOverFlow() {
        Assumptions.assumeTrue(Objects.equals(storage.getClass(), SortedArrayStorage.class) ||
                Objects.equals(storage.getClass(), ArrayStorage.class));
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException se) {
            Assertions.fail(se.getMessage());
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_4));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(storage.get(resume.getUuid()), resume);
    }
}