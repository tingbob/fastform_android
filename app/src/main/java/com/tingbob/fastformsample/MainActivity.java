package com.tingbob.fastformsample;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tingbob.fastform.FormBuilder;
import com.tingbob.fastform.IFormElementType;
import com.tingbob.fastform.listener.OnAttachAddClickListener;
import com.tingbob.fastform.listener.OnButtonClickListener;
import com.tingbob.fastform.listener.OnFormElementValueChangedListener;
import com.tingbob.fastform.listener.OnImageAddClickListener;
import com.tingbob.fastform.listener.OnRemoveClickListener;
import com.tingbob.fastform.listener.OnVideoAddClickListener;
import com.tingbob.fastform.model.FormElementButton;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerAttach;
import com.tingbob.fastform.model.FormElementPickerDate;
import com.tingbob.fastform.model.FormElementPickerImageMultiple;
import com.tingbob.fastform.model.FormElementPickerMulti;
import com.tingbob.fastform.model.FormElementPickerSingle;
import com.tingbob.fastform.model.FormElementPickerTime;
import com.tingbob.fastform.model.FormElementPickerVideoMultiple;
import com.tingbob.fastform.model.FormElementSwitch;
import com.tingbob.fastform.model.FormElementTextEmail;
import com.tingbob.fastform.model.FormElementTextMultiLine;
import com.tingbob.fastform.model.FormElementTextNumber;
import com.tingbob.fastform.model.FormElementTextNumberStatistic;
import com.tingbob.fastform.model.FormElementTextPassword;
import com.tingbob.fastform.model.FormElementTextPhone;
import com.tingbob.fastform.model.FormElementTextSingleLine;
import com.tingbob.fastform.model.FormHeader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFormElementValueChangedListener,
        OnImageAddClickListener, OnVideoAddClickListener, OnButtonClickListener, OnAttachAddClickListener, OnRemoveClickListener {

    private RecyclerView mRecyclerView;
    private FormBuilder mFormBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolBar();
        setupForm();
    }

    private void setupToolBar() {

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void setupForm() {

        mRecyclerView = findViewById(R.id.recyclerView);
        mFormBuilder = new FormBuilder(this, mRecyclerView);
        mFormBuilder.setOnFormElementValueChangeListener(this);
        mFormBuilder.setOnRemoveClickListener(this);

        FormHeader header1 = FormHeader.createInstance().setTitle("Personal Info").setTag("1");
        FormElementTextEmail element11 = FormElementTextEmail.createInstance().setTag("11").setTitle("Email").setHint("Enter Email").setRequired(true);
        FormElementTextPhone element12 = FormElementTextPhone.createInstance().setTag("12").setTitle("Phone").setValue("+8801712345678").setRequired(true);

        FormHeader header2 = FormHeader.createInstance().setTag("2").setTitle("Family Info");
        FormElementTextSingleLine element21 = FormElementTextSingleLine.createInstance().setTag("21").setTitle("Location").setValue("Dhaka").setRequired(true);
        FormElementTextMultiLine element22 = FormElementTextMultiLine.createInstance().setTag("22").setTitle("Address");
        FormElementTextNumber element23 = FormElementTextNumber.createInstance().setTag("23").setTitle("Zip Code").setValue("1000").setInputType(IFormElementType.TYPE_EDITTEXT_NUMBER_INT);
        FormElementTextNumber element24 = FormElementTextNumber.createInstance().setTag("24").setTitle("Zip2 Code").setValue("2000").setInputType(IFormElementType.TYPE_EDITTEXT_NUMBER_INT);
        FormElementTextNumber element25 = FormElementTextNumber.createInstance().setTag("25").setTitle("Zip3 Code").setValue("3000").setInputType(IFormElementType.TYPE_EDITTEXT_NUMBER_DECIMAL);
        List<String> list = new ArrayList<>();
        list.add("23");
        list.add("24");
        list.add("25");
        FormElementTextNumberStatistic element26 = FormElementTextNumberStatistic.createInstance().setTag("26").setTitle("Statistic").setStatisticTags(list);

        FormHeader header3 = FormHeader.createInstance().setTag("3").setTitle("Schedule");
        FormElementPickerDate element31 = FormElementPickerDate.createInstance().setTag("31").setTitle("Date").setDateFormat("MMM dd, yyyy");
        FormElementPickerTime element32 = FormElementPickerTime.createInstance().setTag("32").setTitle("Time").setTimeFormat("KK hh");
        FormElementTextPassword element33 = FormElementTextPassword.createInstance().setTag("33").setTitle("Password").setValue("abcd1234");

        FormHeader header4 = FormHeader.createInstance().setTag("4").setTitle("Preferred Items");
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Guava");
        FormElementPickerSingle element41 = FormElementPickerSingle.createInstance().setTag("41").setTitle("Single Item").setOptions(fruits).setPickerTitle("Pick any item");
        FormElementPickerMulti element42 = FormElementPickerMulti.createInstance().setTag("42").setTitle("Multi Items").setOptions(fruits).setPickerTitle("Pick one or more").setNegativeText("reset");
        FormElementSwitch element43 = FormElementSwitch.createInstance().setTag("43").setTitle("Frozen?").setSwitchTexts("Yes", "No");

        FormElementPickerImageMultiple element51 = FormElementPickerImageMultiple.createInstance().setTag("51").setTitle("Image Picker");
        mFormBuilder.setOnImageClickListener(this);

        List<String> names = new ArrayList<>();
        names.add("12345.pdf");
        names.add("2837938475928.doc");
        names.add("dsliewngiejio.pic");
        FormElementPickerAttach element52 = FormElementPickerAttach.createInstance().setTag("52").setTitle("Attach").setValue("Click to upload").setListValue(names);
        mFormBuilder.setOnAttachUploadClickListener(this);

        List<String> videoThumbs = new ArrayList<>();
        videoThumbs.add("https://file02.16sucai.com/d/file/2014/0829/372edfeb74c3119b666237bd4af92be5.jpg");
        FormElementPickerVideoMultiple element53 = FormElementPickerVideoMultiple.createInstance().setTag("53").setTitle("Video Picker").setListValue(videoThumbs);
        mFormBuilder.setOnVideoClickListener(this);

        FormElementButton element61 = FormElementButton.createInstance().setTag("61").setValue("+ Add a group");
        List<FormElementObject> objects = new ArrayList<>();
        objects.add(header4);
        objects.add(element41);
        objects.add(element42);
        objects.add(element43);
        objects.add(element51);
        objects.add(element52);
        objects.add(element53);
        element61.setActionAddElements(objects);
        mFormBuilder.setOnButtonClickListener(this);

        List<FormElementObject> formItems = new ArrayList<>();
        formItems.add(header1);
        formItems.add(element11);
        formItems.add(element12);
        formItems.add(header2);
        formItems.add(element21);
        formItems.add(element22);
        formItems.add(element23);
        formItems.add(element24);
        formItems.add(element25);
        formItems.add(element26);
        formItems.add(header3);
        formItems.add(element31);
        formItems.add(element32);
        formItems.add(element33);
        formItems.add(header4);
        formItems.add(element41);
        formItems.add(element42);
        formItems.add(element43);
        formItems.add(element51);
        formItems.add(element52);
        formItems.add(element53);
        formItems.add(element61);
        mFormBuilder.addFormElements(formItems);

    }

    @Override
    public void onValueChanged(FormElementObject formElement) {
        Toast.makeText(this, formElement.getValue(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageAddClick(String tag) {
        Toast.makeText(this, "Add Image Click tag = " + tag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVideoAddClick(String tag) {
        Toast.makeText(this, "Add Video Click tag = " + tag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClick(String buttonTag, List<String> tags) {
        Toast.makeText(this, "add first tag = " + tags.get(0), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttachAddClick(String tag) {
        Toast.makeText(this, "Attach Upload Click tag = " + tag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveClick(List<String> tags) {
        Toast.makeText(this, "remove first tag = " + tags.get(0), Toast.LENGTH_SHORT).show();
    }
}
