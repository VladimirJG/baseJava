package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10_000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int count;

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count == storage.length) {
            System.err.println("Unable to add a new resume " + resume + ". The resume list is full.");
        } else if (index >= 0) {
            System.err.println("The resume exists. Resume number " + index);
        } else if (storage[count] == null) {
            storage[count] = resume;
            count++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.err.println("Requested uuid " + uuid + " is missing");
        } else {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.err.println("Requested uuid " + uuid + " impossible to delete. Uuid is missing");
        } else {
            storage[index] = storage[count - 1];
            storage[count - 1] = null;
            count--;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    public int size() {
        return count;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.err.println("Requested resume " + resume + " impossible to update.The requested resume does not exist");
        } else {
            System.out.println(storage[index] + " " + "update");
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
