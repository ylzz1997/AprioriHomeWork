import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lhy on 2020/9/14.
 */
public class FileUtil {
    private String FILE_NAME = Main.FILE_NAME;
    private String TFILE_NAME = Main.TFILE_NAME;

    public List<String> read(){
        try {
            List<String> lists = Files.readAllLines(Paths.get(FILE_NAME));
            return lists;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> readT(){
        try {
            List<String> lists = Files.readAllLines(Paths.get(TFILE_NAME));
            return lists;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public List<CandidateItem> toCandidateItem(List<String> lists){
        List<CandidateItem> rtn = new ArrayList<CandidateItem>();
        for(String i : lists){
            List<String> list = new ArrayList<String>(Arrays.asList(i.split(",")));
            CandidateItem b = new CandidateItem(list);
            rtn.add(b);
        }
        return rtn;
    }

    public List<Transaction> toTransaction(List<String> lists){
        List<Transaction> rtn = new ArrayList<Transaction>();
        for(String i : lists){
            List<String> list = new ArrayList<String>(Arrays.asList(i.split(",")));
            Transaction b = new Transaction(list);
            rtn.add(b);
        }
        return rtn;
    }
}
