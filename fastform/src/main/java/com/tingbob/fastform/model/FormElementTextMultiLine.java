package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementTextMultiLine implements FormElementObject<FormElementTextMultiLine> {

    // private variables
    private String mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    public FormElementTextMultiLine() {
    }

    public static FormElementTextMultiLine createInstance() {
        FormElementTextMultiLine FormElementTextMultiLine = new FormElementTextMultiLine();
        FormElementTextMultiLine.setType(IFormElementType.TYPE_EDITTEXT_TEXT_MULTILINE);
        return FormElementTextMultiLine;
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

    public FormElementTextMultiLine setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementTextMultiLine setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementTextMultiLine setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementTextMultiLine setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementTextMultiLine setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementTextMultiLine setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

}
