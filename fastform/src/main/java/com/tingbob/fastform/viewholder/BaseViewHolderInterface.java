package com.tingbob.fastform.viewholder;

import android.content.Context;

import com.tingbob.fastform.listener.FormItemEditTextListener;
import com.tingbob.fastform.model.BaseFormElement;

/**
 * Base ViewHolder method instance
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public interface BaseViewHolderInterface {
    FormItemEditTextListener getListener();
    void bind(int position, BaseFormElement formElement, Context context);
}
