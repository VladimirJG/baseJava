package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage.set(searchKey, resume);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected List<Resume> doGetCopyList() {
        return new ArrayList<>(storage);
    }
}
