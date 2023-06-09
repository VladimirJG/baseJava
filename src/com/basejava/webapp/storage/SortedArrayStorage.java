package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, count, key);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            System.err.println("The uuid " + resume.getUuid() + " being entered is already in the summary list");
        } else {
            for (int i = count; i >= (index * -1) - 1; i--) {
                if (i == (index * -1) - 1) {
                    storage[i] = resume;
                    break;
                } else {
                    storage[i] = storage[i + 1];
                }
            }
            count++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.err.println("The uuid" + uuid + " you entered does not exist.");
        } else {
            for (int i = index; i <= count; i++) {
                storage[i] = storage[i + 1];
            }
            count--;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.err.println("The uuid" + resume.getUuid() + " you entered does not exist.");
        } else {
            storage[index].setUuid("Update_2");
        }
    }
}
