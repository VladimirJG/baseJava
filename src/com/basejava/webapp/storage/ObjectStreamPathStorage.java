package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.*;
import java.nio.file.Path;

public class ObjectStreamPathStorage extends AbstractPathStorage{

    public ObjectStreamPathStorage(Path searchKey) {
        super(searchKey);
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
