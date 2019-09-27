package com.tingbob.fastform.model;

public interface FormElementObject<T> {
    String getTag();

    String getGroupTag();

    int getType();

    String getTitle();

    String getValue();

    String getHint();

    boolean isRequired();

    T setTag(String tag);

    T setGroupTag(String groupTag);

    T setType(int type);

    T setTitle(String title);

    T setValue(String value);

    T setHint(String hint);

    T setRequired(boolean required);
}
