package repository;

import global.util.annotation.Id;

import java.lang.reflect.Field;
import java.util.*;

public class MemoryRepositoryImpl<T, ID> implements MemoryRepository<T, ID> {
    private final Map<ID, T> dataStore;

    public MemoryRepositoryImpl() {
        dataStore = new HashMap<>();
    }

    @Override
    public T save(T entity) {
        ID id = getId(entity);
        if(id != null) {
            dataStore.put(id, entity);
        }

        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(dataStore.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public void deleteById(ID id) {
        dataStore.remove(id);
    }

    private ID getId(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) { // @Id 어노테이션 사용
                field.setAccessible(true);
                try {
                    return (ID) field.get(entity);
                } catch (IllegalAccessException e) {
                    System.out.println("Exception: ID 필드에 접근할 수 없습니다.");
                    return null;
                }
            }
        }
        System.out.println("Exception: ID 필드가 존재하지 않습니다.");
        return null;
    }
}
