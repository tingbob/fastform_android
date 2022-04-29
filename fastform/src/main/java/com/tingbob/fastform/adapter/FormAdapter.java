package com.tingbob.fastform.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.tingbob.fastform.IFormElementType;
import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.FormItemEditTextListener;
import com.tingbob.fastform.listener.OnAttachAddClickListener;
import com.tingbob.fastform.listener.OnButtonAddClickListener;
import com.tingbob.fastform.listener.OnButtonClickListener;
import com.tingbob.fastform.listener.OnFormElementValueChangedListener;
import com.tingbob.fastform.listener.OnHeaderDelClickListener;
import com.tingbob.fastform.listener.OnImageClickListener;
import com.tingbob.fastform.listener.OnQrcodeScanButtonClickListener;
import com.tingbob.fastform.listener.OnRemoveClickListener;
import com.tingbob.fastform.listener.OnVideoClickListener;
import com.tingbob.fastform.listener.ReloadListener;
import com.tingbob.fastform.model.FormElementButton;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerAttach;
import com.tingbob.fastform.model.FormElementPickerDate;
import com.tingbob.fastform.model.FormElementPickerImageMultiple;
import com.tingbob.fastform.model.FormElementPickerMulti;
import com.tingbob.fastform.model.FormElementPickerSingle;
import com.tingbob.fastform.model.FormElementPickerTime;
import com.tingbob.fastform.model.FormElementPickerVideoMultiple;
import com.tingbob.fastform.model.FormElementSwitch;
import com.tingbob.fastform.model.FormElementTextEmail;
import com.tingbob.fastform.model.FormElementTextMultiLine;
import com.tingbob.fastform.model.FormElementTextNumber;
import com.tingbob.fastform.model.FormElementTextNumberStatistic;
import com.tingbob.fastform.model.FormElementTextPassword;
import com.tingbob.fastform.model.FormElementTextPhone;
import com.tingbob.fastform.model.FormElementTextQrcodeScan;
import com.tingbob.fastform.model.FormElementTextSingleLine;
import com.tingbob.fastform.model.FormHeader;
import com.tingbob.fastform.utils.Arith;
import com.tingbob.fastform.utils.Utils;
import com.tingbob.fastform.viewholder.BaseViewHolder;
import com.tingbob.fastform.viewholder.FormElementAttachNormalViewHolder;
import com.tingbob.fastform.viewholder.FormElementButtonViewHolder;
import com.tingbob.fastform.viewholder.FormElementHeader;
import com.tingbob.fastform.viewholder.FormElementImageMultiViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerAttachViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerDateViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerImageMultiViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerMultiViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerSingleViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerTimeViewHolder;
import com.tingbob.fastform.viewholder.FormElementPickerVideoMultiViewHolder;
import com.tingbob.fastform.viewholder.FormElementSwitchViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextEmailViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextMultiLineViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextNormalViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextNumberStatisticViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextNumberViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextPasswordViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextPhoneViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextQrcodeScanViewHolder;
import com.tingbob.fastform.viewholder.FormElementTextSingleLineViewHolder;
import com.tingbob.fastform.viewholder.FormElementVideoMultiViewHolder;

/**
 * The adapter the holds and displays the form objects
 * Created by Adib on 16-Apr-17.
 */

public class FormAdapter extends RecyclerView.Adapter<BaseViewHolder> implements ReloadListener {

    private Context mContext;
    private List<FormElementObject> mDataset;
    private OnFormElementValueChangedListener mListener;
    private OnImageClickListener onImageClickListener;
    private OnVideoClickListener onVideoClickListener;
    private OnButtonClickListener onButtonClickListener;
    private OnAttachAddClickListener onAttachAddClickListener;
    private OnRemoveClickListener onRemoveClickListener;
    private OnQrcodeScanButtonClickListener onQrcodeScanButtonClickListener;

    /**
     * public constructor with context
     * @param context
     */
    public FormAdapter(Context context) {
        this.mContext = context;
        this.mDataset = new ArrayList<>();
    }

    public void setOnFormElementValueChangeListener(OnFormElementValueChangedListener onFormElementValueChangeListener) {
        this.mListener = onFormElementValueChangeListener;
    }

