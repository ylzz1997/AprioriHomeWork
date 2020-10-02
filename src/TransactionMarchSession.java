import java.util.*;

/**
 * Created by lhy on 2020/9/17.
 */
public class TransactionMarchSession {
    private AprioriHashTree aht;
    private Transaction ta;
    private Queue<CandidateItem> trail;
    private Set<CandidateItem[]> dI;

    public TransactionMarchSession(AprioriHashTree aht, Transaction ta) {
        this.aht = aht;
        this.ta = ta;
        this.trail = new LinkedList<CandidateItem>();
        this.dI = new HashSet<CandidateItem[]>();
    }

    public void start(){
        int pdif = ta.getContent().size()-Main.CANDIDATE_NUM;
        if(pdif<0){
            System.out.print("禁止事务集大于候选集");
            return;
        }
        marchLoop(aht.getHead(),0);
    }

    private void marchLoop(HashTreeNode htn,int offset) {
        if(ta.getContent().size()-offset>Main.CANDIDATE_NUM){
            for(int m=0;m<ta.getContent().size()-offset-Main.CANDIDATE_NUM+1;m++){
                int i = Main.ahca.hashFun(Integer.parseInt(ta.getContent().get(htn.getLevel()+offset+m-1)));
                if(!dI.add(htn.getContent(i))){
                    continue;
                }
                if(htn.isLeaf()[i]){
                    for(int a=0;a<htn.getCanNum(i);a++){
                        trail.add(htn.getContent(i)[a]);
                        if(ta.getContent().containsAll(htn.getContent(i)[a].getContent())){
                            htn.getContent(i)[a].addCount();
                        }
                    }
                }else{
                    marchLoop(htn.getChild(i),m);
                }
            }
        }else {
            int i = Main.ahca.hashFun(Integer.parseInt(ta.getContent().get(htn.getLevel()+offset-1)));
            if(dI.add(htn.getContent(i))){
                if(htn.isLeaf()[i]){
                    for(int a=0;a<htn.getCanNum(i);a++){
                        trail.add(htn.getContent(i)[a]);
                        if(ta.getContent().containsAll(htn.getContent(i)[a].getContent())){
                            htn.getContent(i)[a].addCount();
                        }
                    }
                }
            }
        }
    }

    public void printTrail(){
        for(CandidateItem ci:trail){
            System.out.printf("{");
            for(String s : ci.getContent()){
                System.out.printf(" "+s);
            }
            System.out.println("}");
        }
    }
}
