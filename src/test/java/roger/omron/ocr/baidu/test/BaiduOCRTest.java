package roger.omron.ocr.baidu.test;

import org.junit.Assert;
import org.junit.Test;
import roger.omron.ocr.api.baidu.BaiduOCR;
import roger.omron.ocr.api.baidu.result.BaiduResultBean;
import roger.omron.ocr.api.utils.Logger;

import java.io.File;
import java.io.IOException;

public class BaiduOCRTest {

    @Test
    public void testBaiduORC() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        String thumbnailDir = "/Users/roger/Codespace/omron-data/thumbnails/";
//        String originDir = "/Users/roger/Codespace/omron-data/images/";
//        double factor = 0.1;
//        ImageHandler.scaleImages(originDir, factor, thumbnailDir);

        File thumbnailFile = new File(thumbnailDir);
        if (!thumbnailFile.isDirectory() || !thumbnailFile.isDirectory()) {
            return;
        }

        for (File file : thumbnailFile.listFiles()) {
            if (file.isFile()) {
                String filename = file.getName();
                /**莫名其妙，怎么每个文件都会对应一个._开头的文件？ */
                if ((!filename.startsWith("._")) &&
                        (filename.toUpperCase().endsWith("JPG") || filename.toUpperCase().endsWith("JPEG"))) {
                    Logger.debug("filename", filename);

                    BaiduResultBean baiduResultBean = BaiduOCR.recognize(file);
                    Logger.debug("baiduResultBean", baiduResultBean.toString());

                    Assert.assertEquals("3", baiduResultBean.getWords_result_num());

                    String[] res = filename.split("\\.");
                    res = res[0].split("-");
                    for (int i = 0; i < 3; i++) {
                        Assert.assertEquals(res[i], baiduResultBean.getWords_result().get(i).getWords());
                    }
                }
            }
        }
    }
}
