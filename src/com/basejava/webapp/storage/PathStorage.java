package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.serializer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path path;
    private final StreamSerializer streamSerializer;

    protected PathStorage(Path searchKey, StreamSerializer streamSerializer) {
        this.streamSerializer = streamSerializer;
        path = Paths.get(searchKey.toAbsolutePath().toUri());
        Objects.requireNonNull(path, "path must not be null");
        if (!Files.isDirectory(path) || !Files.isWritable(path)) {
            throw new IllegalArgumentException(searchKey + " is not path or is not writable");
        }
    }

    @Override
    protected Resume doGet(Path searchKey) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Path read error", getFileName(searchKey), e);
        }
    }


    @Override
    protected void doSave(Path searchKey, Resume resume) {
        try {
            Files.createFile(searchKey);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + searchKey, getFileName(searchKey), e);
        }
        doUpdate(searchKey, resume);
    }

    @Override
    protected void doDelete(Path searchKey) {
        try {
            Files.delete(searchKey);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(searchKey), e);
        }
    }

    @Override
    protected void doUpdate(Path searchKey, Resume resume) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }


    @Override
    protected boolean isExist(Path searchKey) {
        return Files.isRegularFile(searchKey);
    }

    @Override
    protected Path getSearchKey(String key) {
        return path.resolve(key);
    }

    @Override
    protected List<Resume> doGetCopyList() {
        return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private String getFileName(Path searchKey) {
        return searchKey.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(path);
        } catch (IOException e) {
            throw new StorageException("Path read error", e);
        }
    }
}
