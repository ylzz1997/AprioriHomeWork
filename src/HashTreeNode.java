import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by lhy on 2020/9/14.
 */
public class HashTreeNode {
    private boolean[] isLeaf;
    private CandidateItem[][] content;
    private int[] canNum;
    private HashTreeNode[] child;
    private int level;
    public boolean[] isLeaf() {
        return isLeaf;
    }

    public CandidateItem[] getContent(int i) {
        return content[i];
    }

    public HashTreeNode() {
        level = 0;
        isLeaf = new boolean[Main.NODE_NUM];
        canNum = new int[Main.NODE_NUM];
        for(int i=0;i<isLeaf.length;i++)
            isLeaf[i]=true;
        for(int i=0;i<canNum.length;i++)
            canNum[i]=0;
        child = new HashTreeNode[Main.NODE_NUM];
        this.content = new CandidateItem[Main.NODE_NUM][Main.LIST_NUM];
    }

    public void setLeaf(boolean leaf,int i) {
        isLeaf[i] = leaf;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCanNum(int i) {
        return canNum[i];
    }

    public HashTreeNode getChild(int i) {
        return child[i];
    }

    private void separate(int nodeNum){
        isLeaf[nodeNum]=false;
        child[nodeNum]=new HashTreeNode();
        child[nodeNum].setLevel(this.level+1);
        for(int i=0;i<content[nodeNum].length;i++){
            child[nodeNum].add(content[nodeNum][i]);
        }
    }

    public boolean add(CandidateItem ci){
        int hashNum = Main.ahca.hashFun(Integer.parseInt(ci.getContent().get(level-1)));
        if(isLeaf[hashNum]){
            if(canNum[hashNum]<Main.LIST_NUM){
                content[hashNum][canNum[hashNum]] = ci;
                canNum[hashNum]++;
                return true;
            }else {
                if(level<Main.CANDIDATE_NUM){
                    separate(hashNum);
                    return child[hashNum].add(ci);
                }else {
                    return false;
                }
            }
        }else {
            return child[hashNum].add(ci);
        }
    }
}
