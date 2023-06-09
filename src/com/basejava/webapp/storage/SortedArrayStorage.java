package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    protected int getIndex(String uuid) {

        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public void update(Resume resume) {

    }
}
