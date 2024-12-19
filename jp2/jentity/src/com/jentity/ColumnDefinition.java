package com.jentity;

import java.lang.reflect.ParameterizedType;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ColumnDefinition<T> {
    private String name;
    private Supplier<T> getFunc;
    private Consumer<T> setFunc;
    
    public ColumnDefinition(String name, Supplier<T> getFunc, Consumer<T> setFunc) {
        this.name = name;
        this.getFunc = getFunc;
        this.setFunc = setFunc;
    }

    public String getName() {
        return name;
    }
    public Class<T> getValueType() {
        return (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public T get() {
        return getFunc.get();
    }
    public void set(T value) {
        setFunc.accept(value);
    }
    
}