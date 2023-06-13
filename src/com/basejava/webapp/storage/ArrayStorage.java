package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    @Override
    protected void saveResume(Resume resume, int index) {
        storage[count] = resume;
        count++;
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[count - 1];
        storage[count - 1] = null;
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        storage[index] = resume;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}