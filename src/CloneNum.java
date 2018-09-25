import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

public class CloneNum {
    private class cloneVal{
        int inner = 0;
        int interval = 0;
    }
    private HashMap<String, cloneVal> cloneMap = new HashMap<String, cloneVal>();
    String furl;
    PrintWriter pw;
    public CloneNum(String furl) throws IOException {
        //stroe info into HashMap
        this.furl = furl;
        BufferedReader bR = new BufferedReader(new FileReader(furl));
         pw = new PrintWriter(furl + "Statics");

        for (String str;(str = bR.readLine())!=null;){
            String[] clonePair = str.split(",");
            if(clonePair[0].equals(clonePair[1])){
                //inner case
                if(cloneMap.get(clonePair[0]) == null){
                    //not added
                    cloneMap.put(clonePair[0],new cloneVal());
                }
                cloneMap.get(clonePair[0]).inner += 2;//inner add by 2
            }
            else {
                if(cloneMap.get(clonePair[0]) == null){
                    cloneMap.put(clonePair[0],new cloneVal());
                }
                if(cloneMap.get(clonePair[1]) == null){
                    cloneMap.put(clonePair[1],new cloneVal());
                }
                cloneMap.get(clonePair[0]).interval ++;
                cloneMap.get(clonePair[1]).interval ++;
            }
        }
    }
    public void printHashMap(){
        for(String key:cloneMap.keySet()){
            pw.write(key + ',' + cloneMap.get(key).inner + ',' + cloneMap.get(key).interval + '\n');
        }
        pw.flush();
    }
    public static void main(String[] args) throws IOException {
        String furl = "A_type12.csvclonepair";
        CloneNum cn = new CloneNum(furl);
        cn.printHashMap();
    }
}
