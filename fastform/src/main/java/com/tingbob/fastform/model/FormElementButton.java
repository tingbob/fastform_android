package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementButton implements FormElementObject<FormElementButton> {

    // private variables
    private String mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    public FormElementButton() {
    }

    public static FormElementButton createInstance() {
        FormElementButton form = new FormElementButton();
        form.setType(IFormElementType.TYPE_BUTTON);
        return form;
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

    public FormElementButton setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementButton setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementButton setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementButton setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementButton setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementButton setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

}
