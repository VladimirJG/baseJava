package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage{
    @Override
    public void save(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void update(Resume resume) {

    }
    protected abstract Object getSearchKey(String uuid);

}
