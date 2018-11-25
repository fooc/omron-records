package roger.omron.ocr.api.baidu.result;

public class Location {
    private String width;
    private String top;
    private String left;
    private String height;

    @Override
    public String toString() {
        return "Location{" +
                "width='" + width + '\'' +
                ", top='" + top + '\'' +
                ", left='" + left + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
