package jgitdeamo;
import org.eclipse.jgit.util.FileUtils;
import org.eclipse.jgit.util.StringUtils;

import java.io.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 命令行辅助工具
 * @author jzhung
 */
public class Main {

    @Test
    public void test() {
        List<String> cmds = new ArrayList<>();
        cmds.add("git init");
        cmds.add("pwd");
        cmds.add("git add .");
        cmds.add("git status");
        cmds.add("git commit -m 'update'");
        cmds.add("git push");


        try {
            for (int i = 0; i < cmds.size(); i++) {
                execute(cmds.get(i));
                System.out.println("-----------------------------------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStream inStream = process.getInputStream();
        InputStream errStream = process.getErrorStream();
        SequenceInputStream sequenceIs = new SequenceInputStream(inStream, errStream);
        BufferedInputStream bufStream = new BufferedInputStream(sequenceIs);
        Reader reader = new InputStreamReader(bufStream, getDefaultEncoding());
        BufferedReader bufReader = new BufferedReader(reader);
        String line;
        while ((line = bufReader.readLine()) != null) {
            System.out.println(line);
            if(line.contains("conflict")) break;
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
        return os;
    }
}
