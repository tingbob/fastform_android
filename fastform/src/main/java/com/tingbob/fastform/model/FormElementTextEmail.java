package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementTextEmail implements FormElementObject<FormElementTextEmail> {

    // private variables
    private String mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    public FormElementTextEmail() {
    }

    public static FormElementTextEmail createInstance() {
        FormElementTextEmail FormElementTextEmail = new FormElementTextEmail();
        FormElementTextEmail.setType(IFormElementType.TYPE_EDITTEXT_EMAIL);
        return FormElementTextEmail;
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

    public FormElementTextEmail setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementTextEmail setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementTextEmail setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementTextEmail setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementTextEmail setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementTextEmail setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

}
