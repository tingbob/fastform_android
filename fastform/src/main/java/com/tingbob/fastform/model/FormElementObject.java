package com.tingbob.fastform.model;

public interface FormElementObject<T> {
    int getTag();

    int getType();

    String getTitle();

    String getValue();

    String getHint();

    boolean isRequired();

    T setValue(String value);
}
