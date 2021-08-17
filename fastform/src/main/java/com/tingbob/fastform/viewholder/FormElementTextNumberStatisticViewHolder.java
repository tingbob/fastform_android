package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.text.InputType;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.FormItemEditTextListener;
import com.tingbob.fastform.model.FormElementObject;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementTextNumberStatisticViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    public AppCompatTextView mTextViewTitle;
    public AppCompatTextView mTextViewValue;

    public FormElementTextNumberStatisticViewHolder(View v) {
        super(v);
        mTextViewRequired = v.findViewById(R.id.formElementRequired);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mTextViewValue = v.findViewById(R.id.formElementValue);
    }

    @Override
    public void bind(int position, FormElementObject formElement, final Context context) {
        mTextViewRequired.setVisibility(formElement.isRequired() ? View.VISIBLE : View.GONE);
        mTextViewTitle.setText(formElement.getTitle());
        mTextViewValue.setText(formElement.getValue());
        mTextViewValue.setHint(formElement.getHint());
    }

}
