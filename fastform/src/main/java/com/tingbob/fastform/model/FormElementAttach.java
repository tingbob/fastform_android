package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.util.List;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementAttach implements FormElementObject<FormElementAttach> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify related group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required
    private List<String> mListValue;

    public static FormElementAttach createInstance() {
        FormElementAttach formElementAttach = new FormElementAttach();
        formElementAttach.setType(IFormElementType.TYPE_ATTACH_NORMAL);
        return formElementAttach;
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
    public FormElementAttach setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public FormElementAttach setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public FormElementAttach setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementAttach setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public FormElementAttach setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public FormElementAttach setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    public List<String> getListValue() {
        return mListValue;
    }

    public FormElementAttach setListValue(List<String> values) {
        mListValue = values;
        return this;
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public FormElementAttach setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }
}
