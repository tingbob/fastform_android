package com.tingbob.fastform.viewholder;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.widget.AppCompatTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.ReloadListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerDate;

/**
 * ViewHolder for DatePicker
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementPickerDateViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    private AppCompatTextView mTextViewTitle;
    private AppCompatTextView mTextViewValue;
    private View mDatePickerButton;
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendarCurrentDate;
    private ReloadListener mReloadListener;
    private FormElementObject mFormElement;
    private int mPosition;

    public FormElementPickerDateViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mTextViewRequired = v.findViewById(R.id.formElementRequired);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mTextViewValue = v.findViewById(R.id.formElementValue);
        mDatePickerButton = v.findViewById(R.id.linear_date_picker);
        mReloadListener = reloadListener;
        mCalendarCurrentDate = Calendar.getInstance();
    }

    @Override
    public void bind(int position, FormElementObject formElement, final Context context) {
        mFormElement = formElement;
        mPosition = position;

        mDatePickerDialog = new DatePickerDialog(context,
                date,
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

        mTextViewRequired.setVisibility(formElement.isRequired() ? View.VISIBLE : View.GONE);
        mTextViewTitle.setText(formElement.getTitle());
        mTextViewValue.setText(formElement.getValue());
        mTextViewValue.setHint(formElement.getHint());

        mDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });
    }

    /**
     * setting up date picker dialog listener
     */
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendarCurrentDate.set(Calendar.YEAR, year);
            mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
            mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormatDate = ((FormElementPickerDate) mFormElement).getDateFormat();
            SimpleDateFormat sdfDate = new SimpleDateFormat(myFormatDate, Locale.US);

            String currentValue = mFormElement.getValue();
            String newValue = sdfDate.format(mCalendarCurrentDate.getTime());

            // trigger event only if the value is changed
            if (!currentValue.equals(newValue)) {
                mFormElement.setValue(newValue);
                mReloadListener.updateValue(mPosition, newValue);
            }
        }

    };

}
