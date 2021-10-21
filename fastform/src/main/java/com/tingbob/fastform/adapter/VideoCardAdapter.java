package com.tingbob.fastform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tingbob.fastform.R;
import com.tingbob.fastform.listener.OnVideoAddClickListener;
import com.tingbob.fastform.model.FormElementPickerVideoMultiple;

import java.util.ArrayList;
import java.util.List;

public class VideoCardAdapter extends RecyclerView.Adapter<VideoCardAdapter.VideoCardViewHolder> {

    private Context mContext;
    private List<String> thumbsList;
    private final String videoAddBtnUri = "badd";
    public OnVideoAddClickListener onVideoAddClickListener;
    public FormElementPickerVideoMultiple formElement;

    public VideoCardAdapter(Context mContext, FormElementPickerVideoMultiple formElement, OnVideoAddClickListener onVideoAddClickListener) {
        this.mContext = mContext;
        this.formElement = formElement;
        this.onVideoAddClickListener = onVideoAddClickListener;
        thumbsList = new ArrayList<>();
        if (formElement.getListValue() != null && !formElement.getListValue().isEmpty()) {
            thumbsList.addAll(formElement.getListValue());
        }
        thumbsList.add(videoAddBtnUri);
    }

    public String getItem(int position) {
        return thumbsList.get(position);
    }

    public void removeItem(int position) {
        formElement.getListValue().remove(thumbsList.get(position));
        thumbsList.remove(position);
        notifyDataSetChanged();
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
            final String thumbPath = getItem(position);
            if (thumbPath.equals(videoAddBtnUri)) {
                Glide.with(mContext)
                        .load(R.drawable.ic_image_add)
                        .dontAnimate()
                        .into(iv_thumb);
            } else {
                Glide.with(mContext)
                        .load(thumbPath)
                        .dontAnimate()
                        .into(iv_thumb);
            }


            boolean bAddBtn = (position == getItemCount() - 1);
            if (bAddBtn) {
                iv_thumb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { onVideoAddClickListener.onVideoAddClick(formElement.getTag());
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

            iv_video_play.setVisibility(bAddBtn ? View.GONE : View.VISIBLE);
        }
    }
}