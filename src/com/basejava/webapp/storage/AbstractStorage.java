package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void save(Resume resume) {
        Object searchKey = getNotExistingSearchKey(getKey(resume));
        doSave(searchKey, resume);

    }

    @Override
    public final Resume get(String key) {
        Object searchKey = getExistingSearchKey(key);
        return doGet(searchKey);
    }

    @Override
    public final void delete(String key) {
        Object searchKey = getExistingSearchKey(key);
        doDelete(searchKey);
    }

    @Override
    public final void update(Resume resume) {
        Object searchKey = getExistingSearchKey(getKey(resume));
        doUpdate(searchKey, resume);
    }

    private Object getExistingSearchKey(String key) {
        Object searchKey = getSearchKey(key);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(key);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String key) {
        Object searchKey = getSearchKey(key);
        if (isExist(searchKey)) {
            throw new ExistStorageException(key);
        }
        return searchKey;
    }

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String key);

    protected abstract String getKey(Resume resume);
}