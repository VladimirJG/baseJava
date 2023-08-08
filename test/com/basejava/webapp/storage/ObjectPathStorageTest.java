package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializer.ObjectStreamSerializer;

class ObjectPathStorageTest extends AbstractStorageTest {

    protected ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toPath(), new ObjectStreamSerializer()));
    }
}