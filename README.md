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

### install

google it:

`how to install apk`

## how to use

## Known issues

## Features that need to be added

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

## other

```
if (switch_if_af.isChecked()) {
                focus(mPreviewView, null, camera);
            }
            ```
拍照            使用這個會有延遲(多一倍時間)
