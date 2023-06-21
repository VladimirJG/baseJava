package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume resume) {
        if (storage.contains(resume)){
            throw new ExistStorageException(resume.getUuid());
        }
        storage.add(resume);
    }

    @Override
    public Resume get(String uuid) {
       // int index = getIndex(uuid);
        Resume newResume = new Resume(uuid);
        int index = storage.indexOf(newResume);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
       /* int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        storage.remove(index);*/
        Resume newResume = new Resume(uuid);
        int index = storage.indexOf(newResume);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
         storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        Resume[] newArray = new Resume[storage.size()];
        return storage.toArray(newArray);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void update(Resume resume) {
       /* int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        storage.set(index, resume);*/
        int index = storage.indexOf(resume);
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        storage.set(index, new Resume("update " + resume));
    }

    /*public int getIndex(Resume resume) {
        return storage.indexOf(resume.getUuid());
    }*/
}
