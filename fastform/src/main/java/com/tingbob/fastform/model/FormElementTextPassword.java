package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementTextPassword implements FormElementObject<FormElementTextPassword> {

    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    public FormElementTextPassword() {
    }

    public static FormElementTextPassword createInstance() {
        FormElementTextPassword FormElementTextPassword = new FormElementTextPassword();
        FormElementTextPassword.setType(IFormElementType.TYPE_EDITTEXT_PASSWORD);
        return FormElementTextPassword;
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

    public FormElementTextPassword setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementTextPassword setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementTextPassword setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementTextPassword setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementTextPassword setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementTextPassword setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }
    
}
