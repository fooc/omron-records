package roger.omron.ocr.api.baidu.result;

import java.util.List;

public class BaiduResultBean {
    private String log_id;
    private String direction;
    private String words_result_num;
    private List<WordsResult> words_result;


    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(String words_result_num) {
        this.words_result_num = words_result_num;
    }

    public List<WordsResult> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<WordsResult> words_result) {
        this.words_result = words_result;
    }

    @Override
    public String toString() {
        return "Jsonbean{" +
                "log_id='" + log_id + '\'' +
                ", direction='" + direction + '\'' +
                ", words_result_num='" + words_result_num + '\'' +
                ", words_result=" + words_result +
                '}';
    }
}
