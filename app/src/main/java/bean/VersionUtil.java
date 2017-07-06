package bean;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class VersionUtil {
    /**
     * 获取当前应用的versionCode
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 包管理
            PackageManager packageManager = context.getPackageManager();
            // 包名字
            String packageName = context.getPackageName();
            // 包信息
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            // 应用的版本code
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
