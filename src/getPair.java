import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class getPair {
    private String furl;
    private boolean showOnlyFirst2 = false;
    private List<String> tmpList;
    public String getProjName(String url){
        //F:\datasetalllllllllll\sampleGithub\1000+star\quasar\234-Fiber.java
        String[] strArray = url.split("\\\\");
        return strArray[4];
    }
    public getPair(String furl)  throws IOException {
        //1,F:\datasetalllllllllll\sampleGithub\50-100starhub\rcsjta\1215-SIPMessage.java,186,194,2,53
        //1,F:\datasetalllllllllll\sampleGithub\500-1000star\soot\2216-BodyBuilder.java,179,187,7,58
        //1,F:\datasetalllllllllll\sampleGithub\50-100starhub\SocialSDK\215-XmlSimpleExpression.java,97,119,8,102
        //0        1
        this.furl = furl;
        tmpList = new ArrayList<>();
        BufferedReader bR = new BufferedReader(new FileReader(furl));
        List<List<String>> tdList = new ArrayList<List<String>>();
        PrintWriter pw = new PrintWriter(furl + "clonepair");


        for (String str;(str = bR.readLine())!=null;){
            if (str == null || str.equals("")) {    //得用.equals
                continue;
            }
            String[] divString = str.split(",");
//            str = divString[0] + "," +getProjName(divString[1]) + "\n";
//            System.out.print(str);
            //先把同一个id的克隆类取出来
            if(Integer.parseInt(divString[0]) == tdList.size() ){
                //insert no q
                tdList.get(tdList.size() - 1).add(getProjName(divString[1]));

            }
            else if (Integer.parseInt(divString[0]) > tdList.size() ){
                tdList.add(new ArrayList<String>() );
                tdList.get(tdList.size() - 1).add(getProjName(divString[1]));
            }
        }
        //now all projName are store into a 2d list
        for(List<String> li : tdList){
            for(int i = 0; i < li.size() - 1; i ++){
                for(int j = i + 1; j < li.size(); j ++){
                    pw.write(li.get(i));
                    pw.write(",");
                    pw.write(li.get(j));
                    pw.write("\n");
                }
            }
        }
        pw.flush();

    }
    public static void main(String[] args) throws IOException {

        String furl = "A_type12.csv";
        getPair gP = new getPair(furl);

    }
}
