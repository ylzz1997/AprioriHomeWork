import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhy on 2020/9/14.
 */
public class Main {
    public static String  FILE_NAME = System.getProperty("user.dir")+ "/candidateItem.txt";
    public static String  TFILE_NAME = System.getProperty("user.dir")+ "/transactionItem.txt";
    public static int CANDIDATE_NUM = 3;
    public static int NODE_NUM = 3;
    public static int LIST_NUM = 3;
    public static FileUtil fileUtil = new FileUtil();
    public static AHCA ahca;
    public static void main(String[] args) {
        List<String> CI = fileUtil.read();
        List<CandidateItem> LC = fileUtil.toCandidateItem(CI);
        CANDIDATE_NUM = LC.get(0).getContent().size();
        ahca = new AHCA(LC,1.0/NODE_NUM+0.05);
        AprioriHashTree aht = new AprioriHashTree();
        if(!aht.addAll(LC)){
            System.out.println("树高度高于候选集的项数！请调整节点数量或内容数组数量以适应更大数量的候选集!");
            return;
        }
        List<String> TI = fileUtil.readT();
        List<Transaction> LT = fileUtil.toTransaction(TI);
        for(Transaction t:LT){
            TransactionMarchSession session = new TransactionMarchSession(aht,t);
            session.start();
            session.printTrail();
        }
        aht.printTreeStruct();
    }
}
