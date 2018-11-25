package roger.omron.ocr.api.baidu.result;

public class WordsResult {
    private Location location;
    private String words;

    @Override
    public String toString() {
        return "WordsResult{" +
                "location=" + location +
                ", words='" + words + '\'' +
                '}';
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
