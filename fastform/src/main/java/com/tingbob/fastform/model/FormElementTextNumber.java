package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementTextNumber implements FormElementObject<FormElementTextNumber> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify related group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required
    private String mRelatedStatisticTag;

    public static FormElementTextNumber createInstance() {
        FormElementTextNumber FormElementTextNumber = new FormElementTextNumber();
        FormElementTextNumber.setType(IFormElementType.TYPE_EDITTEXT_NUMBER);
        return FormElementTextNumber;
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

    @Override
    public FormElementTextNumber setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public FormElementTextNumber setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public FormElementTextNumber setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementTextNumber setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public FormElementTextNumber setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public FormElementTextNumber setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public FormElementTextNumber setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }

    public String getRelatedStatisticTag() {
        return mRelatedStatisticTag;
    }

    public FormElementTextNumber setRelatedStatisticTag(String tag) {
        this.mRelatedStatisticTag = tag;
        return this;
    }
    
}
