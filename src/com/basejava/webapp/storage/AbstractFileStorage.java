package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

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
        return null;
    }

    @Override
    protected void doSave(File searchKey, Resume resume) {

    }

    @Override
    protected void doDelete(File searchKey) {

    }

    @Override
    protected void doUpdate(File searchKey, Resume resume) {

    }

    @Override
    protected boolean isExist(File searchKey) {
        return false;
    }

    @Override
    protected File getSearchKey(String key) {
        return null;
    }

    @Override
    protected List<Resume> doGetCopyList() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
    protected abstract void doWrite(Resume resume, File searchKey) throws IOException;
}
