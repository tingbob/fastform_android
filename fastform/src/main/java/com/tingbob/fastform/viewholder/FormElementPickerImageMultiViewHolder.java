package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tingbob.fastform.R;
import com.tingbob.fastform.adapter.ImageCardAdapter;
import com.tingbob.fastform.listener.OnImageClickListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerImageMultiple;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementPickerImageMultiViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    public AppCompatTextView mTextViewTitle;
    public RecyclerView mRecyclerValue;
    public ImageCardAdapter imageCardAdapter;
    public OnImageClickListener onImageClickListener;

    public FormElementPickerImageMultiViewHolder(View v, OnImageClickListener onImageClickListener) {
        super(v);
        this.onImageClickListener = onImageClickListener;
        mTextViewRequired = v.findViewById(R.id.formElementRequired);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mRecyclerValue = v.findViewById(R.id.formElementValue);
    }

    @Override
    public void bind(int position, FormElementObject formElement, Context context) {
        mTextViewRequired.setVisibility(formElement.isRequired() ? View.VISIBLE : View.GONE);
        mTextViewTitle.setText(formElement.getTitle());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        mRecyclerValue.setLayoutManager(layoutManager);
        mRecyclerValue.setItemAnimator(new DefaultItemAnimator());
        imageCardAdapter = new ImageCardAdapter(itemView.getContext(), (FormElementPickerImageMultiple) formElement, onImageClickListener);
        mRecyclerValue.setAdapter(imageCardAdapter);
    }
}
