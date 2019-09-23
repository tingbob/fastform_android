package com.tingbob.fastform;

public interface IFormElementType {
    // different types for the form elements
    int TYPE_HEADER = 0;
    int TYPE_EDITTEXT_TEXT_SINGLELINE = 1;
    int TYPE_EDITTEXT_TEXT_MULTILINE = 2;
    int TYPE_EDITTEXT_NUMBER = 3;
    int TYPE_EDITTEXT_EMAIL = 4;
    int TYPE_EDITTEXT_PHONE = 5;
    int TYPE_EDITTEXT_PASSWORD = 6;
    int TYPE_PICKER_DATE = 7;
    int TYPE_PICKER_TIME = 8;
    int TYPE_PICKER_SINGLE = 9;
    int TYPE_PICKER_MULTI = 10;
    int TYPE_SWITCH = 11;
}
