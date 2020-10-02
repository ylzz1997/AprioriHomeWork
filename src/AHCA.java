import java.util.*;

/**
 * Created by lhy on 2020/9/19.
 */
public class AHCA {
    private boolean reHash = false;
    private double maxPar;
    private Map<Integer,Integer> intMap =  new HashMap<Integer,Integer>();
    public int hashFun(int a){
        //System.out.println(a%Main.NODE_NUM+" "+intMap.get(a)%Main.NODE_NUM);
        if(reHash){
            return intMap.get(a)%Main.NODE_NUM;
        }else {
            return a%Main.NODE_NUM;
        }
    }

    public double getMaxPar() {
        return maxPar;
    }

    public void setMaxPar(double maxPar) {
        this.maxPar = maxPar;
    }

    public AHCA(){
        maxPar = 1;
        reHash = false;
    }

    public AHCA(List<CandidateItem> cil,double maxPar){
        this.maxPar = maxPar;
        int[] ss = new int[Main.NODE_NUM];
        float[] ssf = new float[Main.NODE_NUM];
        int[] repeat = new int[Main.NODE_NUM];
        Set<Integer> noRepeat = new HashSet<Integer>();
        for(int i=0;i<ss.length;i++){
            ss[i]=0;
            repeat[i]=0;
        }
        int sum = 0;
        for(CandidateItem ci:cil){
            List<String> a = ci.getContent();
            for(String s :a){
                sum++;
                noRepeat.add(Integer.parseInt(s));
                int i = Integer.parseInt(s)%Main.NODE_NUM;
                ss[i]++;
            }
        }
        //开个数组
        for(int i=0;i<ss.length;i++){
            ssf[i] = (float)ss[i]/(float)sum;
            //System.out.println(ssf[i]);
            if(ssf[i]>maxPar){
                reHash=true;
                int c = 0;
                for(int b :noRepeat){
                    if(b%Main.NODE_NUM==i){
                       // System.out.println(b+"映射"+c);
                        intMap.put(b,c);
                        c++;
                    }else {
                        intMap.put(b,b);
                    }
                }
                break;
            }
        }
    }
}