package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;

import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.OnHeaderDelClickListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormHeader;

/**
 * ViewHolder for Header
 * Created by tingbob  on 30-Jul-17.
 */

public class FormElementHeader extends BaseViewHolder {

    public AppCompatTextView mTextViewTitle;
    public ImageView iv_del;
    private OnHeaderDelClickListener onHeaderDelClickListener;

    public FormElementHeader(View v, OnHeaderDelClickListener onHeaderDelClickListener) {
        super(v);
        this.onHeaderDelClickListener = onHeaderDelClickListener;
        mTextViewTitle = v.findViewById(R.id.formElementTitle);
        iv_del = v.findViewById(R.id.iv_del);
    }

    @Override
    public void bind(int position, FormElementObject formElement, final Context context) {
        final FormHeader formHeader = (FormHeader)formElement;
        mTextViewTitle.setText(formHeader.getTitle());
        iv_del.setVisibility((formHeader.getRelatedTags() == null || formHeader.getRelatedTags().isEmpty()) ? View.GONE : View.VISIBLE);
        if (iv_del.getVisibility() == View.VISIBLE) {
            iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onHeaderDelClickListener != null) {
                        onHeaderDelClickListener.onHeaderDelClick(formHeader.getTag());
                    }
                }
            });
        } else {
            iv_del.setOnClickListener(null);
        }

    }

}
