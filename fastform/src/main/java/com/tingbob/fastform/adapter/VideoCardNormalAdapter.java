package com.tingbob.fastform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.OnVideoClickListener;
import com.tingbob.fastform.model.FormElementVideoMultiple;

import java.util.ArrayList;
import java.util.List;

public class VideoCardNormalAdapter extends RecyclerView.Adapter<VideoCardNormalAdapter.VideoCardViewHolder> {

    private Context mContext;
    private List<String> thumbsList;
    public FormElementVideoMultiple formElement;
    public OnVideoClickListener onVideoClickListener;

    public VideoCardNormalAdapter(Context mContext, FormElementVideoMultiple formElement, OnVideoClickListener onVideoClickListener) {
        this.mContext = mContext;
        this.formElement = formElement;
        this.onVideoClickListener = onVideoClickListener;
        thumbsList = new ArrayList<>();
        if (formElement.getListValue() != null && !formElement.getListValue().isEmpty()) {
            thumbsList.addAll(formElement.getListValue());
        }
    }

    public String getItem(int position) {
        return thumbsList.get(position);
    }

    @Override
    public VideoCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video_card, parent, false);

        return new VideoCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoCardViewHolder holder, int position) {
        holder.updateView(position);
    }

    @Override
    public int getItemCount() {
        return thumbsList.size();
    }

    public class VideoCardViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_thumb;
        public ImageView iv_del;
        public ImageView iv_video_play;

        public VideoCardViewHolder(View view) {
            super(view);
            iv_thumb = view.findViewById(R.id.iv_thumb);
            iv_del = view.findViewById(R.id.iv_del);
            iv_video_play = view.findViewById(R.id.iv_video_play);
        }

        public void updateView(final int position) {
            String videoPath = getItem(position);
            Glide.with(mContext)
                    .load(videoPath)
                    .dontAnimate()
                    .into(iv_thumb);

            iv_thumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onVideoClickListener != null) {
                        onVideoClickListener.onVideoItemClick(videoPath);
                    }
                }
            });

            iv_del.setVisibility(View.GONE);
            iv_video_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onVideoClickListener != null) {
                        onVideoClickListener.onVideoItemClick(videoPath);
                    }
                }
            });
        }
    }
}