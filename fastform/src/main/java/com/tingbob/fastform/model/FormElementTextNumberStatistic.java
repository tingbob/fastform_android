package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.util.List;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementTextNumberStatistic implements FormElementObject<FormElementTextNumberStatistic> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify related group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required
    private List<String> mStatisticTags;
    private String mRelatedStatisticTag;
    private int mInputType;

    public static FormElementTextNumberStatistic createInstance() {
        FormElementTextNumberStatistic FormElementTextNumberStatistic = new FormElementTextNumberStatistic();
        FormElementTextNumberStatistic.setType(IFormElementType.TYPE_NUMBER_STATISTIC);
        return FormElementTextNumberStatistic;
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
    public FormElementTextNumberStatistic setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public FormElementTextNumberStatistic setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public FormElementTextNumberStatistic setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementTextNumberStatistic setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public FormElementTextNumberStatistic setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public FormElementTextNumberStatistic setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public FormElementTextNumberStatistic setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }

    public List<String> getStatisticTags() {
        return mStatisticTags;
    }

    public FormElementTextNumberStatistic setStatisticTags(List<String> list) {
        this.mStatisticTags = list;
        return this;
    }

    public String getRelatedStatisticTag() {
        return mRelatedStatisticTag;
    }

    public FormElementTextNumberStatistic setRelatedStatisticTag(String tag) {
        this.mRelatedStatisticTag = tag;
        return this;
    }

    public int getInputType() {
        return mInputType;
    }

    public FormElementTextNumberStatistic setInputType(int inputType) {
        this.mInputType = inputType;
        return this;
    }
}
