package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected final void insertElement(Resume resume, int index) {
        for (int i = count; i >= (index * -1) - 1; i--) {
            if (i == (index * -1) - 1) {
                storage[i] = resume;
                break;
            } else {
                storage[i] = storage[i + 1];
            }
        }
    }

    @Override
    protected final void refillVoid(int index) {
        System.arraycopy(storage, index + 1, storage, index, storage.length - 1 - index);
    }

    @Override
    protected final int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, count, key);
    }
}