package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializer {


    @Override
    public void doWrite(Resume resume, OutputStream searchKey) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(searchKey)) {
            output.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(InputStream searchKey) throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(searchKey)) {
            return (Resume) input.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
