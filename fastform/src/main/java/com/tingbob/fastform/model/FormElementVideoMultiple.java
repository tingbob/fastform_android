package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.util.List;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementVideoMultiple implements FormElementObject<FormElementVideoMultiple> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify related group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required
    private List<String> mListValue;

    public static FormElementVideoMultiple createInstance() {
        FormElementVideoMultiple formElementVideoMultiple = new FormElementVideoMultiple();
        formElementVideoMultiple.setType(IFormElementType.TYPE_VIDEO_NORMAL);
        return formElementVideoMultiple;
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
    public FormElementVideoMultiple setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public FormElementVideoMultiple setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public FormElementVideoMultiple setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementVideoMultiple setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public FormElementVideoMultiple setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public FormElementVideoMultiple setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    public List<String> getListValue() {
        return mListValue;
    }

    public FormElementVideoMultiple setListValue(List<String> values) {
        mListValue = values;
        return this;
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public FormElementVideoMultiple setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }
}
