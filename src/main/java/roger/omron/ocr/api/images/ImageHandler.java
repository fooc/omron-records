package roger.omron.ocr.api.images;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import net.coobird.thumbnailator.Thumbnails;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageHandler {


    public static void preProcess(String originImageFile, String resImageFilePrefix){
        OpenCV.loadLocally();

        Mat originImage = Imgcodecs.imread(originImageFile);

        Mat grayImage = new Mat();
        Imgproc.cvtColor(originImage, grayImage, Imgproc.COLOR_BGR2GRAY);
        Imgcodecs.imwrite(resImageFilePrefix + "gray.jpg", grayImage);

        Mat thresImage = new Mat();
//        Imgproc.adaptiveThreshold(grayImage, thresImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 91, 5);
        Imgproc.threshold(grayImage, thresImage, 110, 255, Imgproc.THRESH_BINARY_INV);
        Imgcodecs.imwrite(resImageFilePrefix + "thres.jpg", thresImage);

        Mat erodeImage = new Mat();
        Mat structImage = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2, 2));
        Imgproc.erode(thresImage, erodeImage, structImage, new Point(-1, -1), 2);
        Imgcodecs.imwrite(resImageFilePrefix + "erode.jpg", erodeImage);


        Mat dilateImage = new Mat();
        Imgproc.dilate(thresImage, dilateImage, structImage, new Point(-1, -1), 30);
        Imgcodecs.imwrite(resImageFilePrefix + "dilate.jpg", dilateImage);

        Mat finalImage = new Mat();
//        Imgproc.adaptiveThreshold(grayImage, thresImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 91, 5);
        Imgproc.threshold(dilateImage, finalImage, 100, 255, Imgproc.THRESH_BINARY_INV);
        Imgcodecs.imwrite(resImageFilePrefix + "final.jpg", finalImage);

    }

    public static void scale(String originImage, double factor, String thumbnail) throws IOException {
        Thumbnails.of(originImage).scale(factor).toFile(thumbnail);
    }

    public static void scale(File originImage, double factor, String thumbnail) throws IOException {
        Thumbnails.of(originImage).scale(factor).toFile(thumbnail);
    }

    public static void scaleImages(String originDir, double factor, String thumbnailDir) throws IOException {
        File originFile = new File(originDir);
        File thumbnailFile = new File(thumbnailDir);
        if (!originFile.isDirectory() || !thumbnailFile.isDirectory()) {
            return;
        }

        for (File file : originFile.listFiles()) {
            if (file.isFile()) {
                String filename = file.getName().toUpperCase();
                /**莫名其妙，怎么每个文件都会对应一个._开头的文件？ */
                if ((!filename.startsWith("._")) && (filename.endsWith("JPG") || filename.endsWith("JPEG"))) {
//                    System.out.println(filename);
                    scale(file, factor, thumbnailDir + filename);
                }


            }
        }
    }

    /**
     * 获得图片的Base64编码
     *
     * @param imgFile
     * @return
     */
    public static String getImageBased64Str(String imgFile){
        return getImageBased64Str(new File(imgFile));
    }

    public static String getImageBased64Str(File imageFile)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        return Base64.encode(data);//返回Base64编码过的字节数组字符串
    }


    public static void main(String ... argv) throws IOException {
        String originDir = "/Users/roger/Codespace/omron-data/images/";
        String thumbnailDir = "/Users/roger/Codespace/omron-data/thumbnails/";
        double factor = 0.18;
        scaleImages(originDir, factor, thumbnailDir);
    }
}
