package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementTextPhone implements FormElementObject<FormElementTextPhone> {

    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    public FormElementTextPhone() {
    }

    public static FormElementTextPhone createInstance() {
        FormElementTextPhone FormElementTextPhone = new FormElementTextPhone();
        FormElementTextPhone.setType(IFormElementType.TYPE_EDITTEXT_PHONE);
        return FormElementTextPhone;
    }

    @Override
    public int getTag() {
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
        return mValue;
    }

    @Override
    public String getHint() {
        return mHint;
    }

    @Override
    public boolean isRequired() {
        return mRequired;
    }

    public FormElementTextPhone setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementTextPhone setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementTextPhone setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementTextPhone setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementTextPhone setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementTextPhone setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }
    
}
