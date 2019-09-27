package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.util.List;

/**
 * Object for header of the form lists
 * Created by Adib on 18-Apr-17.
 */

public class FormHeader implements FormElementObject<FormHeader> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify belong group, use it when add group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required
    private List<String> mRelatedTags; // the elements's tag in a header group, use it when del a header group

    /**
     * static method to create instance with title
     * @return
     */
    public static FormHeader createInstance() {
        FormHeader formHeader = new FormHeader();
        formHeader.setType(IFormElementType.TYPE_HEADER);
        return formHeader;
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
    public FormHeader setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public FormHeader setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public FormHeader setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormHeader setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public FormHeader setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public FormHeader setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public FormHeader setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }

    public List<String> getRelatedTags() {
        return mRelatedTags;
    }

    public FormHeader setRelatedTags(List<String> relatedTags) {
        this.mRelatedTags = relatedTags;
        return this;
    }
}
