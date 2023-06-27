package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    public static int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doSave(Object searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow ", resume.getUuid());
        }
        insertElement(resume, (int) searchKey);
        size++;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    public void doDelete(Object searchKey) {
        refillVoid((int) searchKey);
        storage[size - 1] = null;
        size--;

    }

    @Override
    public void doUpdate(Object searchKey, Resume resume) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    public List<Resume> getAllSorted() {
        Resume[] copySt = Arrays.copyOf(storage, size);
        return Arrays.asList(copySt);
    }

    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void refillVoid(int index);
}