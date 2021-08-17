package com.tingbob.fastform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tingbob.fastform.R;
import com.tingbob.fastform.model.FormElementAttach;

import java.util.ArrayList;
import java.util.List;

public class AttachListNormalAdapter extends RecyclerView.Adapter<AttachListNormalAdapter.AttachListViewHolder> {

    private Context mContext;
    private List<String> attachList;
    public FormElementAttach formElement;

    public AttachListNormalAdapter(Context mContext, FormElementAttach formElement) {
        this.mContext = mContext;
        this.formElement = formElement;
        attachList = new ArrayList<>();
        if (formElement.getListValue() != null && !formElement.getListValue().isEmpty()) {
            attachList.addAll(formElement.getListValue());
        }
    }

    public String getItem(int position) {
        return attachList.get(position);
    }

    @Override
    public AttachListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attach_list, parent, false);

        return new AttachListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AttachListViewHolder holder, int position) {
        holder.updateView(position);
    }

    @Override
    public int getItemCount() {
        return attachList.size();
    }

    public class AttachListViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tv_name;
        public ImageView iv_del;

        public AttachListViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            iv_del = view.findViewById(R.id.iv_del);
        }

        public void updateView(final int position) {
            String name = getItem(position);
            tv_name.setText(name);
            iv_del.setVisibility(View.GONE);
        }
    }
}