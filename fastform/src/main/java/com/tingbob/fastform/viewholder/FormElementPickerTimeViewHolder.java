package com.tingbob.fastform.viewholder;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.ReloadListener;
import com.tingbob.fastform.model.BaseFormElement;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerTime;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementPickerTimeViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewTitle;
    private AppCompatEditText mEditTextValue;
    private TimePickerDialog mTimePickerDialog;
    private Calendar mCalendarCurrentTime;
    private ReloadListener mReloadListener;
    private FormElementObject mFormElement;
    private int mPosition;

    public FormElementPickerTimeViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mEditTextValue = v.findViewById(R.id.formElementValue);
        mReloadListener = reloadListener;
        mCalendarCurrentTime = Calendar.getInstance();
        mTimePickerDialog = new TimePickerDialog(context,
                time,
                mCalendarCurrentTime.get(Calendar.HOUR),
                mCalendarCurrentTime.get(Calendar.MINUTE),
                false);
    }

    @Override
    public void bind(int position, FormElementObject formElement, final Context context) {
        mFormElement = formElement;
        mPosition = position;

        mTextViewTitle.setText(formElement.getTitle());
        mEditTextValue.setText(formElement.getValue());
        mEditTextValue.setHint(formElement.getHint());
        mEditTextValue.setFocusableInTouchMode(false);

        mEditTextValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialog.show();
            }
        });

        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialog.show();
            }
        });
    }

    /**
     * setting up time picker dialog listener
     */
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendarCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendarCurrentTime.set(Calendar.MINUTE, minute);

            String myFormatTime = ((FormElementPickerTime) mFormElement).getTimeFormat(); // custom format
            SimpleDateFormat sdfTime = new SimpleDateFormat(myFormatTime, Locale.US);

            String currentValue = mFormElement.getValue();
            String newValue = sdfTime.format(mCalendarCurrentTime.getTime());

            // trigger event only if the value is changed
            if (!currentValue.equals(newValue)) {
                mReloadListener.updateValue(mPosition, newValue);
            }
        }
    };

}
