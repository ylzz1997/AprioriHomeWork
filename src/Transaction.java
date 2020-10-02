import java.util.*;

/**
 * Created by lhy on 2020/9/17.
 */
public class Transaction {
    private List<String> content;

    public Transaction(List<String> content) {
        this.content = content;
        Collections.sort(content,new Realize_Comparator());
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }


    private class Realize_Comparator implements Comparator<String> {
        @Override
        public int compare(String arg0, String arg1) {
            Integer arg2 = Integer.parseInt(arg0);
            Integer arg3 = Integer.parseInt(arg1);
            return arg2.compareTo(arg3);
        }
    }
}
