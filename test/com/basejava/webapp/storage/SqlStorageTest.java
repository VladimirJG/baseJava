package com.basejava.webapp.storage;

import com.basejava.webapp.Config;

import static org.junit.jupiter.api.Assertions.*;

class SqlStorageTest extends AbstractStorageTest {

    protected SqlStorageTest() {
        super(Config.get().getSqlStorage());
    }
}