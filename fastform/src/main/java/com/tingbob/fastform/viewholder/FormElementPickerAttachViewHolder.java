package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tingbob.fastform.R;
import com.tingbob.fastform.adapter.AttachListAdapter;
import com.tingbob.fastform.adapter.ImageCardAdapter;
import com.tingbob.fastform.listener.OnAttachUploadClickListener;
import com.tingbob.fastform.listener.OnImageAddClickListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerAttach;
import com.tingbob.fastform.model.FormElementPickerImageMultiple;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementPickerAttachViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    public AppCompatTextView mTextViewTitle;
    private AppCompatTextView mTextUploadButton;
    public RecyclerView mRecyclerValue;
    public AttachListAdapter attachListAdapter;
    public OnAttachUploadClickListener onAttachUploadClickListener;

    public FormElementPickerAttachViewHolder(View v, OnAttachUploadClickListener onAttachUploadClickListener) {
        super(v);
        this.onAttachUploadClickListener = onAttachUploadClickListener;
        mTextViewRequired = v.findViewById(R.id.formElementRequired);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mTextUploadButton = v.findViewById(R.id.tv_upload_attach);
        mRecyclerValue = v.findViewById(R.id.formElementValue);
    }

    @Override
    public void bind(int position, final FormElementObject formElement, Context context) {
        mTextViewRequired.setVisibility(formElement.isRequired() ? View.VISIBLE : View.GONE);
        mTextViewTitle.setText(formElement.getTitle());
        mTextUploadButton.setText(formElement.getValue());
        mTextUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAttachUploadClickListener != null) {
                    onAttachUploadClickListener.onAttachUploadClick(formElement.getTag());
                }
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerValue.setLayoutManager(layoutManager);
        mRecyclerValue.setItemAnimator(new DefaultItemAnimator());
        attachListAdapter = new AttachListAdapter(itemView.getContext(), (FormElementPickerAttach) formElement);
        mRecyclerValue.setAdapter(attachListAdapter);
    }
}
