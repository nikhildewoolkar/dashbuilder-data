import java.nio.file.Files;
import java.nio.file.Paths;
public class Logic {
    public static void main(String args[])
    {
        try  
        {  
            String path="/home/nikhildewoolkar/Desktop/RedHat_Work/CSV_TO_JSON/the_oscar_award.csv";
            StringBuffer ans=new StringBuffer("[");
            Files.lines(Paths.get(path)).forEach(line -> {
                StringBuffer s=new StringBuffer("[\"");
                int commacount=0;
                int i=0;
                while(i<line.length())
                {
                    if(line.charAt(i)!=',' && line.charAt(i)!='\"')
                    {
                        s.append(line.charAt(i));
                    }
                    else if(line.length()>i+1 && line.charAt(i)=='\"')
                    {
                        i++;
                        commacount++;
                        while(true)
                        {
                            
                            if(line.charAt(i)=='\"')
                            {
                                commacount++;
                            }
                            if(commacount%2==0 && line.charAt(i+1)==',')
                            {
                                break;
                            }
                            s.append(line.charAt(i));
                            i++;
                        }
                        commacount=0;
                    }
                    else if(line.charAt(i)==',')
                    {
                        s.append("\""+","+"\"");
                    }
                    i++;
                }
                s.append("\"]");
                ans.append(s+(","));
            });
            String ans1=ans.toString();
            ans1=ans1.substring(0,ans.length()-1);
            ans1+="]";
            ans1=ans1.replace("True","true");
            ans1=ans1.replace("False","false");
            System.out.println(ans);
            Files.writeString(Paths.get("ans.json"), ans1);
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }
    }
}