    public void setOnImageAddClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public void setOnVideoAddClickListener(OnVideoClickListener onVideoClickListener) {
        this.onVideoClickListener = onVideoClickListener;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public void setOnAttachAddClickListener(OnAttachAddClickListener onAttachAddClickListener) {
        this.onAttachAddClickListener = onAttachAddClickListener;
    }

    public void setOnRemoveClickListener(OnRemoveClickListener onRemoveClickListener) {
        this.onRemoveClickListener = onRemoveClickListener;
    }

    public void setOnQrcodeScanButtonClickListener(OnQrcodeScanButtonClickListener onQrcodeScanButtonClickListener) {
        this.onQrcodeScanButtonClickListener = onQrcodeScanButtonClickListener;
    }

    private OnHeaderDelClickListener onHeaderDelClickListener = new OnHeaderDelClickListener() {
        @Override
        public void onHeaderDelClick(String tag) {
            FormHeader formHeader = (FormHeader)getElementByTag(tag);
            List<String> tags = formHeader.getRelatedTags();
            if (tags == null || tags.isEmpty()) {
                return;
            }

            if (onRemoveClickListener != null) {
                onRemoveClickListener.onRemoveClick(tags);
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
                    case IFormElementType.TYPE_PICKER_VIDEO_MULTIPLE: {
                        formElement = FormElementPickerVideoMultiple.createInstance();
                        break;
                    }
                    case IFormElementType.TYPE_PICKER_ATTACH: {
                        formElement = FormElementPickerAttach.createInstance();
                        break;
                    }
                    case IFormElementType.TYPE_TEXT_QRCODE_SCAN: {
                        formElement = FormElementTextQrcodeScan.createInstance();
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
            formElementButton.addCount();
            if (formHeader != null && !relatedTags.isEmpty()) {
                formHeader.setTitle(formHeader.getTitle() + formElementButton.getAddedCount());
                formHeader.setRelatedTags(relatedTags);
            }

            if (formElementTextNumber != null && !TextUtils.isEmpty(formElementTextNumber.getRelatedStatisticTag())) {
                FormElementTextNumberStatistic formElementTextNumberStatistic = (FormElementTextNumberStatistic)getElementByTag(formElementTextNumber.getRelatedStatisticTag());
                formElementTextNumberStatistic.getStatisticTags().add(formElementTextNumber.getTag());
            }
            notifyDataSetChanged();
            if (onButtonClickListener != null) {
                onButtonClickListener.onButtonClick(tag, relatedTags);
            }
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
     * fill list of elements to be show
     * @param formObjects
     */
    public void fillElements(List<FormElementObject> formObjects) {
        this.mDataset.clear();
        addElements(formObjects);
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
     * set value for any unique tag
     * @param tag
     * @param value
     */
    public void updateValueAtTag(String tag, String value) {
        FormElementObject f = getElementByTag(tag);
        f.setValue(value);
        notifyItemChanged(getPositionByTag(tag));
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
                        FormElementObject textNumber = getElementByTag(tag);
                        if (textNumber != null) {
                            if (textNumber instanceof FormElementTextNumber) {
                                ((FormElementTextNumber)textNumber).setRelatedStatisticTag(statistic.getTag());
                            } else if (textNumber instanceof FormElementTextNumberStatistic) {
                                ((FormElementTextNumberStatistic)textNumber).setRelatedStatisticTag(statistic.getTag());
                            }
                        } else {
                            statistic.getStatisticTags().remove(tag);
                        }
                    }
                }
            }
        }
    }

    public void updateValueStatistic(String tag) {
        int pos = getPositionByTag(tag);
        notifyItemChanged(pos);
        FormElementObject object = getElementByTag(tag);
        if (object instanceof FormElementTextNumberStatistic) {
            FormElementTextNumberStatistic statistic = (FormElementTextNumberStatistic)object;
            if (!TextUtils.isEmpty(statistic.getRelatedStatisticTag())) {
                int posStatistic = getPositionByTag(statistic.getRelatedStatisticTag());
                notifyItemChanged(posStatistic);
            }
        }
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
                return new FormElementPickerSingleViewHolder(inflater.inflate(R.layout.form_element_picker_options, parent, false),
                        mContext, this);
            }
            case IFormElementType.TYPE_PICKER_MULTI: {
                return new FormElementPickerMultiViewHolder(inflater.inflate(R.layout.form_element_picker_options, parent, false),
                        mContext, this);
            }
            case IFormElementType.TYPE_SWITCH: {
                return new FormElementSwitchViewHolder(inflater.inflate(R.layout.form_element_switch, parent, false),
                        mContext, this);
            }
            case IFormElementType.TYPE_NUMBER_STATISTIC: {
                return new FormElementTextNumberStatisticViewHolder(inflater.inflate(R.layout.form_element_number_statistic, parent, false));
            }
            case IFormElementType.TYPE_PICKER_IMAGE_MULTIPLE: {
                return new FormElementPickerImageMultiViewHolder(inflater.inflate(R.layout.form_element_imagevideo_multiple_picker, parent, false),
                        onImageClickListener);
            }
            case IFormElementType.TYPE_PICKER_VIDEO_MULTIPLE: {
                return new FormElementPickerVideoMultiViewHolder(inflater.inflate(R.layout.form_element_imagevideo_multiple_picker, parent, false),
                        onVideoClickListener);
            }
            case IFormElementType.TYPE_BUTTON: {
                return new FormElementButtonViewHolder(inflater.inflate(R.layout.form_element_button, parent, false),
                        onButtonAddClickListener);
            }
            case IFormElementType.TYPE_PICKER_ATTACH: {
                return new FormElementPickerAttachViewHolder(inflater.inflate(R.layout.form_element_attach_picker, parent, false),
                        onAttachAddClickListener);
            }
            case IFormElementType.TYPE_TEXT_NORMAL: {
                return new FormElementTextNormalViewHolder(inflater.inflate(R.layout.form_element_normal, parent, false));
            }
            case IFormElementType.TYPE_IMAGE_NORMAL: {
                return new FormElementImageMultiViewHolder(inflater.inflate(R.layout.form_element_imagevideo_multiple_picker, parent, false), onImageClickListener);
            }
            case IFormElementType.TYPE_VIDEO_NORMAL: {
                return new FormElementVideoMultiViewHolder(inflater.inflate(R.layout.form_element_imagevideo_multiple_picker, parent, false), onVideoClickListener);
            }
            case IFormElementType.TYPE_ATTACH_NORMAL: {
                return new FormElementAttachNormalViewHolder(inflater.inflate(R.layout.form_element_attach_normal, parent, false));
            }
            case IFormElementType.TYPE_TEXT_QRCODE_SCAN: {
                return new FormElementTextQrcodeScanViewHolder(inflater.inflate(R.layout.form_element_text_qrcode_scan, parent, false), onQrcodeScanButtonClickListener);
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
        double statistic = 0;
        int inputType = 0;
        if (formStatistic.getStatisticTags() != null) {
            for (String tag : formStatistic.getStatisticTags()) {
                FormElementObject formElementObject = getElementByTag(tag);
                String value = formElementObject.getValue();
                if (formElementObject instanceof FormElementTextNumber) {
                    FormElementTextNumber formElementTextNumber = (FormElementTextNumber)formElementObject;
                    inputType = formElementTextNumber.getInputType();
                } else if (formElementObject instanceof FormElementTextNumberStatistic) {
                    FormElementTextNumberStatistic formElementTextNumberStatistic = (FormElementTextNumberStatistic)formElementObject;
                    inputType = formElementTextNumberStatistic.getInputType();
                }
                if (value.contains(".") && value.indexOf(".") == value.length() - 1) {
                    value = value.substring(0, value.indexOf("."));
                }
                statistic = Arith.add(statistic, TextUtils.isEmpty(value) ? 0 : Double.valueOf(value));
            }
        }
        formStatistic.setInputType(inputType);
        String value = String.valueOf(statistic);
        if (inputType == IFormElementType.TYPE_EDITTEXT_NUMBER_DECIMAL) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            value = decimalFormat.format(statistic);
        } else if (inputType == IFormElementType.TYPE_EDITTEXT_NUMBER_INT) {
            value = value.substring(0, value.indexOf("."));
        }

        formStatistic.setValue(value);
    }

    /**
     * use the listener to update value and notify dataset changes to adapter
     * @param position
     * @param updatedValue
     */
    @Override
    public void updateValue(int position, String updatedValue) {
        mDataset.get(position).setValue(updatedValue);
        notifyItemChanged(position);
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

    public void updateVideoPaths(String tag, List<String> videoPaths) {
        if (videoPaths == null || videoPaths.isEmpty()) {
            return;
        }
        FormElementPickerVideoMultiple formElement = (FormElementPickerVideoMultiple)getElementByTag(tag);
        formElement.setListValue(videoPaths);
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