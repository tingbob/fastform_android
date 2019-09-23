package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementTextSingleLine implements FormElementObject<FormElementTextSingleLine> {

    // private variables
    private String mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    public FormElementTextSingleLine() {
    }

    public static FormElementTextSingleLine createInstance() {
        FormElementTextSingleLine formElementTextSingleLine = new FormElementTextSingleLine();
        formElementTextSingleLine.setType(IFormElementType.TYPE_EDITTEXT_TEXT_SINGLELINE);
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

    public FormElementTextSingleLine setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementTextSingleLine setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementTextSingleLine setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementTextSingleLine setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementTextSingleLine setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementTextSingleLine setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

}
