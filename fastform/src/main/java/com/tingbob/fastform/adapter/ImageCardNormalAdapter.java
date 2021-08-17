package com.tingbob.fastform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tingbob.fastform.R;
import com.tingbob.fastform.model.FormElementImageMultiple;

import java.util.ArrayList;
import java.util.List;

public class ImageCardNormalAdapter extends RecyclerView.Adapter<ImageCardNormalAdapter.ImageCardViewHolder> {

    private Context mContext;
    private List<String> imagesList;
    public FormElementImageMultiple formElement;

    public ImageCardNormalAdapter(Context mContext, FormElementImageMultiple formElement) {
        this.mContext = mContext;
        this.formElement = formElement;
        imagesList = new ArrayList<>();
        if (formElement.getListValue() != null && !formElement.getListValue().isEmpty()) {
            imagesList.addAll(formElement.getListValue());
        }
    }

    public String getItem(int position) {
        return imagesList.get(position);
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
            String imagePath = getItem(position);
            Glide.with(mContext)
                    .load(imagePath)
                    .dontAnimate()
                    .into(iv_thumb);

            iv_del.setVisibility(View.GONE);
        }
    }
}