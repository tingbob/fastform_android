package com.tingbob.fastform;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

import com.tingbob.fastform.adapter.FormAdapter;
import com.tingbob.fastform.listener.OnAttachAddClickListener;
import com.tingbob.fastform.listener.OnButtonClickListener;
import com.tingbob.fastform.listener.OnFormElementValueChangedListener;
import com.tingbob.fastform.listener.OnImageAddClickListener;
import com.tingbob.fastform.listener.OnRemoveClickListener;
import com.tingbob.fastform.listener.OnVideoAddClickListener;
import com.tingbob.fastform.model.FormElementObject;

/** Wrapper class around the adapter to assist in building form
 * Created by Adib on 16-Apr-17.
 */

public class FormBuilder {

    private FormAdapter mFormAdapter;

    /**
     * constructor without listener callback for changed values
     * @param context
     * @param recyclerView
     */
    public FormBuilder(Context context, RecyclerView recyclerView) {
        initializeFormBuildHelper(context, recyclerView);
    }

    /**
     * private method for initializing form build helper
     * @param context
     * @param recyclerView
     */
    private void initializeFormBuildHelper(Context context, RecyclerView recyclerView) {

        // initialize form adapter
        this.mFormAdapter = new FormAdapter(context);

        // set up the recyclerview with adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(false);

        recyclerView.setLayoutManager(linearLayoutManager);

        //add divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bg_divider_gray);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(mFormAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public void setOnFormElementValueChangeListener(OnFormElementValueChangedListener onFormElementValueChangeListener) {
        mFormAdapter.setOnFormElementValueChangeListener(onFormElementValueChangeListener);
    }

    public void setOnImageClickListener(OnImageAddClickListener onImageAddClickListener) {
        mFormAdapter.setOnImageAddClickListener(onImageAddClickListener);
    }

    public void setOnVideoClickListener(OnVideoAddClickListener onVideoAddClickListener) {
        mFormAdapter.setOnVideoAddClickListener(onVideoAddClickListener);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        mFormAdapter.setOnButtonClickListener(onButtonClickListener);
    }

    public void setOnAttachUploadClickListener(OnAttachAddClickListener onAttachAddClickListener) {
        mFormAdapter.setOnAttachAddClickListener(onAttachAddClickListener);
    }

    public void setOnRemoveClickListener(OnRemoveClickListener onRemoveClickListener) {
        mFormAdapter.setOnRemoveClickListener(onRemoveClickListener);
    }

    /**
     * add list of form elements to be shown
     * @param formElementObjects
     */
    public void addFormElements(List<FormElementObject> formElementObjects) {
        this.mFormAdapter.addElements(formElementObjects);
    }

    /**
     * add list of form elements of the position to be shown
     * @param formElementObjects
     */
    public void addFormElements(int index, List<FormElementObject> formElementObjects) {
        this.mFormAdapter.addElements(index, formElementObjects);
    }

    /**
     * get value of specific form element
     * @param tag
     * @return
     */
    public FormElementObject getFormElement(String tag) {
        return this.mFormAdapter.getElementByTag(tag);
    }

    public FormElementObject getFormElementPos(int pos) {
        return this.mFormAdapter.getElement(pos);
    }

    /**
     *
     * return true if the form is valid
     *
     * @return
     */
    public boolean isValidForm(Context context) {
        for (int i = 0; i < this.mFormAdapter.getItemCount(); i++) {
            FormElementObject formElementObject = this.mFormAdapter.getElement(i);
            if (formElementObject.isRequired() && TextUtils.isEmpty(formElementObject.getValue())) {
                Toast.makeText(context,
                        String.format(context.getString(R.string.should_input), formElementObject.getTitle()),
                        Toast.LENGTH_LONG)
                        .show();
                return false;
            }
        }
        return true;
    }

    public void updateImagePaths(String tag, List<String> imagePaths) {
        mFormAdapter.updateImagePaths(tag, imagePaths);
    }

    public void updateVideoPaths(String tag, List<String> videoPaths) {
        mFormAdapter.updateVideoPaths(tag, videoPaths);
    }

    public void updateAttachList(String tag, List<String> attachList) {
        mFormAdapter.updateAttachList(tag, attachList);
    }
}