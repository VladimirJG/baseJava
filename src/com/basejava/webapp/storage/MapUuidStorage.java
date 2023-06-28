package com.basejava.webapp.storage;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        storage.get(uuid);
        return uuid;
    }
}
