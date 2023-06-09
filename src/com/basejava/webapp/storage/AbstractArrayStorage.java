package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count;


    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.err.println("Requested uuid " + uuid + " is missing");
        } else {
            return storage[index];
        }
        return null;
    }

    public int size() {
        return count;
    }


    protected abstract int getIndex(String uuid);
}
