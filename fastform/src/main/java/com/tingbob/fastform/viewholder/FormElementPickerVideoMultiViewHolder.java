package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tingbob.fastform.R;
import com.tingbob.fastform.adapter.VideoCardAdapter;
import com.tingbob.fastform.listener.OnVideoAddClickListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerVideoMultiple;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementPickerVideoMultiViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    public AppCompatTextView mTextViewTitle;
    public RecyclerView mRecyclerValue;
    public VideoCardAdapter videoCardAdapter;
    public OnVideoAddClickListener onVideoAddClickListener;

    public FormElementPickerVideoMultiViewHolder(View v, OnVideoAddClickListener onVideoAddClickListener) {
        super(v);
        this.onVideoAddClickListener = onVideoAddClickListener;
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
        videoCardAdapter = new VideoCardAdapter(itemView.getContext(), (FormElementPickerVideoMultiple) formElement, onVideoAddClickListener);
        mRecyclerValue.setAdapter(videoCardAdapter);
    }
}
