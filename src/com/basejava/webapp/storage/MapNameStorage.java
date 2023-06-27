package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class MapNameStorage extends AbstractMapStorage {


    @Override
    protected String getKey(Resume resume) {
        return resume.getFullName();
    }
    @Override
    protected Object getSearchKey(String fullName) {
        storage.get(fullName);
        return fullName;
    }
}
