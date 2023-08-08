package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializer.ObjectStreamSerializer;

class ObjectFileStorageTest extends AbstractStorageTest {

    protected ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}