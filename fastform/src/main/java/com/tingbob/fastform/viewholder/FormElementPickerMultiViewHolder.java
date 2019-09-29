package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.ReloadListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerMulti;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementPickerMultiViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    private AppCompatTextView mTextViewTitle;
    private AppCompatTextView mTextViewValue;
    private ReloadListener mReloadListener;
    private FormElementObject mFormElement;
    private FormElementPickerMulti mFormElementPickerMulti;

    public FormElementPickerMultiViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mTextViewRequired = v.findViewById(R.id.formElementRequired);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mTextViewValue = v.findViewById(R.id.formElementValue);
        mReloadListener = reloadListener;
    }

    @Override
    public void bind(final int position, FormElementObject formElement, final Context context) {
        mFormElement = formElement;
        mFormElementPickerMulti = (FormElementPickerMulti) mFormElement;

        mTextViewRequired.setVisibility(formElement.isRequired() ? View.VISIBLE : View.GONE);
        mTextViewTitle.setText(formElement.getTitle());
        mTextViewValue.setText(formElement.getValue());
        mTextViewValue.setHint(formElement.getHint());

        // reformat the options in format needed
        final CharSequence[] options = new CharSequence[mFormElementPickerMulti.getOptions().size()];
        final boolean[] optionsSelected = new boolean[mFormElementPickerMulti.getOptions().size()];
        final ArrayList<Integer> mSelectedItems = new ArrayList<>();

        for (int i = 0; i < mFormElementPickerMulti.getOptions().size(); i++) {
            options[i] = mFormElementPickerMulti.getOptions().get(i);
            optionsSelected[i] = false;

            if (mFormElementPickerMulti.getOptionsSelected().contains(options[i])) {
                optionsSelected[i] = true;
                mSelectedItems.add(i);
            }
        }

        // prepare the dialog
        final AlertDialog dialog  = new AlertDialog.Builder(context)
                .setTitle(mFormElementPickerMulti.getPickerTitle())
                .setMultiChoiceItems(options, optionsSelected,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton(mFormElementPickerMulti.getPositiveText(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s = "";
                        List<String> selectedOptions = new ArrayList<>();
                        for (int i = 0; i < mSelectedItems.size(); i++) {
                            s += options[mSelectedItems.get(i)];
                            selectedOptions.add(String.valueOf(options[mSelectedItems.get(i)]));
                            if (i < mSelectedItems.size() - 1) {
                                s += ", ";
                            }
                        }
                        mTextViewValue.setText(s);
                        mFormElementPickerMulti.setOptionsSelected(selectedOptions);
                        mReloadListener.updateValue(position, s);
                    }
                })
                .setNegativeButton(mFormElementPickerMulti.getNegativeText(), null)
                .create();

        mTextViewValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

}
