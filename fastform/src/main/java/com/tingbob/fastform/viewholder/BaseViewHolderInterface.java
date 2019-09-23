package com.tingbob.fastform.viewholder;

import android.content.Context;

import com.tingbob.fastform.listener.FormItemEditTextListener;
import com.tingbob.fastform.model.FormElementObject;

/**
 * Base ViewHolder method instance
 * Created by tingbob  on 30-Jul-17.
 */

public interface BaseViewHolderInterface {
    FormItemEditTextListener getListener();
    void bind(int position, FormElementObject formElement, Context context);
}
