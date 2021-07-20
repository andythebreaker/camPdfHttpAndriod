# camPdfHttpAndriod

[![hackmd-github-sync-badge](https://hackmd.io/9HZ4SVC_S9-S6aFdMVn_0Q/badge)](https://hackmd.io/9HZ4SVC_S9-S6aFdMVn_0Q)

take photo on Android 8.0 + and pack it ino a pdf file then set up a http server on local for download on nearby device

## 注意

MAIN分支壞了，請切到"before merge"

## Introduction

1. Take photos of paper documents on your phone
2. Send to computer
3. Convert to pdf file
4. Download and save on computer
5. All the above actions are done in one go and no external services are required, increasing data security and speed

![](https://i.imgur.com/gw1RIgh.png)

## compatibility

- Smartphone or tablet with lens
- Wi-Fi connection capability is necessary, and it is recommended to have its own network capability
- android only

test success only on android 11+

can install on android 8+ device, but there will be problems that cannot be successfully installed or crashed.

Compiled version: android 11

## Download and install

### download

https://github.com/andythebreaker/camPdfHttpAndriod/files/6847262/app-release.zip

### install

google it:

`how to install apk`

## how to use

### Configure your phone and computer in the local network

Suggested steps

1. Connect the mobile phone to the mobile network (2G/3G/4G LTE/5G...etc)
2. Mobile phone sharing network (as a wifi base station)
3. Connect the computer to the mobile phone (using wifi)

### Start this application

As the title

### Confirm permissions

Requires access to network, camera, and file read and write permissions

Depending on the device and system, you may need to enter the settings page of the Android system to grant these permissions

### Shooting target file

Press the black button to take a photo. The completed photo can be seen in the list of this program. The list is currently located in the center of the graphical user interface of this program. Slide the list up and down to view the pictures that have been taken.

### Receive and download the pdf file

- View the top list of the graphical user interface of this program, slide the list up and down to find the red ip position
- Move to your computer
- Use the latest version of the Chrome browser and type in the ip location you just found, type "colon" at the back and then enter the port number (the default is 33445, this number can be viewed at the top right of the user interface of this program)
- The top action will look like this
> enter `192.168.87.87:33445`
- Next, press the only button on the page
- You may need to allow pop-up permissions (in your browser)
- A window will pop up (in a new tab), that is the pdf file of the target document
- Download this pdf file (you can rename it during the downloading process, if you want), and do whatever you want

## Functions and features

- File size A4
- Auto center focus, exposure adjustment (when the shutter is pressed)
- Lock the document direction (upright), when the phone's steering function is locked

## Known issues

github 上的issue...

### IDE

- 2個編譯階段警告
- 39個警告
- 29個typo

### APK

- 修正switch字樣
- sssss字樣移除
- 14.28字樣移除

## Features that need to be added

### 手機端

- zoom in
- android版本向下支援
- 存外部取SD卡
- 閃光燈
- 減少對焦延遲(預判對焦)
- 圖片寫入完成反饋優化(加速)
- 橫向頁預覽
- 頁預覽動態載入(減少延遲或重複讀寫記憶元件)
- 插入頁旗標與刪除頁按鈕功能
- 減少GUI物件密度(排版)

### 電腦端

- js重構
- 重命名欄位
- 直接下載
- 圖片集預覽
- pdf動態生成
- good GUI
- 減少延遲
- 動態圖片傳輸(WS伺服器主動)

## Development Platform

### IDE
win10, 64bit
Android Studio 4.2.2 (Wednesday, June 30, 2021)

#### Settings and versions

![](https://i.imgur.com/3ZyYjHR.png)

##### Package dependencies

```gradle=
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    implementation 'org.nanohttpd:nanohttpd:2.3.1'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation "androidx.camera:camera-camera2:1.1.0-alpha06"
    implementation "androidx.camera:camera-lifecycle:1.1.0-alpha06"
    implementation "androidx.camera:camera-view:1.0.0-alpha26"
    implementation "androidx.camera:camera-core:1.1.0-alpha06"
    implementation "androidx.camera:camera-extensions:1.0.0-alpha26"
    // a dependency on Jackson Databind
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.8.9'

}
```

### Verification platform

SAMSUNG Galaxy M11
android 11

## 技術細節

- cameraX
- jsPDF

## other

### java

```
if (switch_if_af.isChecked()) {
                focus(mPreviewView, null, camera);
            }
```
拍照
使用這個會有延遲(多一倍時間)


### js

at mainchunk.js
const generate pdf fromimages
整個是硬邏輯
下note:
問題出在photo->pdf要轉90度
ymaegin要減width