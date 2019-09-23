package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementPickerSingle implements FormElementObject<FormElementPickerSingle> {

    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    private String pickerTitle; // custom title for picker
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker

    public FormElementPickerSingle() {
    }

    public static FormElementPickerSingle createInstance() {
        FormElementPickerSingle formElementPickerSingle = new FormElementPickerSingle();
        formElementPickerSingle.setType(IFormElementType.TYPE_PICKER_SINGLE);
        formElementPickerSingle.setPickerTitle("Pick one");
        return formElementPickerSingle;
    }

    @Override
    public int getTag() {
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
        return mValue;
    }

    @Override
    public String getHint() {
        return mHint;
    }

    @Override
    public boolean isRequired() {
        return mRequired;
    }

    public FormElementPickerSingle setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementPickerSingle setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementPickerSingle setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementPickerSingle setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementPickerSingle setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementPickerSingle setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    // custom setters
    public FormElementPickerSingle setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementPickerSingle setOptionsSelected(List<String> mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementPickerSingle setPickerTitle(String title) {
        this.pickerTitle = title;
        return this;
    }

    // custom getters
    public List<String> getOptions() {
        return (this.mOptions == null) ? new ArrayList<String>() : this.mOptions;
    }

    public List<String> getOptionsSelected() {
        return (this.mOptionsSelected == null) ? new ArrayList<String>() : this.mOptionsSelected;
    }

    public String getPickerTitle() {
        return this.pickerTitle;
    }

}
