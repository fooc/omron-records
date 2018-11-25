package roger.omron.ocr.api.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Network {


    public static String doPost(String url,
                                Map<String, String> urlParams,
                                Map<String, String>  headerParams,
                                Map<String, String>  bodyParams) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        /**set url params*/
        if (urlParams != null){
            url += "?";
            for (String key: urlParams.keySet()){
                url += key + "=" + urlParams.get(key) + "&";
            }
        }


        /**HttpPost*/
        HttpPost httpPost = new HttpPost(url);

        /**set header params*/
        if (headerParams != null){
            for (String key: headerParams.keySet()){
                httpPost.setHeader(key, headerParams.get(key));
            }
        }


        /**set body params*/
        List paramData = new ArrayList();
        for (String key: bodyParams.keySet()){
            paramData.add(new BasicNameValuePair(key, bodyParams.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramData,"UTF-8"));

        /**HttpResponse*/
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        }finally{
            try{
                if(httpResponse!=null){
                    httpResponse.close();
                }
            }catch(IOException e){
                Logger.info("## release resouce error ##" + e);
            }
        }
        return result;
    }
}
