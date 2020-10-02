import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by lhy on 2020/9/15.
 */
public class AprioriHashTree {
    private HashTreeNode head;

    public AprioriHashTree(HashTreeNode head) {
        this.head = head;
    }

    public AprioriHashTree() {
        this.head = new HashTreeNode();
        head.setLevel(1);
    }

    public HashTreeNode getHead() {
        return head;
    }

    public boolean add(CandidateItem ci){
        return head.add(ci);
    }

    public boolean addAll(List<CandidateItem> lists){
        for(int i = 0;i<lists.size();i++){
           if(!add(lists.get(i))){
               return false;
           }
        }
        return true;
    }

    public void printTreeStruct(){
        Queue<HashTreeNode> q = new LinkedList<HashTreeNode>();
        q.add(head);
        int a = 0;
        while (!q.isEmpty()){
            HashTreeNode htn = q.remove();
            System.out.println("结点"+a+"号,在第"+htn.getLevel()+"层{");
            for(int i=0;i<Main.NODE_NUM;i++){
                if(htn.isLeaf()[i]){
                    System.out.println(" 当前是"+i+"号,是分支内容，候选集为{");
                        for(int b=0;b<htn.getCanNum(i);b++){
                        CandidateItem ci = htn.getContent(i)[b];
                         if(ci==null) {System.out.println(" {无内容}");break;}
                        System.out.print("  {");
                        for(String c:ci.getContent()){
                            System.out.print(" "+c+" ");
                        }
                        System.out.println("} 当前支持度计数为"+ci.getCount());
                    }
                    System.out.println("}");
                }else {
                    q.add(htn.getChild(i));
                    System.out.println(" 当前是"+i+"号,是子节点，可继续下潜");
                }
            }
            a++;
        }
    }
}
