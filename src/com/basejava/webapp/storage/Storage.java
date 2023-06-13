package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public interface Storage {

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();

    void update(Resume resume);
}