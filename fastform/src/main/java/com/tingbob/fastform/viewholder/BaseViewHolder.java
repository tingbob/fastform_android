package com.tingbob.fastform.viewholder;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.tingbob.fastform.listener.FormItemEditTextListener;
import com.tingbob.fastform.model.FormElementObject;

/**
 * Base ViewHolder for all other viewholders
 * Created by tingbob  on 30-Jul-17.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements BaseViewHolderInterface {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public FormItemEditTextListener getListener() {
        return null;
    }

    @Override
    public void bind(int position, FormElementObject formElement, Context context) {

    }

}
