package com.basejava.webapp.storage;

import com.basejava.webapp.Config;
import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static com.basejava.webapp.util.ResumeTestData.*;

abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
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
        Resume testVariable = new Resume(UUID_1, "updateName");
        RESUME_1.addContacts(ContactType.MAIL, "mail1@google.com");
        RESUME_1.addContacts(ContactType.SKYPE, "NewSkype");
        RESUME_1.addContacts(ContactType.TELEPHONE, "+7 921 222-22-22");
        storage.update(testVariable);
        Assertions.assertEquals(testVariable, storage.get(UUID_1));
    }

    @Test
    void updateNotSame() {
        Resume testVariable = new Resume(UUID_1, "updateName");
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
        Assertions.assertEquals(expected, actual);
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