package com.jentity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.AbstractList;
import java.util.HashSet;
import java.util.Set;

public class Entity {
    public String getTableName() {
        return this.getClass().getAnnotation(Table.class).name();
    }

    public Set<ColumnDefinition<?>> getColumnDefinitions() {
        Set<ColumnDefinition<?>> columnDefs = new HashSet<>();
        Class selfClass = this.getClass();
        Field[] fields = selfClass.getFields();
        for (Field field : fields) {
            Column col = field.getAnnotation(Column.class);
            if (col == null) continue;
            columnDefs.add();
        }
        return columnDefs;
    }
}
