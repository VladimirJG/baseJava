package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected String getKey(Resume resume) {
        return resume.getUuid();
    }
    @Override
    protected Object getSearchKey(String uuid) {
        storage.get(uuid);
        return uuid;
    }
}
