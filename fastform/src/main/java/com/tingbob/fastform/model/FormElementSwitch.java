package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementSwitch implements FormElementObject<FormElementSwitch> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify related group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    private String positiveText; // text for right side
    private String negativeText; // text for left side

    public static FormElementSwitch createInstance() {
        FormElementSwitch FormElementSwitch = new FormElementSwitch();
        FormElementSwitch.setType(IFormElementType.TYPE_SWITCH);
        return FormElementSwitch;
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
    public FormElementSwitch setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public FormElementSwitch setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public FormElementSwitch setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementSwitch setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public FormElementSwitch setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public FormElementSwitch setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public FormElementSwitch setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }

    // custom setters
    public FormElementSwitch setSwitchTexts(String positiveText, String negativeText) {
        this.positiveText = positiveText;
        this.negativeText = negativeText;
        return this;
    }

    // custom getters
    public String getPositiveText() {
        return this.positiveText;
    }

    public String getNegativeText() {
        return this.negativeText;
    }

}
