package com.tingbob.fastform.model;

/**
 * Created by Adib on 16-Apr-17.
 */

public class BaseFormElement implements FormElementObject<BaseFormElement> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify related group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    // setters
    @Override
    public BaseFormElement setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public BaseFormElement setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public BaseFormElement setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public BaseFormElement setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public BaseFormElement setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public BaseFormElement setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    @Override
    public String getTag() {
        return this.mTag;
    }

    @Override
    public int getType() {
        return this.mType;
    }

    @Override
    public String getTitle() {
        return this.mTitle;
    }

    @Override
    public String getValue() {
        return (this.mValue == null) ? "" : this.mValue;
    }

    @Override
    public String getHint() {
        return (this.mHint == null) ? "" : this.mHint;
    }

    @Override
    public boolean isRequired() {
        return this.mRequired;
    }

    @Override
    public String toString() {
        return "BaseFormElement{" +
                "mTag=" + mTag +
                ", mType=" + mType +
                ", mTitle='" + mTitle + '\'' +
                ", mValue='" + mValue + '\'' +
                ", mHint='" + mHint + '\'' +
                ", mRequired=" + mRequired +
                '}';
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public BaseFormElement setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }
}
