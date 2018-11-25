package roger.omron.ocr.api.youdao;

import roger.omron.ocr.api.images.ImageHandler;
import roger.omron.ocr.api.utils.MD5;
import roger.omron.ocr.api.utils.Network;

import java.util.HashMap;
import java.util.Map;

public class YoudaoOCR {

    public static void main(String[] args) throws Exception {
        Map map = new HashMap<String, String>();
        String url = "http://openapi.youdao.com/ocrapi";
        String appKey = "4e48190f447b653a";
        String detectType = "10011";
        String imageType = "1";
        String langType = "zh-en";
        String docType = "json";
        String salt = String.valueOf(System.currentTimeMillis());
        String img = ImageHandler.getImageBased64Str("/Users/roger/Downloads/130-85-96-crop.jpg");

        map.put("appKey", appKey);
        map.put("img", img);
        map.put("detectType", detectType);
        map.put("imageType", imageType);
        map.put("langType", langType);
        map.put("salt", salt);
        map.put("docType", docType);
        String sign = MD5.getMD5(appKey + img + salt + "");
        map.put("sign", sign);
        String result= Network.doPost(url, null, null, map);
        System.out.println(result);
    }


}