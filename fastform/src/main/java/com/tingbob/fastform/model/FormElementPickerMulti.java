package com.tingbob.fastform.model;

import com.tingbob.fastform.IFormElementType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tingbob  on 28-Jul-17.
 */

public class FormElementPickerMulti implements FormElementObject<FormElementPickerMulti> {

    // private variables
    private String mTag; // unique tag to identify the object
    private String mGroupTag; // unique tag ot identify related group
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private String mHint; // value to be shown if mValue is null
    private boolean mRequired; // value to set is the field is required

    private String pickerTitle; // custom title for picker
    private List<String> options; // list of options for single and multi picker
    private List<String> optionsSelected; // list of selected options for single and multi picker
    private String positiveText = "Ok"; // text for positive operation, like "ok"
    private String negativeText = "Cancel"; // text for negative operation, like "cancel"

    public static FormElementPickerMulti createInstance() {
        FormElementPickerMulti FormElementPickerMulti = new FormElementPickerMulti();
        FormElementPickerMulti.setType(IFormElementType.TYPE_PICKER_MULTI);
        return FormElementPickerMulti;
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
    public FormElementPickerMulti setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public FormElementPickerMulti setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public FormElementPickerMulti setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    @Override
    public FormElementPickerMulti setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    @Override
    public FormElementPickerMulti setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    @Override
    public FormElementPickerMulti setRequired(boolean required) {
        mRequired = required;
        return this;
    }

    @Override
    public String getGroupTag() {
        return mGroupTag;
    }

    @Override
    public FormElementPickerMulti setGroupTag(String groupTag) {
        this.mGroupTag = groupTag;
        return this;
    }

    // custom setters
    public FormElementPickerMulti setOptions(List<String> mOptions) {
        this.options = mOptions;
        return this;
    }

    public FormElementPickerMulti setOptionsSelected(List<String> mOptionsSelected) {
        this.optionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementPickerMulti setPickerTitle(String title) {
        this.pickerTitle = title;
        return this;
    }

    // custom getters
    public List<String> getOptions() {
        return (this.options == null) ? new ArrayList<String>() : this.options;
    }

    public List<String> getOptionsSelected() {
        return (this.optionsSelected == null) ? new ArrayList<String>() : this.optionsSelected;
    }

    public FormElementPickerMulti setPositiveText(String positiveText) {
        this.positiveText = positiveText;
        return this;
    }

    public FormElementPickerMulti setNegativeText(String negativeText) {
        this.negativeText = negativeText;
        return this;
    }

    // custom getters
    public String getPickerTitle() {
        return this.pickerTitle;
    }

    public String getPositiveText() {
        return this.positiveText;
    }

    public String getNegativeText() {
        return this.negativeText;
    }

}
