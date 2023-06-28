package com.basejava.webapp.storage;

public class MapNameStorage extends AbstractMapStorage {



    @Override
    protected Object getSearchKey(String fullName) {
        storage.get(fullName);
        return fullName;
    }
}
