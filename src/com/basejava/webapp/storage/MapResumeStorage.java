package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class MapResumeStorage extends AbstractMapStorage<Resume> {

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected void doSave(Resume searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    protected void doUpdate(Resume searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
