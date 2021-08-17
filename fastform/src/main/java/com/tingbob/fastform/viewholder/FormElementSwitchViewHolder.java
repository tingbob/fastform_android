package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;

import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.ReloadListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementSwitch;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementSwitchViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    public AppCompatTextView mTextViewTitle, mTextViewPositive, mTextViewNegative;
    public SwitchCompat mSwitch;
    private ReloadListener mReloadListener;
    private FormElementObject mFormElement;
    private FormElementSwitch mFormElementSwitch;
    private int mPosition;

    public FormElementSwitchViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mTextViewRequired = v.findViewById(R.id.formElementRequired);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mTextViewPositive = v.findViewById(R.id.formElementPositiveText);
        mTextViewNegative = v.findViewById(R.id.formElementNegativeText);
        mSwitch = v.findViewById(R.id.formElementSwitch);
        mReloadListener = reloadListener;
    }

    @Override
    public void bind(final int position, FormElementObject formElement, final Context context) {
        mFormElement = formElement;
        mPosition = position;
        mFormElementSwitch = (FormElementSwitch) mFormElement;

        mTextViewRequired.setVisibility(formElement.isRequired() ? View.VISIBLE : View.GONE);
        mTextViewTitle.setText(mFormElementSwitch.getTitle());
        mTextViewPositive.setText(mFormElementSwitch.getPositiveText());
        mTextViewNegative.setHint(mFormElementSwitch.getNegativeText());
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mFormElement.setValue(b ? mFormElementSwitch.getPositiveText() : mFormElementSwitch.getNegativeText());
                mReloadListener.updateValue(position, b ? mFormElementSwitch.getPositiveText() : mFormElementSwitch.getNegativeText());
            }
        });
    }

}
