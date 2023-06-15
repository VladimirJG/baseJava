package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {


    @Override
    protected final void insertElement(Resume resume, int index) {
        storage[count] = resume;
    }

    @Override
    protected final void refillVoid(int index) {
        storage[index] = storage[count - 1];
    }

    protected final int getIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}