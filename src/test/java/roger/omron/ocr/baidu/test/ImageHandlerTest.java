package roger.omron.ocr.baidu.test;

import org.junit.Test;
import roger.omron.ocr.api.images.ImageHandler;

public class ImageHandlerTest {

    @Test
    public void testImagePreProcess(){

        String originImage = "/Users/roger/Codespace/omron-data/crop/130-85-96-inv.jpg";
        String resImage = "/Users/roger/Codespace/omron-data/crop/130-85-96-inv-";
        ImageHandler.preProcess(originImage, resImage);
    }
}
