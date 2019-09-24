package com.tingbob.fastform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.tingbob.fastform.IFormElementType;
import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.FormItemEditTextListener;
import com.tingbob.fastform.listener.OnFormElementValueChangedListener;
import com.tingbob.fastform.listener.ReloadListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementTextNumber;
import com.tingbob.fastform.model.FormElementTextNumberStatistic;
import com.tingbob.fastform.viewholder.BaseViewHolder;
import com.tingbob.fastform.viewholder.FormElementHeader;
import com.tingbob.fastform.viewholder.FormElementPickerDateViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerMultiViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerSingleViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerTimeViewHolder;
import com.tingbob.fastform.viewholder.FormElementSwitchViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextEmailViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextMultiLineViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextNumberStatisticViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextNumberViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextPasswordViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextPhoneViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextSingleLineViewHolder;

/**
 * The adapter the holds and displays the form objects
 * Created by Adib on 16-Apr-17.
 */

public class FormAdapter extends RecyclerView.Adapter<BaseViewHolder> implements ReloadListener {

    private Context mContext;
    private List<FormElementObject> mDataset;
    private OnFormElementValueChangedListener mListener;

    /**
     * public constructor with context
     * @param context
     */
    public FormAdapter(Context context, OnFormElementValueChangedListener listener) {
        mContext = context;
        mListener = listener;
        mDataset = new ArrayList<>();
    }

    /**
     * adds list of elements to be shown
     * @param formObjects
     */
    public void addElements(List<FormElementObject> formObjects) {
        this.mDataset = formObjects;
        updateRelatedStatisticTags();
        notifyDataSetChanged();
    }

    /**
     * adds single element to be shown
     * @param formObject
     */
    public void addElement(FormElementObject formObject) {
        this.mDataset.add(formObject);
        notifyDataSetChanged();
    }

    /**
     * set value for any unique index
     * @param position
     * @param value
     */
    public void setValueAtIndex(int position, String value) {
        FormElementObject formElementObject = mDataset.get(position);
        formElementObject.setValue(value);
        notifyDataSetChanged();
    }

    /**
     * set value for any unique tag
     * @param tag
     * @param value
     */
    public void setValueAtTag(int tag, String value) {
        for (FormElementObject f : this.mDataset) {
            if (f.getTag().equals(tag)) {
                f.setValue(value);
                return;
            }
        }
        notifyDataSetChanged();
    }

    /**
     * get element by position
     * @param position
     * @return
     */

    public FormElementObject getElement(int position) {
        if (mDataset.isEmpty()) {
            return null;
        }
        return mDataset.get(position);
    }

    public FormElementObject getElementByTag(String tag) {
        for (FormElementObject f : this.mDataset) {
            if (f.getTag().equals(tag)) {
                return f;
            }
        }

        return null;
    }

    public int getPositionByTag(String tag) {
        int pos;
        for (pos = 0; pos < getItemCount(); pos++) {
            FormElementObject f = getElement(pos);
            if (f.getTag().equals(tag)) {
                break;
            }
        }
        return pos;
    }

    /**
     * get value of any element by tag
     * @param index
     * @return
     */
    public String getValueAtIndex(int index) {
        FormElementObject elementObject = getElement(index);
        if (elementObject != null) {
            return elementObject.getValue();
        }
        return null;
    }

    /**
     * get value of any element by tag
     * @param tag
     * @return
     */
    public String getValueAtTag(String tag) {
        FormElementObject elementObject = getElementByTag(tag);
        if (elementObject != null) {
            return elementObject.getValue();
        }
        return null;
    }

    /**
     * set related statistic text number
     *
     */
    public void updateRelatedStatisticTags() {
        for (FormElementObject f : this.mDataset) {
            if (f instanceof FormElementTextNumberStatistic) {
                FormElementTextNumberStatistic statistic = (FormElementTextNumberStatistic)f;
                if (statistic.getStatisticTags() != null) {
                    for (String tag : statistic.getStatisticTags()) {
                        FormElementTextNumber textNumber = (FormElementTextNumber)getElementByTag(tag);
                        textNumber.setRelatedStatisticTag(statistic.getTag());
                    }
                }
            }
        }
    }

