import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume getUuid = null;
        for (int i = 0; i < size(); i++) {
            if (i == storage.length - 1 && (!storage[i].toString().equals(uuid))) {
                return null;
            }
            if (storage[i].toString().equals(uuid)) {
                getUuid = storage[i];
                break;
            }
        }
        return getUuid;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                for (int j = i; j < storage.length - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
