package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {
    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume resume, OutputStream searchKey) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(searchKey)) {
            output.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(InputStream searchKey) throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(searchKey)) {
            return (Resume) input.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
