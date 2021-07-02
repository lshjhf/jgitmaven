package jgitdeamo;
//import org.eclipse.jgit.util.FileUtils;
//import org.eclipse.jgit.util.StringUtils;

import java.io.*;
//import org.junit.Test;

//import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 命令行辅助工具
 */
public class Main {

    public  static List<String> getFileContext1(String path) throws Exception {
        FileReader fileReader =new FileReader(path);
        BufferedReader bufferedReader =new BufferedReader(fileReader);
        List<String> list =new ArrayList<String>();
        String str=null;
        while((str=bufferedReader.readLine())!=null) {
            if(str.trim().length()>2) {
                list.add(str);
            }
        }
        return list;
    }


    public static void main(String[] args) throws Exception {


        //List<String> cmds = getFileContext1("/Users/daniel/Downloads/cmdcommit1.txt");
       /* cmds.add("git branch ss");
        cmds.add("git checkout ss");
        cmds.add("git pull");
        cmds.add("pwd");
        cmds.add("git add .");
        cmds.add("git branch -M main");

        cmds.add("git commit -m 'update'");
        cmds.add("git remote add origin https://github.com/lshjhf/jgitmaven.git");
        cmds.add("git push -u origin main");*/


        try {
            int i=0;
            while(true) {
                Scanner sc = new Scanner(System.in);

                List<String> cmds = new ArrayList<String>();

                String ad=sc.nextLine();
                if (ad.equals("exit")) break;
                cmds.add(ad);

                execute(cmds.get(0));

                System.out.println("-----------------------------------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String cmd) throws Exception {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStream inStream = process.getInputStream();
        InputStream errStream = process.getErrorStream();
        SequenceInputStream sequenceIs = new SequenceInputStream(inStream, errStream);
        BufferedInputStream bufStream = new BufferedInputStream(sequenceIs);
        Reader reader = new InputStreamReader(bufStream, getDefaultEncoding());
        BufferedReader bufReader = new BufferedReader(reader);
        String line;
        TestEmail a=new TestEmail();

        while ((line = bufReader.readLine()) != null) {

            if(line.contains("Automatic merge failed; fix conflicts and then commit the result."))
            {a.test();
                 }
            else System.out.println(line);
        }
        inStream.close();
        errStream.close();
        process.destroy();
    }

    public static String getDefaultEncoding() {
        if (getOS().trim().toLowerCase().startsWith("win")) {
            return "GBK";
        } else {
            return "UTF-8";
        }
    }

    public static String getOS() {
        String os = System.getProperty("os.name");
        System.out.println(os);
        return /*321*/os;
    }
}
