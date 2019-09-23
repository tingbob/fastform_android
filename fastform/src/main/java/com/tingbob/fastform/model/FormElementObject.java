package com.tingbob.fastform.model;

public interface FormElementObject<T> {
    String getTag();

    int getType();

    String getTitle();

    String getValue();

    String getHint();

    boolean isRequired();

    T setValue(String value);
}
