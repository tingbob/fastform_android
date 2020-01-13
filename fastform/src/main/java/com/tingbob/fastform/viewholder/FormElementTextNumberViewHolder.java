package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DialerKeyListener;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.tingbob.fastform.IFormElementType;
import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.FormItemEditTextListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementTextNumber;
import com.tingbob.fastform.utils.MoneyValueFilter;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementTextNumberViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    public AppCompatTextView mTextViewTitle;
    public AppCompatEditText mEditTextValue;
    public FormItemEditTextListener mFormCustomEditTextListener;
    private int inputType;

    public FormElementTextNumberViewHolder(View v, FormItemEditTextListener listener) {
        super(v);
        mTextViewRequired = v.findViewById(R.id.formElementRequired);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mEditTextValue = v.findViewById(R.id.formElementValue);
        mFormCustomEditTextListener = listener;
        mEditTextValue.addTextChangedListener(mFormCustomEditTextListener);
        mEditTextValue.setRawInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }

    @Override
    public FormItemEditTextListener getListener() {
        return mFormCustomEditTextListener;
    }

    @Override
    public void bind(int position, FormElementObject formElement, final Context context) {
        inputType = ((FormElementTextNumber)formElement).getInputType();
        if (inputType == IFormElementType.TYPE_EDITTEXT_NUMBER_INT) {
            mEditTextValue.setRawInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            mEditTextValue.setFilters(new InputFilter[]{new DialerKeyListener()});
        } else if (inputType == IFormElementType.TYPE_EDITTEXT_NUMBER_DECIMAL) {
            mEditTextValue.setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            mEditTextValue.setFilters(new InputFilter[]{new MoneyValueFilter()});
        }
        mTextViewRequired.setVisibility(formElement.isRequired() ? View.VISIBLE : View.GONE);
        mTextViewTitle.setText(formElement.getTitle());
        mEditTextValue.setText(formElement.getValue());
        mEditTextValue.setHint(formElement.getHint());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextValue.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEditTextValue, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }
}
