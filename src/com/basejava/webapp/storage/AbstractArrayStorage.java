package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count;

    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count == storage.length) {
            System.err.println("Unable to add a new resume " + resume + ". The resume list overflow.");
        } else if (index >= 0) {
            System.err.println("The resume exists. Resume number " + index);
        } else {
            insertElement(resume, index);
            count++;
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.err.println("Requested uuid " + uuid + " is missing");
        } else {
            return storage[index];
        }
        return null;
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.err.println("The uuid" + uuid + " you entered does not exist.");
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
            System.err.println("Requested resume " + resume + " impossible to update.The requested resume does not exist");
        } else {
            updateResume(index, resume);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void updateResume(int index, Resume resume);

    protected abstract void refillVoid(int index);
}