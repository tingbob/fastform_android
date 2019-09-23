package com.tingbob.fastformsample;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tingbob.fastform.FormBuilder;
import com.tingbob.fastform.listener.OnFormElementValueChangedListener;
import com.tingbob.fastform.model.FormElementObject;
import com.tingbob.fastform.model.FormElementPickerDate;
import com.tingbob.fastform.model.FormElementPickerMulti;
import com.tingbob.fastform.model.FormElementPickerSingle;
import com.tingbob.fastform.model.FormElementPickerTime;
import com.tingbob.fastform.model.FormElementSwitch;
import com.tingbob.fastform.model.FormElementTextEmail;
import com.tingbob.fastform.model.FormElementTextMultiLine;
import com.tingbob.fastform.model.FormElementTextNumber;
import com.tingbob.fastform.model.FormElementTextPassword;
import com.tingbob.fastform.model.FormElementTextPhone;
import com.tingbob.fastform.model.FormElementTextSingleLine;
import com.tingbob.fastform.model.FormHeader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFormElementValueChangedListener {

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
        mFormBuilder = new FormBuilder(this, mRecyclerView, this);

        FormHeader header1 = FormHeader.createInstance("Personal Info");
        FormElementTextEmail element11 = FormElementTextEmail.createInstance().setTitle("Email").setHint("Enter Email");
        FormElementTextPhone element12 = FormElementTextPhone.createInstance().setTitle("Phone").setValue("+8801712345678");

        FormHeader header2 = FormHeader.createInstance("Family Info");
        FormElementTextSingleLine element21 = FormElementTextSingleLine.createInstance().setTitle("Location").setValue("Dhaka");
        FormElementTextMultiLine element22 = FormElementTextMultiLine.createInstance().setTitle("Address");
        FormElementTextNumber element23 = FormElementTextNumber.createInstance().setTitle("Zip Code").setValue("1000");

        FormHeader header3 = FormHeader.createInstance("Schedule");
        FormElementPickerDate element31 = FormElementPickerDate.createInstance().setTitle("Date").setDateFormat("MMM dd, yyyy");
        FormElementPickerTime element32 = FormElementPickerTime.createInstance().setTitle("Time").setTimeFormat("KK hh");
        FormElementTextPassword element33 = FormElementTextPassword.createInstance().setTitle("Password").setValue("abcd1234");

        FormHeader header4 = FormHeader.createInstance("Preferred Items");
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Guava");
        FormElementPickerSingle element41 = FormElementPickerSingle.createInstance().setTitle("Single Item").setOptions(fruits).setPickerTitle("Pick any item");
        FormElementPickerMulti element42 = FormElementPickerMulti.createInstance().setTitle("Multi Items").setOptions(fruits).setPickerTitle("Pick one or more").setNegativeText("reset");
        FormElementSwitch element43 = FormElementSwitch.createInstance().setTitle("Frozen?").setSwitchTexts("Yes", "No");

        List<FormElementObject> formItems = new ArrayList<>();
        formItems.add(header1);
        formItems.add(element11);
        formItems.add(element12);
        formItems.add(header2);
        formItems.add(element21);
        formItems.add(element22);
        formItems.add(element23);
        formItems.add(header3);
        formItems.add(element31);
        formItems.add(element32);
        formItems.add(element33);
        formItems.add(header4);
        formItems.add(element41);
        formItems.add(element42);
        formItems.add(element43);
        mFormBuilder.addFormElements(formItems);

    }

    @Override
    public void onValueChanged(FormElementObject formElement) {
        Toast.makeText(this, formElement.getValue(), Toast.LENGTH_SHORT).show();
    }
}
