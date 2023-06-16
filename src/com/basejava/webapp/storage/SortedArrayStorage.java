package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        int newElementIndex = index * -1 - 1;
        System.arraycopy(storage, newElementIndex, storage, newElementIndex + 1, size - newElementIndex);
        storage[newElementIndex] = resume;
    }

    @Override
    protected void refillVoid(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume key = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, key);
    }
}