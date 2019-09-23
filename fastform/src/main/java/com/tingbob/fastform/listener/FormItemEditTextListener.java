package com.tingbob.fastform.listener;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.tingbob.fastform.adapter.FormAdapter;
import com.tingbob.fastform.model.BaseFormElement;
import com.tingbob.fastform.model.FormElementObject;

/**
 * Edit text listener for form element edit texts
 * Created by tingbob  on 30-Jul-17.
 */

public class FormItemEditTextListener implements TextWatcher {

    private int position;
    private FormAdapter formAdapter;

    public FormItemEditTextListener(FormAdapter formAdapter) {
        this.formAdapter = formAdapter;
    }

    public void updatePosition(int position) {
        this.position = position;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        // get current form element, existing value and new value
        FormElementObject formElementObject = formAdapter.getDataset().get(position);
        String currentValue = formElementObject.getValue();
        String newValue = charSequence.toString();

        // trigger event only if the value is changed
        if (!TextUtils.isEmpty(currentValue) && !currentValue.equals(newValue)) {
            formElementObject.setValue(newValue);
            if (formAdapter.getValueChangeListener() != null)
                formAdapter.getValueChangeListener().onValueChanged(formElementObject);
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
