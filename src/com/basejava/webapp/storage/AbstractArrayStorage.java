package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 100_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count;

    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow ",resume.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertElement(resume, index);
            count++;
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            refillVoid(index);
            storage[count - 1] = null;
            count--;
        }
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    public final int size() {
        return count;
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void refillVoid(int index);
}