package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, int index) {
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
    protected void deleteResume(int index) {
        for (int i = index; i <= count; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        resume.setUuid("Update 2");
        storage[index] = resume;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, count, key);
    }
}