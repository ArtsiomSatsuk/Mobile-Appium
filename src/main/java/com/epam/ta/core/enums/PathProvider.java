package com.epam.ta.core.enums;

import java.io.File;

public enum PathProvider {

    //download 'Checklist_v6.6.1_apkpure.com.apk' and put it in 'apk' folder
    CHECKLIST_APK {
        public String getAbsolutePath() {
            return System.getProperty("user.dir") + File.separator + "apk" + File.separator + "Checklist_v6.6.1_apkpure.com.apk";
        }
    };

    public abstract String getAbsolutePath();

}