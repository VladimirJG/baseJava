package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int count;

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume resume) {
        if (count == storage.length) {
            System.err.println("Unable to add a new resume" + resume + ". The resume list is full.");
        } else if (storage[count] == null) {
            storage[count] = resume;
            count++;
        }
    }

    public Resume get(String uuid) {
        if (isEmpty(storage)) {
            System.err.println("Resume list is empty.Requested uuid " + uuid + " is missing");
        } else {
            for (int i = 0; i < count - 1; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (isEmpty(storage)) {
            System.err.println("Resume list is empty.Requested uuid " + uuid + " is missing");
        } else {
            for (int i = 0; i < count - 1; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[count - 1];
                    storage[count - 1] = null;
                    count--;
                    break;
                }
            }
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
        if (isEmpty(storage)) {
            System.err.println("Resume list is empty.Requested resume " + resume + " is missing");
        } else {
            for (Resume res : storage) {
                if (res.equals(resume)) {
                    System.out.println(res + " " + "update");
                    break;
                }
            }
        }
    }

    private boolean isEmpty(Resume[] storage) {
        return storage[0] == null;
    }
}
