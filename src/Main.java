import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("请输入节点分支数量与每一个节点所包含的候选集数量，两个数值请使用空格隔开：");
        Scanner s = new Scanner(System.in);
        if(s.hasNext()){
            NODE_NUM=s.nextInt();
        }
        if(s.hasNext()){
            LIST_NUM=s.nextInt();
        }
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
            System.out.println("比对路径");
            session.printTrail();
        }
        aht.printTreeStruct();
    }
}