    public void updateValueStatistic(String tag) {
        int pos = getPositionByTag(tag);
        notifyItemChanged(pos);
    }

    /**
     * get whole dataset
     * @return
     */
    public List<FormElementObject> getDataset() {
        return mDataset;
    }

    /**
     * get value changed listener
     * @return
     */
    public OnFormElementValueChangedListener getValueChangeListener() {
        return mListener;
    }

    /**
     * gets total item count
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * gets view item type based on header, or the form element type
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position).getType();
    }

    /**
     * creating the view holder to be shown for a position
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // get layout based on header or element type
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;
        switch (viewType) {
            case IFormElementType.TYPE_HEADER:
                v = inflater.inflate(R.layout.form_element_header, parent, false);
                return new FormElementHeader(v);
            case IFormElementType.TYPE_EDITTEXT_TEXT_SINGLELINE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextSingleLineViewHolder(v, new FormItemEditTextListener(this));
            case IFormElementType.TYPE_EDITTEXT_TEXT_MULTILINE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextMultiLineViewHolder(v, new FormItemEditTextListener(this));
            case IFormElementType.TYPE_EDITTEXT_NUMBER:
                v = inflater.inflate(R.layout.form_element_number, parent, false);
                return new FormElementTextNumberViewHolder(v, new FormItemEditTextListener(this));
            case IFormElementType.TYPE_EDITTEXT_EMAIL:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextEmailViewHolder(v, new FormItemEditTextListener(this));
            case IFormElementType.TYPE_EDITTEXT_PHONE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextPhoneViewHolder(v, new FormItemEditTextListener(this));
            case IFormElementType.TYPE_EDITTEXT_PASSWORD:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextPasswordViewHolder(v, new FormItemEditTextListener(this));
            case IFormElementType.TYPE_PICKER_DATE:
                v = inflater.inflate(R.layout.form_element_date_picker, parent, false);
                return new FormElementPickerDateViewHolder(v, mContext, this);
            case IFormElementType.TYPE_PICKER_TIME:
                v = inflater.inflate(R.layout.form_element_time_picker, parent, false);
                return new FormElementPickerTimeViewHolder(v, mContext, this);
            case IFormElementType.TYPE_PICKER_SINGLE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementPickerSingleViewHolder(v, mContext, this);
            case IFormElementType.TYPE_PICKER_MULTI:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementPickerMultiViewHolder(v, mContext, this);
            case IFormElementType.TYPE_SWITCH:
                v = inflater.inflate(R.layout.form_element_switch, parent, false);
                return new FormElementSwitchViewHolder(v, mContext, this);
            case IFormElementType.TYPE_NUMBER_STATISTIC:
                v = inflater.inflate(R.layout.form_element_number_statistic, parent, false);
                return new FormElementTextNumberStatisticViewHolder(v,new FormItemEditTextListener(this));
            default:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextSingleLineViewHolder(v, new FormItemEditTextListener(this));
        }
    }

    /**
     * draws the view for the position specific view holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {

        // updates edit text listener index
        if (holder.getListener() != null) {
            holder.getListener().updatePosition(holder.getAdapterPosition());
        }

        // gets current object
        FormElementObject currentObject = mDataset.get(position);
        if (currentObject instanceof FormElementTextNumberStatistic) {
            FormElementTextNumberStatistic formStatistic = (FormElementTextNumberStatistic)currentObject;
            int statistic = 0;
            if (formStatistic.getStatisticTags() != null) {
                for (String tag : formStatistic.getStatisticTags()) {
                    String value = getValueAtTag(tag);
                    statistic += TextUtils.isEmpty(value) ? 0 : Integer.valueOf(value);
                }
            }
            formStatistic.setValue(String.valueOf(statistic));
        }
        holder.bind(position, currentObject, mContext);
    }

    /**
     * use the listener to update value and notify dataset changes to adapter
     * @param position
     * @param updatedValue
     */
    @Override
    public void updateValue(int position, String updatedValue) {
        mDataset.get(position).setValue(updatedValue);
        notifyDataSetChanged();
        if (mListener != null)
            mListener.onValueChanged(mDataset.get(position));
    }

}