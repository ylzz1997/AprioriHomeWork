import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhy on 2020/9/14.
 */
public class CandidateItem {
    private List<String> content;
    private int count;
    public  CandidateItem(List<String> content){
        this.content = content;
        this.count=0;
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }

    public List<String> getContent() {
        return content;
    }
}
