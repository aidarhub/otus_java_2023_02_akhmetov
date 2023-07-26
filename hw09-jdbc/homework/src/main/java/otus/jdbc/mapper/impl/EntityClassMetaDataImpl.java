package otus.jdbc.mapper.impl;

import otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.crm.model.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> entityClass;
    private String className;
    private Field fieldWithId;
    private List<Field> allFields;
    private List<Field> fieldsWithoutId;
    private Constructor<T> constructor;

    public EntityClassMetaDataImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public String getName() {
        if (className == null) {
            className = entityClass.getSimpleName().toLowerCase();
        }
        return className;
    }

    @Override
    public Constructor<T> getConstructor() {
        if (constructor == null) {
            try {
                constructor = entityClass.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return constructor;
    }

    @Override
    public Field getIdField() {
        if (fieldWithId == null) {
            fieldWithId = getAllFields().stream()
                    .filter(f -> f.isAnnotationPresent(Id.class))
                    .findFirst()
                    .orElseThrow();
        }
        return fieldWithId;
    }

    @Override
    public List<Field> getAllFields() {
        if (allFields == null) {
            allFields = Arrays.stream(entityClass.getDeclaredFields()).toList();
        }
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        if (fieldsWithoutId == null) {
            fieldsWithoutId = new ArrayList<>(getAllFields());
            fieldsWithoutId.remove(getIdField());
        }
        return fieldsWithoutId;
    }
}
