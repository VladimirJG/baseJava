import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int count = 0;

    void clear() {
        Arrays.fill(storage, 0, size() - 1, null);
        count = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                count++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size() - 1; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[size() - 1];
                storage[size() - 1] = null;
                count--;
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
        return count;
    }
}
