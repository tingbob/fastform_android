package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.tingbob.fastform.R;
import com.tingbob.fastform.model.BaseFormElement;
import com.tingbob.fastform.model.FormElementObject;

/**
 * ViewHolder for Header
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementHeader extends BaseViewHolder {

    public AppCompatTextView mTextViewTitle;

    public FormElementHeader(View v) {
        super(v);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
    }

    @Override
    public void bind(int position, FormElementObject formElement, final Context context) {
        mTextViewTitle.setText(formElement.getTitle());
    }

}
