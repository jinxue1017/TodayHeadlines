package lianjinxue201762.bawei.com.todayheadline;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.lang.reflect.Type;

import bean.MessageBean;
import bean.Version;
import bean.VersionUtil;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/2815:28
 */

public class CheckGengxin extends AppCompatActivity {
    private String url = "http://172.18.47.93/checkversion.php";
    // 文件保存地址
    private String targetPath = "";
    private static final String TAG = "CheckGengxin";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gengxin);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = Environment.getExternalStorageDirectory();
            targetPath = file.getAbsolutePath() + File.separator;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        Log.d("lllllllllllllllll", "onCreate: "+progressDialog);

    }
    /**
     * 检查版本号
     */
    private void checkVerson() {
        int versionCode = VersionUtil.getVersionCode(this);
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("version", String.valueOf(versionCode));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("lllllllllllllllll", "onSuccess: " + result);
                Gson gson = new Gson();
                Type type = new TypeToken<MessageBean<Version>>() {
                }.getType();

                MessageBean<Version> messageBean = gson.fromJson(result, type);
                if (messageBean.isSuccess()) {
                    final Version version = messageBean.getResult();
                    // 有更新
                    if (version.isHasNewVersion()) {
                        // 强制更新
                        if (version.isMustUpdate()) {
                            new AlertDialog.Builder(CheckGengxin.this)
                                    .setTitle("版本更新")
                                    .setMessage("升级到最新版本")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            updateVersion(version.getUrl());
                                            dialog.dismiss();
                                        }
                                    })
                                    // 不可取消
                                    .setCancelable(false)
                                    .create().show();


                        } else {
                            // 选择更新
                            new AlertDialog.Builder(CheckGengxin.this)
                                    .setTitle("版本更新")
                                    .setMessage("是否要升级到最新版本")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            updateVersion(version.getUrl());
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .create().show();
                        }
                    } else {
                        // 无可用更新
                        Toast.makeText(CheckGengxin.this, "当前已经是最新版本", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("lllllllllllllllll", "onError: " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 版本更新
     *
     * @param url
     */
    private void updateVersion(String url) {
        targetPath = targetPath + System.currentTimeMillis() + ".apk";
        RequestParams params = new RequestParams(url);
        // 设置下载保存路径
        params.setSaveFilePath(targetPath);
        // xutils的文件下载
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                installApk(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(CheckGengxin.this, "下载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                if (isDownloading) {
                    progressDialog.setMessage("正在下载...");
                    progressDialog.show();
                    progressDialog.setMax((int) total);
                    progressDialog.setProgress((int) current);
                }
            }
        });
    }

    /**
     * 安装apk
     * @param file
     */
    private void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
