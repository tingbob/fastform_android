package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tingbob.fastform.R;
import com.tingbob.fastform.adapter.AttachListNormalAdapter;
import com.tingbob.fastform.model.FormElementAttach;
import com.tingbob.fastform.model.FormElementObject;

/**
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementAttachNormalViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewRequired;
    public AppCompatTextView mTextViewTitle;
    public RecyclerView mRecyclerValue;
    public AttachListNormalAdapter attachListAdapter;

    public FormElementAttachNormalViewHolder(View v) {
        super(v);
        mTextViewRequired = v.findViewById(R.id.formElementRequired);
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        mRecyclerValue = v.findViewById(R.id.formElementValue);
    }

    @Override
    public void bind(int position, final FormElementObject formElement, Context context) {
        mTextViewRequired.setVisibility(formElement.isRequired() ? View.VISIBLE : View.GONE);
        mTextViewTitle.setText(formElement.getTitle());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerValue.setLayoutManager(layoutManager);
        mRecyclerValue.setItemAnimator(new DefaultItemAnimator());
        attachListAdapter = new AttachListNormalAdapter(itemView.getContext(), (FormElementAttach) formElement);
        mRecyclerValue.setAdapter(attachListAdapter);
    }
}
