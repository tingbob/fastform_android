package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementPickerTime implements FormElementObject<FormElementPickerTime> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify related group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    private String timeFormat; // custom format for date

    public static FormElementPickerTime createInstance() {
        FormElementPickerTime formElementPickerTime = new FormElementPickerTime();
        formElementPickerTime.setType(IFormElementType.TYPE_PICKER_TIME);
        formElementPickerTime.setTimeFormat("KK:mm a");
        return formElementPickerTime;
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
    public FormElementPickerTime setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public FormElementPickerTime setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public FormElementPickerTime setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementPickerTime setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public FormElementPickerTime setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public FormElementPickerTime setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public FormElementPickerTime setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }

    // custom setter
    public FormElementPickerTime setTimeFormat(String format) {
        checkValidTimeFormat(format);
        this.timeFormat = format;
        return this;
    }

    // custom getter
    public String getTimeFormat() {
        return this.timeFormat;
    }

    private void checkValidTimeFormat(String format) {
        try {
            new SimpleDateFormat(format, Locale.US);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Time format is not correct: " + e.getMessage());
        }
    }

}
