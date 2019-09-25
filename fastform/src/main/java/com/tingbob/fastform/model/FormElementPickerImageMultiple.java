package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.util.List;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementPickerImageMultiple implements FormElementObject<FormElementPickerImageMultiple> {

    // private variables
    private String mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required
    private List<String> mListValue;

    public FormElementPickerImageMultiple() {
    }

    public static FormElementPickerImageMultiple createInstance() {
        FormElementPickerImageMultiple formElementTextSingleLine = new FormElementPickerImageMultiple();
        formElementTextSingleLine.setType(IFormElementType.TYPE_PICKER_IMAGE_MULTIPLE);
        return formElementTextSingleLine;
    }

    @Override
    public String getTag() {
        return mTag;
    }

    @Override
    public int getType() {
        return mType;
    }

    @Override
    public String getTitle() {
        return mTitle;
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
        return mRequired;
    }

    public FormElementPickerImageMultiple setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementPickerImageMultiple setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementPickerImageMultiple setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementPickerImageMultiple setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementPickerImageMultiple setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementPickerImageMultiple setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    public List<String> getListValue() {
        return mListValue;
    }

    public FormElementPickerImageMultiple setListValue(List<String> values) {
        mListValue = values;
        return this;
    }
}
