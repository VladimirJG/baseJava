package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    public static int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doSave(Integer searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow ", resume.getUuid());
        }
        insertElement(resume, searchKey);
        size++;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    public void doDelete(Integer searchKey) {
        refillVoid(searchKey);
        storage[size - 1] = null;
        size--;

    }

    @Override
    public void doUpdate(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    public List<Resume> doGetCopyList() {
        Resume[] copy = Arrays.copyOf(storage, size);
        return Arrays.asList(copy);
    }

    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void refillVoid(int index);
}