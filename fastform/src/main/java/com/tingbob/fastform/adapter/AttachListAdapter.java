package com.tingbob.fastform.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.tingbob.fastform.R;
import com.tingbob.fastform.model.FormElementPickerAttach;
import java.util.ArrayList;
import java.util.List;

public class AttachListAdapter extends RecyclerView.Adapter<AttachListAdapter.AttachListViewHolder> {

    private Context mContext;
    private List<String> attachList;
    public FormElementPickerAttach formElement;

    public AttachListAdapter(Context mContext, FormElementPickerAttach formElement) {
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

    public void removeItem(int position) {
        formElement.getListValue().remove(attachList.get(position));
        attachList.remove(position);
        notifyDataSetChanged();
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

            iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(position);
                }
            });
        }
    }
}