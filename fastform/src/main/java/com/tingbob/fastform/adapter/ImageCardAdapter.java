package com.tingbob.fastform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tingbob.fastform.GlideApp;
import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.OnImageAddClickListener;
import com.tingbob.fastform.model.FormElementPickerImageMultiple;

import java.util.ArrayList;
import java.util.List;

public class ImageCardAdapter extends RecyclerView.Adapter<ImageCardAdapter.ImageCardViewHolder> {

    private Context mContext;
    private List<String> imagesList;
    private final String imageAddBtnUri = "badd";
    public OnImageAddClickListener onImageAddClickListener;
    public FormElementPickerImageMultiple formElement;

    public ImageCardAdapter(Context mContext, FormElementPickerImageMultiple formElement, OnImageAddClickListener onImageAddClickListener) {
        this.mContext = mContext;
        this.formElement = formElement;
        this.onImageAddClickListener = onImageAddClickListener;
        imagesList = new ArrayList<>();
        if (formElement.getListValue() != null && !formElement.getListValue().isEmpty()) {
            imagesList.addAll(formElement.getListValue());
        }
        imagesList.add(imageAddBtnUri);
    }

    public String getItem(int position) {
        return imagesList.get(position);
    }

    public void removeItem(int position) {
        imagesList.remove(position);
        notifyDataSetChanged();
        formElement.setListValue(imagesList.subList(0, getItemCount() - 1));
    }

    @Override
    public ImageCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_card, parent, false);

        return new ImageCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageCardViewHolder holder, int position) {
        holder.updateView(position);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ImageCardViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_thumb;
        public ImageView iv_del;

        public ImageCardViewHolder(View view) {
            super(view);
            iv_thumb = view.findViewById(R.id.iv_thumb);
            iv_del = view.findViewById(R.id.iv_del);
        }

        public void updateView(final int position) {
            final String imagePath = getItem(position);
            if (imagePath.equals(imageAddBtnUri)) {
                GlideApp.with(mContext)
                        .load(R.drawable.ic_image_add)
                        .dontAnimate()
                        .into(iv_thumb);
            } else {
                GlideApp.with(mContext)
                        .load(imagePath)
                        .dontAnimate()
                        .into(iv_thumb);
            }


            boolean bAddBtn = (position == getItemCount() - 1);
            if (bAddBtn) {
                iv_thumb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { onImageAddClickListener.onImageAddClick(formElement.getTag());
                    }
                });
            } else {
                iv_thumb.setOnClickListener(null);
            }


            iv_del.setVisibility(bAddBtn ? View.GONE : View.VISIBLE);
            iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(position);
                }
            });
        }
    }
}