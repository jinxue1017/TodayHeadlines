package bean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/614:52
 */

public class HttpUtils {
      public static String getUrlConnect(String urlpath){
              try {
                  URL url=new URL(urlpath);
                  HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                  httpURLConnection.setRequestMethod("GET");
                  httpURLConnection.setDoInput(true);
                  httpURLConnection.connect();
                  System.out.println(" -------> code "+httpURLConnection.getResponseCode());
                  if (httpURLConnection.getResponseCode()==200){
                      InputStream in = httpURLConnection.getInputStream();
                      StringBuffer str=new StringBuffer();
                      int len=-1;
                      byte[]buff=new byte[1024];
                      while ((len=in.read(buff))!=-1){
                          str.append(new String(buff,0,len,"utf-8"));
                      }
                      in.close();
                      httpURLConnection.disconnect();
                      return str.toString();
                  }else {
                      return  null;

                  }

              } catch (Exception e) {
                  e.printStackTrace();
              }

              return  null;
          }
}
