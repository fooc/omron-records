package roger.omron.ocr.api.baidu;

import com.google.gson.Gson;
import roger.omron.ocr.api.baidu.result.BaiduResultBean;
import roger.omron.ocr.api.images.ImageHandler;
import roger.omron.ocr.api.utils.Network;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaiduOCR {

//    static private final String appID = "14927360";
//    static private final String appKey = "";
//    static private final String secretKey = "";

    static private final String access_token = "24.2ce134089be225522f314d53c933c692.2592000.1545617311.282335-14927360";
    static private final String image_type = "BASE64";
    static private final String detect_direction = "true";

    static private final String Content_Type = "application/x-www-form-urlencoded";

    static private final String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/numbers";

    static private final String imageFile = "/Users/roger/Codespace/omron-data/crop/130-85-96-inv-final.jpg";

    public static BaiduResultBean recognize(String imageFile) throws IOException {
        return recognize(new File(imageFile));
    }

    public static BaiduResultBean recognize(File imageFile) throws IOException {
        String imageBase64 = ImageHandler.getImageBased64Str(imageFile);

        Map urlParams = new HashMap();
        urlParams.put("access_token", access_token);

        Map headerParams = new HashMap();
        headerParams.put("Content-Type", Content_Type);

        Map bodyParams = new HashMap();
        bodyParams.put("image_type", image_type);
        bodyParams.put("image", imageBase64);
        bodyParams.put("detect_direction", detect_direction);

        String result = Network.doPost(url, urlParams, headerParams, bodyParams);
        return new Gson().fromJson(result, BaiduResultBean.class);
    }

    public static void main(String ... argv) throws IOException {
//        String jsonStr = "{\"log_id\": 9029880562684967993, \"direction\": 0, \"words_result_num\": 3, \"words_result\": [{\"location\": {\"width\": 107, \"top\": 125, \"left\": 74, \"height\": 39}, \"words\": \"132\"}, {\"location\": {\"width\": 111, \"top\": 159, \"left\": 72, \"height\": 40}, \"words\": \"94\"}, {\"location\": {\"width\": 117, \"top\": 191, \"left\": 72, \"height\": 35}, \"words\": \"99\"}]}";
//
//        BaiduResultBean baiduResultBean = new Gson().fromJson(jsonStr, BaiduResultBean.class);
        BaiduResultBean baiduResultBean = recognize(imageFile);
        System.out.println(baiduResultBean.toString());
        System.out.println(baiduResultBean.getLog_id());
        System.out.println(baiduResultBean.getDirection());
        System.out.println(baiduResultBean.getWords_result_num());
        System.out.println(baiduResultBean.getWords_result().get(0).getWords());
        System.out.println(baiduResultBean.getWords_result().get(1).getWords());
        System.out.println(baiduResultBean.getWords_result().get(2).getWords());

    }

}
