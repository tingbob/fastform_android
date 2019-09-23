package com.tingbob.fastform.listener;

import com.tingbob.fastform.model.FormElementObject;

/**
 * Callback to activity when any data in form adapter is changed
 */

public interface OnFormElementValueChangedListener {

    void onValueChanged(FormElementObject baseFormElement);

}