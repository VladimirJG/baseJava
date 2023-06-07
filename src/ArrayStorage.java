import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int count;

    void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    void save(Resume r) {
        storage[size()] = r;
        count++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < count - 1; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < count - 1; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[count - 1];
                storage[count - 1] = null;
                count--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    int size() {
        return count;
    }
}
