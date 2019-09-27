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
import com.tingbob.fastform.listener.OnAttachUploadClickListener;
import com.tingbob.fastform.listener.OnButtonAddClickListener;
import com.tingbob.fastform.listener.OnButtonClickListener;
import com.tingbob.fastform.listener.OnFormElementValueChangedListener;
import com.tingbob.fastform.listener.OnHeaderDelClickListener;
import com.tingbob.fastform.listener.OnImageAddClickListener;
import com.tingbob.fastform.listener.ReloadListener;
import com.tingbob.fastform.model.FormElementButton;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerAttach;
import com.tingbob.fastform.model.FormElementPickerDate;
import com.tingbob.fastform.model.FormElementPickerImageMultiple;
import com.tingbob.fastform.model.FormElementPickerMulti;
import com.tingbob.fastform.model.FormElementPickerSingle;
import com.tingbob.fastform.model.FormElementPickerTime;
import com.tingbob.fastform.model.FormElementSwitch;
import com.tingbob.fastform.model.FormElementTextEmail;
import com.tingbob.fastform.model.FormElementTextMultiLine;
import com.tingbob.fastform.model.FormElementTextNumber;
import com.tingbob.fastform.model.FormElementTextNumberStatistic;
import com.tingbob.fastform.model.FormElementTextPassword;
import com.tingbob.fastform.model.FormElementTextPhone;
import com.tingbob.fastform.model.FormElementTextSingleLine;
import com.tingbob.fastform.model.FormHeader;
import com.tingbob.fastform.utils.Utils;
import com.tingbob.fastform.viewholder.BaseViewHolder;
import com.tingbob.fastform.viewholder.FormElementButtonViewHolder;
import com.tingbob.fastform.viewholder.FormElementHeader;
import com.tingbob.fastform.viewholder.FormElementPickerAttachViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerDateViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerImageMultiViewHolder;
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
    private OnImageAddClickListener onImageAddClickListener;
    private OnButtonClickListener onButtonClickListener;
    private OnAttachUploadClickListener onAttachUploadClickListener;

    /**
     * public constructor with context
     * @param context
     */
    public FormAdapter(Context context,
                       OnFormElementValueChangedListener listener) {
        this.mContext = context;
        this.mListener = listener;
        this.mDataset = new ArrayList<>();
    }

    public void setOnFormElementValueChangeListener(OnFormElementValueChangedListener onFormElementValueChangeListener) {
        this.mListener = onFormElementValueChangeListener;
    }

    public void setOnImageAddClickListener(OnImageAddClickListener onImageAddClickListener) {
        this.onImageAddClickListener = onImageAddClickListener;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public void setOnAttachUploadClickListener(OnAttachUploadClickListener onAttachUploadClickListener) {
        this.onAttachUploadClickListener = onAttachUploadClickListener;
    }

    private OnHeaderDelClickListener onHeaderDelClickListener = new OnHeaderDelClickListener() {
        @Override
        public void onHeaderDelClick(String tag) {
            FormHeader formHeader = (FormHeader)getElementByTag(tag);
            List<String> tags = formHeader.getRelatedTags();
            if (tags == null || tags.isEmpty()) {
                return;
            }
            for (String relatedTag : tags) {
                mDataset.remove(getElementByTag(relatedTag));
            }
            updateRelatedStatisticTags();
            notifyDataSetChanged();
        }
    };

    private OnButtonAddClickListener onButtonAddClickListener = new OnButtonAddClickListener() {
        @Override
        public void onButtonAddClick(String tag) {
            FormElementButton formElementButton = (FormElementButton)getElementByTag(tag);
            int index = getPositionByTag(tag);
            if (formElementButton.getActionAddElements() == null || formElementButton.getActionAddElements().isEmpty()) {
                return;
            }
            FormHeader formHeader = null;
            List<String> relatedTags = new ArrayList<>();
            FormElementTextNumber formElementTextNumber = null;
            for (int i = 0; i < formElementButton.getActionAddElements().size(); i++) {
                FormElementObject formElementObject = formElementButton.getActionAddElements().get(i);
                FormElementObject formElement;
                switch (formElementObject.getType()) {
                    case IFormElementType.TYPE_HEADER: {
                        formHeader = FormHeader.createInstance();
                        formElement = formHeader;
                        break;
                    }
                    case IFormElementType.TYPE_EDITTEXT_TEXT_SINGLELINE: {
                        formElement = FormElementTextSingleLine.createInstance();
                        break;
                    }
                    case IFormElementType.TYPE_EDITTEXT_TEXT_MULTILINE: {
                        formElement = FormElementTextMultiLine.createInstance();
                        break;
                    }
                    case IFormElementType.TYPE_EDITTEXT_NUMBER: {
                        formElementTextNumber = FormElementTextNumber.createInstance()
                                .setRelatedStatisticTag(((FormElementTextNumber)formElementObject).getRelatedStatisticTag());
                        formElement = formElementTextNumber;
                        break;
                    }
                    case IFormElementType.TYPE_EDITTEXT_EMAIL: {
                        formElement = FormElementTextEmail.createInstance();
                        break;
                    }
                    case IFormElementType.TYPE_EDITTEXT_PHONE: {
                        formElement = FormElementTextPhone.createInstance();
                        break;
                    }
                    case IFormElementType.TYPE_EDITTEXT_PASSWORD: {
                        formElement = FormElementTextPassword.createInstance();
                        break;
                    }
                    case IFormElementType.TYPE_PICKER_DATE: {
                        formElement = FormElementPickerDate.createInstance()
                                .setDateFormat(((FormElementPickerDate)formElementObject).getDateFormat());
                        break;
                    }
                    case IFormElementType.TYPE_PICKER_TIME: {
                        formElement = FormElementPickerTime.createInstance()
                                .setTimeFormat(((FormElementPickerTime)formElementObject).getTimeFormat());
                        break;
                    }
                    case IFormElementType.TYPE_PICKER_SINGLE: {
                        formElement = FormElementPickerSingle.createInstance()
                                .setOptions(((FormElementPickerSingle)formElementObject).getOptions());
                        break;
                    }
                    case IFormElementType.TYPE_PICKER_MULTI: {
                        formElement = FormElementPickerMulti.createInstance()
                                .setNegativeText(((FormElementPickerMulti)formElementObject).getNegativeText())
                                .setPositiveText(((FormElementPickerMulti)formElementObject).getPositiveText())
                                .setPickerTitle(((FormElementPickerMulti)formElementObject).getPickerTitle())
                                .setOptions(((FormElementPickerMulti)formElementObject).getOptions());
                        break;
                    }
                    case IFormElementType.TYPE_SWITCH: {
                        formElement = FormElementSwitch.createInstance()
                                .setSwitchTexts(((FormElementSwitch)formElementObject).getPositiveText(), ((FormElementSwitch)formElementObject).getNegativeText());
                        break;
                    }
                    case IFormElementType.TYPE_PICKER_IMAGE_MULTIPLE: {
                        formElement = FormElementPickerImageMultiple.createInstance();
                        break;
                    }
                    default:
                        formElement = FormElementTextSingleLine.createInstance();
                        break;
                }
                formElement.setTag(Utils.generateKey());
                relatedTags.add(formElement.getTag());
                formElement.setGroupTag(formElementButton.getGroupTag());
                formElement.setType(formElementObject.getType());
                formElement.setTitle(formElementObject.getTitle());
                formElement.setValue(formElementObject.getValue());
                formElement.setHint(formElementObject.getHint());
                formElement.setRequired(formElementObject.isRequired());
                addElement(index + i, formElement);
            }
            if (formHeader != null && !relatedTags.isEmpty()) {
                formHeader.setRelatedTags(relatedTags);
            }

            if (formElementTextNumber != null && !TextUtils.isEmpty(formElementTextNumber.getRelatedStatisticTag())) {
                FormElementTextNumberStatistic formElementTextNumberStatistic = (FormElementTextNumberStatistic)getElementByTag(formElementTextNumber.getRelatedStatisticTag());
                formElementTextNumberStatistic.getStatisticTags().add(formElementTextNumber.getTag());
            }
            notifyDataSetChanged();
        }
    };

    /**
     * adds list of elements to be shown
     * @param formObjects
     */
    public void addElements(List<FormElementObject> formObjects) {
        this.mDataset.addAll(formObjects);
        updateRelatedStatisticTags();
        notifyDataSetChanged();
    }

    /**
     * adds list of elements of the position to be shown
     *
     * @param index
     * @param formObjects
     */

    public void addElements(int index, List<FormElementObject> formObjects) {
        this.mDataset.addAll(index, formObjects);
        updateRelatedStatisticTags();
        notifyDataSetChanged();
    }

    /**
     * adds single element to be shown
     * @param formObject
     */
    public void addElement(FormElementObject formObject) {
        this.mDataset.add(formObject);
        updateRelatedStatisticTags();
        notifyDataSetChanged();
    }

    /**
     * adds single element of the position to be shown
     *
     * @param index
     * @param formObject
     */
    public void addElement(int index, FormElementObject formObject) {
        this.mDataset.add(index, formObject);
        updateRelatedStatisticTags();
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
            case IFormElementType.TYPE_HEADER: {
                return new FormElementHeader(inflater.inflate(R.layout.form_element_header, parent, false), onHeaderDelClickListener);
            }
            case IFormElementType.TYPE_EDITTEXT_TEXT_SINGLELINE: {
                return new FormElementTextSingleLineViewHolder(inflater.inflate(R.layout.form_element, parent, false),
                        new FormItemEditTextListener(this));
            }
            case IFormElementType.TYPE_EDITTEXT_TEXT_MULTILINE: {
                return new FormElementTextMultiLineViewHolder(inflater.inflate(R.layout.form_element, parent, false),
                        new FormItemEditTextListener(this));
            }
            case IFormElementType.TYPE_EDITTEXT_NUMBER: {
                return new FormElementTextNumberViewHolder(inflater.inflate(R.layout.form_element_number, parent, false),
                        new FormItemEditTextListener(this));
            }
            case IFormElementType.TYPE_EDITTEXT_EMAIL: {
                return new FormElementTextEmailViewHolder(inflater.inflate(R.layout.form_element, parent, false),
                        new FormItemEditTextListener(this));
            }
            case IFormElementType.TYPE_EDITTEXT_PHONE: {
                return new FormElementTextPhoneViewHolder(inflater.inflate(R.layout.form_element, parent, false),
                        new FormItemEditTextListener(this));
            }
            case IFormElementType.TYPE_EDITTEXT_PASSWORD: {
                return new FormElementTextPasswordViewHolder(inflater.inflate(R.layout.form_element, parent, false),
                        new FormItemEditTextListener(this));
            }
            case IFormElementType.TYPE_PICKER_DATE: {
                return new FormElementPickerDateViewHolder(inflater.inflate(R.layout.form_element_date_picker, parent, false),
                        mContext, this);
            }
            case IFormElementType.TYPE_PICKER_TIME: {
                return new FormElementPickerTimeViewHolder(inflater.inflate(R.layout.form_element_time_picker, parent, false),
                        mContext, this);
            }
            case IFormElementType.TYPE_PICKER_SINGLE: {
                return new FormElementPickerSingleViewHolder(inflater.inflate(R.layout.form_element, parent, false),
                        mContext, this);
            }
            case IFormElementType.TYPE_PICKER_MULTI: {
                return new FormElementPickerMultiViewHolder(inflater.inflate(R.layout.form_element, parent, false),
                        mContext, this);
            }
            case IFormElementType.TYPE_SWITCH: {
                return new FormElementSwitchViewHolder(inflater.inflate(R.layout.form_element_switch, parent, false),
                        mContext, this);
            }
            case IFormElementType.TYPE_NUMBER_STATISTIC: {
                return new FormElementTextNumberStatisticViewHolder(inflater.inflate(R.layout.form_element_number_statistic, parent, false),
                        new FormItemEditTextListener(this));
            }
            case IFormElementType.TYPE_PICKER_IMAGE_MULTIPLE: {
                return new FormElementPickerImageMultiViewHolder(inflater.inflate(R.layout.form_element_imageview_multiple_picker, parent, false),
                        onImageAddClickListener);
            }
            case IFormElementType.TYPE_BUTTON: {
                return new FormElementButtonViewHolder(inflater.inflate(R.layout.form_element_button, parent, false),
                        onButtonClickListener, onButtonAddClickListener);
            }
            case IFormElementType.TYPE_PICKER_ATTACH: {
                return new FormElementPickerAttachViewHolder(inflater.inflate(R.layout.form_element_attach, parent, false),
                        onAttachUploadClickListener);
            }
            default:
                return new FormElementTextSingleLineViewHolder(inflater.inflate(R.layout.form_element, parent, false),
                        new FormItemEditTextListener(this));
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
        FormElementObject currentObject = getElement(position);

        // set statistic value
        setStatisticValue(currentObject);

        holder.bind(position, currentObject, mContext);
    }

    public void setStatisticValue(FormElementObject currentObject) {
        if (!(currentObject instanceof FormElementTextNumberStatistic)) {
            return;
        }
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

    public void updateImagePaths(String tag, List<String> imagePaths) {
        if (imagePaths == null || imagePaths.isEmpty()) {
            return;
        }
        FormElementPickerImageMultiple formElement = (FormElementPickerImageMultiple)getElementByTag(tag);
        formElement.setListValue(imagePaths);
        notifyItemChanged(getPositionByTag(tag));
    }

    public void updateAttachList(String tag, List<String> attachList) {
        if (attachList == null || attachList.isEmpty()) {
            return;
        }
        FormElementPickerAttach formElement = (FormElementPickerAttach)getElementByTag(tag);
        formElement.setListValue(attachList);
        notifyItemChanged(getPositionByTag(tag));
    }

}