package lianjinxue201762.bawei.com.todayheadline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/2220:24
 */

public class Webviews extends AppCompatActivity implements View.OnClickListener{
    private TextView titleTxt;
    private ProgressBar progressBar;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        initView();
    }

    private void initView() {
        titleTxt=(TextView)findViewById(R.id.titlezhuti);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        webView=(WebView)findViewById(R.id.webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {

                super.onReceivedTitle(view, title);
                titleTxt.setText(title);

            }
        });


        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                view.loadUrl(url);
                //true  自己处理  false浏览器出处理 默认false
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view,url);
			System.out.println("end----"+ url);
			progressBar.setVisibility(View.GONE);
            }



        });




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                goback();
                break;
            case R.id.right:
                webView.reload();
                webView.goForward();
                break;

            default:
                break;
        }
    }

    private void goback() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            webView.clearView();
            finish();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(webView.canGoBack()){
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
