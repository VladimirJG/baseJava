package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        assert directory != null;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected Resume doGet(File searchKey) {
        try {
            return doRead(searchKey);
        } catch (IOException e) {
            throw new StorageException("File read error", searchKey.getName(), e);
        }
    }

    @Override
    protected void doSave(File searchKey, Resume resume) {
        try {
            doWrite(resume, searchKey);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + searchKey.getAbsolutePath(), searchKey.getName(), e);
        }
        doUpdate(searchKey, resume);
    }

    @Override
    protected void doDelete(File searchKey) {
        if (!searchKey.delete()) {
            throw new StorageException("File delete error", searchKey.getName());
        }
    }

    @Override
    protected void doUpdate(File searchKey, Resume resume) {
        try {
            doWrite(resume, searchKey);
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    @Override
    protected File getSearchKey(String key) {
        return new File(directory, key);
    }

    @Override
    protected List<Resume> doGetCopyList() {
        File[] files = getList(directory);
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = getList(directory);
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        return getList(directory).length;
    }

    protected abstract void doWrite(Resume resume, File searchKey) throws IOException;

    protected abstract Resume doRead(File searchKey) throws IOException;

    private File[] getList(File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("directory cannot be empty", directory.getName());
        }
        return files;
    }
}
