package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementPickerDate implements FormElementObject<FormElementPickerDate> {
    // private variables
    private String mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    private String dateFormat; // custom format for date

    public FormElementPickerDate() {
    }

    public static FormElementPickerDate createInstance() {
        FormElementPickerDate formElementPickerDate = new FormElementPickerDate();
        formElementPickerDate.setType(IFormElementType.TYPE_PICKER_DATE);
        formElementPickerDate.setDateFormat("dd/MM/yy");
        return formElementPickerDate;
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

    public FormElementPickerDate setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementPickerDate setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementPickerDate setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementPickerDate setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementPickerDate setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementPickerDate setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    // custom setter
    public FormElementPickerDate setDateFormat(String format) {
        checkValidDateFormat(format);
        this.dateFormat = format;
        return this;
    }

    // custom getter
    public String getDateFormat() {
        return this.dateFormat;
    }

    private void checkValidDateFormat(String format) {
        try {
            new SimpleDateFormat(format, Locale.US);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Date format is not correct: " + e.getMessage());
        }
    }
    
}
