package intellinectsschool.intellinects.com.v4flavors.Fcm;

import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.io.File;
//


/**
 * Created by Indrajeet  on 26-05-2017
 */

public class IntellinectsApp extends MultiDexApplication {

    protected File extStorageAppBasePath;

    protected File extStorageAppCachePath;

    private static IntellinectsApp instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        configureCrashReporting();

        instance = this;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){

            File externalStorageDir = Environment.getExternalStorageDirectory();

            if (externalStorageDir != null) {
                extStorageAppBasePath = new File(externalStorageDir.getAbsolutePath() + File.separator
                        + "Divine Child CBSE" + File.separator + "data" + File.separator + getPackageName());
            }
            if (extStorageAppBasePath != null) {
                extStorageAppCachePath = new File(extStorageAppBasePath.getAbsolutePath() + File.separator + "cache");
                boolean isCachePathAvailable = true;
                if (!extStorageAppCachePath.exists()) {
                    isCachePathAvailable = extStorageAppCachePath.mkdirs();
                }
                if (!isCachePathAvailable) {
                    extStorageAppCachePath = null;
                }
            }
        }
        //VTLib.init(IntellinectsApp.getAppContext());
    }

    private void configureCrashReporting() {

//        Crashlytics crashlyticsKit = new Crashlytics.Builder()
//                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
//                .build();
//
//        Fabric.with(this, crashlyticsKit);
    }

    @Override
    public File getCacheDir() {
        if (extStorageAppCachePath != null) {
            return extStorageAppCachePath;
        } else {
            return super.getCacheDir();
        }
    }

    public static IntellinectsApp getInstance(){
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }

}
