package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    /*private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {

        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };*/
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void insertElement(Resume resume, int index) {
        int newElementIndex = index * -1 - 1;
        System.arraycopy(storage, newElementIndex, storage, newElementIndex + 1, size - newElementIndex);
        storage[newElementIndex] = resume;
    }

    @Override
    protected void refillVoid(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }


    @Override
    protected Integer getSearchKey(String uuid) {
        Resume key = new Resume(uuid, "dummy field");
        return Arrays.binarySearch(storage, 0, size, key, RESUME_COMPARATOR);
    }
}