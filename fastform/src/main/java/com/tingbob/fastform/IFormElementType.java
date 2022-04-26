package com.tingbob.fastform;

public interface IFormElementType {
    // different types for the form elements
    int TYPE_HEADER = 0;                        // 组标题
    int TYPE_EDITTEXT_TEXT_SINGLELINE = 1;      // 单行edit text
    int TYPE_EDITTEXT_TEXT_MULTILINE = 2;       // 多行edit text
    int TYPE_EDITTEXT_NUMBER = 3;               // 数字
    int TYPE_EDITTEXT_EMAIL = 4;                // email
    int TYPE_EDITTEXT_PHONE = 5;                // phone
    int TYPE_EDITTEXT_PASSWORD = 6;             // password
    int TYPE_PICKER_DATE = 7;                   // 日期选择
    int TYPE_PICKER_TIME = 8;                   // 时间选择
    int TYPE_PICKER_SINGLE = 9;                 // 几个选项的单选
    int TYPE_PICKER_MULTI = 10;                 // 几个选项的多选
    int TYPE_SWITCH = 11;                       // 切换开关按钮
    int TYPE_NUMBER_STATISTIC = 12;             // 数字统计
    int TYPE_PICKER_IMAGE_MULTIPLE = 13;        // image多选
    int TYPE_BUTTON = 14;                       // 按钮
    int TYPE_PICKER_ATTACH = 15;                // 附件选择
    int TYPE_TEXT_NORMAL = 16;                  // 文字显示
    int TYPE_IMAGE_NORMAL = 17;                 // image显示
    int TYPE_ATTACH_NORMAL = 18;                // 附件显示
    int TYPE_EDITTEXT_NUMBER_INT = 19;          // 整型数字编辑框
    int TYPE_EDITTEXT_NUMBER_DECIMAL = 20;      // 小数点数字编辑框
    int TYPE_PICKER_VIDEO_MULTIPLE = 21;        // video多选
    int TYPE_VIDEO_NORMAL = 22;                 // video显示
    int TYPE_TEXT_QRCODE_SCAN = 23;                 // video显示
}
