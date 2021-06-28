package jgitdeamo;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
//import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
//import org.eclipse.jgit.merge.MergeStrategy;
//import org.eclipse.jgit.merge.ResolveMerger;
//import org.eclipse.jgit.merge.Merger;
//import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
//import org.eclipse.jgit.treewalk.FileTreeIterator;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;

//import static org.junit.Assert.assertTrue;

/**
 * JGit API测试
 */
public class gitTest {

    public String remotePath = "https://github.com/lshjhf/test1.git";//远程库路径
    public String localPath = "/Users/daniel/IdeaProjects/new";//下载已有仓库到本地路径
    public String initPath = "/Users/daniel/IdeaProjects/new1";//本地路径新建


    /**
     * 克隆远程库
     * @throws IOException
     * @throws GitAPIException
     */
    @Test
    public void testClone() throws IOException, GitAPIException {
        //设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider("lshjhf","bmqbmq123456");

        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();

        Git git= cloneCommand.setURI(remotePath) //设置远程URI
                .setBranch("master") //设置clone下来的分支
                .setDirectory(new File(localPath)) //设置下载存放路径
                .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                .call();

        System.out.print(git.tag());

    }


    public String clone() {
        String result;
        try {
            Git.cloneRepository()
                    .setURI("https://github.com/smltq/blog.git")
                    .setDirectory(new File("/blog"))
                    .call();
            result = "克隆成功了!";
        } catch (GitAPIException e) {
            result = e.getMessage();
            e.printStackTrace();
        }
        return result;
    }


    /*build*/@Test
            public void buildss() throws IOException {
    FileRepositoryBuilder builder = new FileRepositoryBuilder();
    Repository repository = builder.setGitDir(new File("/my/git/directory"))
            .readEnvironment() // scan environment GIT_* variables
            .findGitDir() // scan up the file system tree
            .build();}
    /**
     * 本地新建仓库
     */
    @Test
    public void testCreate() throws IOException {
        //本地新建仓库地址
        Repository newRepo = FileRepositoryBuilder.create(new File(initPath + "/.git"));
        newRepo.create();
    }

    /**
     * 本地仓库新增文件
     */
    @Test
    public void testAdd() throws IOException, GitAPIException {
        File myfile = new File(localPath + "/myfile3.txt");
        myfile.createNewFile();
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));

        //添加文件
        git.add().addFilepattern("myfile3").call();
    }
   /**git add所有到本地*/
    @Test
    public void add() throws IOException, GitAPIException {

        Git git = new Git(new FileRepository(localPath+"/.git"));

        //添加文件
        git.add().addFilepattern(".").call();
    }


    /**
     * 本地提交代码dddd
     */
    @Test
    public void testCommit() throws IOException, GitAPIException,
            JGitInternalException {
        //git仓库地址

        Git git = new Git(new FileRepository(localPath+"/.git"));
        //提交代码
        git.commit().setMessage("test jGit").call();
    }


    /**
     * 拉取远程仓库内容到本地
     */
    @Test
    public void testPull() throws IOException, GitAPIException {


            UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                    UsernamePasswordCredentialsProvider("username", "password");
            //git仓库地址
            Git git = new Git(new FileRepository(localPath + "/.git"));
            git.pull().setRemoteBranchName("master").
                    setCredentialsProvider(usernamePasswordCredentialsProvider).call();


        }


/**        @Test
        public void testmerge() throws IOException, GitAPIException {
            Merger ourMerger = MergeStrategy.OURS.newMerger(new FileRepository(localPath + "/.git"));
            boolean merge = ourMerger.merge(new ObjectId[]{new FileRepository(localPath + "/.git").resolve("a"), new FileRepository(localPath + "/.git").resolve("c")});
            assertTrue(merge);
        }
*/

    /**
     * push本地代码到远程仓库地址
     */
    @Test
    public void testPush() throws IOException, JGitInternalException,
            GitAPIException {

        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider("lshjhf","bmqbmq123456");
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));
        git.push().setRemote("origin").     setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }





@Test
    public void merge() throws GitAPIException , IOException {
       Git git = Git.cloneRepository()
                .setURI(remotePath)
                .setBranch("master")
                .setDirectory(new File(localPath))
                .call();
            ObjectId objectId = git.getRepository().resolve("origin/develop");
            MergeResult mergeResult = git.merge().include(objectId).call();


    }}
/*
   @Test
    public static void status() {
        File RepoGitDir = new File("/blog/.git");
        Repository repo = null;
        try {
            repo = new FileRepository(RepoGitDir.getAbsolutePath());
            Git git = new Git(repo);
            Status status = git.status().call();
            log.info("Git Change: " + status.getChanged());
            log.info("Git Modified: " + status.getModified());
            log.info("Git UncommittedChanges: " + status.getUncommittedChanges());
            log.info("Git Untracked: " + status.getUntracked());
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (repo != null) {
                repo.close();
            }
        }*/
/*    @Test
    public void testmerge() throws IOException, JGitInternalException,
            GitAPIException{
        Git git = new Git(new FileRepository(localPath+"/.git")); // you get it through a CloneCommand, InitCommand
        // or through the file system*/

        /**CheckoutCommand coCmd = git.checkout();
// Commands are part of the api module, which include git-like calls
        coCmd.setName("dev");
        coCmd.setCreateBranch(false); // probably not needed, just to make sure
        coCmd.call(); // switch to "master" branch

        MergeCommand mgCmd = git.merge();
        mgCmd.include("master"); // considered as a Ref to a branch
        MergeResult res = mgCmd.call(); // actually do the merge

        if (res.getMergeStatus().equals(MergeResult.MergeStatus.CONFLICTING)){
            System.out.println(res.getConflicts().toString());
            // inform the user he has to handle the conflicts
        }
}*/


  /*  public void merge1() throws GitAPIException, IOException {
        Git git = new Git(new FileRepository(localPath+"/.git")); // you get it through a CloneCommand, InitCommand
        // or through the file system

        CheckoutCommand coCmd = git.checkout();
// Commands are part of the api module, which include git-like calls
        coCmd.setName("master");
        coCmd.setCreateBranch(false); // probably not needed, just to make sure
        coCmd.call(); // switch to "master" branch

        MergeCommand mgCmd = git.merge();
        MergeResult res = mgCmd.call(); // actually do the merge

        if (res.getMergeStatus().equals(MergeResult.MergeStatus.CONFLICTING)){
        System.out.println(res.getConflicts().toString());
        // inform the user he has to handle the conflicts
        }




/**
    @RestController
    @Slf4j
    public class TestController {

        @RequestMapping("test")
        public String sayHello() {
            //cloneBranch();
            //checkOutBranch();
            //createNewBranch();
            //delectBranch();
            mergeBranch();
            return "Hello Spring Boot";
        }

        void cloneBranch() {
            String remotePath = "***";
            //权限验证
            UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                    UsernamePasswordCredentialsProvider("**", "**");
            try {
                List<String> branchs = new ArrayList<String>();
                branchs.add("refs/heads/test");
                branchs.add("refs/heads/dev_test_dev02");
                //====连接dev_test_dev01分支
                CloneCommand cloneCommand = Git.cloneRepository();
                Git git = cloneCommand.setURI(remotePath)//要从中克隆的uri
                        .setDirectory(new File("E:/repo"))//克隆到的目录
                        .setBranchesToClone(branchs)//.setBranch("refs/heads/test")
                        .setCredentialsProvider(usernamePasswordCredentialsProvider)
                        .setBranch("refs/heads/test")
                        .call();
                List<Ref> list = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();//ALL -a 所有分支  REMOTE -r 远程分支
                System.out.println(list);
                List<Ref> list1 = git.branchList().setListMode(ListBranchCommand.ListMode.REMOTE).call();//ALL -a 所有分支  REMOTE -r 远程分支
                System.out.println(list1);
                List<Ref> gitList = git.branchList().call();//本地分支
                for (Ref ref : gitList) {
                    System.out.println("分支：" + ref + "" + ref.getName() + "" + ref.getObjectId().getName());
                }

                System.out.println();


            } catch (Exception e) {
                System.out.println(e);
            }
        }


        void checkOutBranch() {
            //-   refs/heads/test   refs/remotes/origin/dev_test_dev02   refs/remotes/origin/test
            try {
                Git git = Git.open(new File("E:/repo"));
                List<Ref> list = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();//ALL -a 所有分支  REMOTE -r 远程分支
                System.out.println(list);
                //切换远端分支
//            Ref ref= git.checkout().setCreateBranch(true)//创建新分支
//            .setName("dev_test_dev02")
//            .setStartPoint("origin/dev_test_dev02")//对应于起点选项
//            .call();
//            System.out.println(ref);
                //切换本地分支
                Ref ref = git.checkout().setCreateBranch(false).setName("test").call();
                System.out.println(ref);

            } catch (Exception e) {
                System.out.println(e);
            }

        }

        void createNewBranch() {

            try {
                Git git = Git.open(new File("E:/repo"));
                List<Ref> list = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();//ALL -a 所有分支  REMOTE -r 远程分支
                System.out.println(list);
                //切换本地分支dev_test_dev02
                Ref ref = git.checkout().setCreateBranch(false).setName("dev_test_dev02").call();
                System.out.println(ref);
                //切换本地分支test
                ref = git.checkout().setCreateBranch(false).setName("test").call();
                System.out.println(ref);
                //新建
//            ref = git.checkout().setCreateBranch(true).setName("dev_test_dev01").call();
//            System.out.println(ref);
                //01 本地分支以存在 切换本地分支
                ref = git.checkout().setCreateBranch(false).setName("dev_test_dev01").call();
                System.out.println(ref);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        void delectBranch() {
            //权限验证
            UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                    UsernamePasswordCredentialsProvider("**", "***");
            try {
                Git git = Git.open(new File("E:/repo"));
                List<Ref> list = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();//ALL -a 所有分支  REMOTE -r 远程分支
                //删除远程分支
//            RefSpec refSpec1 = new RefSpec().setSource(null).setDestination(R_HEADS+"dev_test_dev02");
//            if (null == refSpec1) {
//                System.out.println("远程分支不存在"+"dev_test_dev02");
//            }
//            git.push().setCredentialsProvider(usernamePasswordCredentialsProvider).setRefSpecs(refSpec1).setRemote("origin").call();
//            System.out.println("删除分支成功");
//            System.out.println("--------------");

                //删除远程分支
//            RefSpec refSpec2 = new RefSpec().setSource(null).setDestination(R_HEADS+"dev_test_dev01");//设置目标
//            if (null == refSpec2) {
//                System.out.println("远程分支不存在"+"dev_test_dev01");
//            }
//            git.push().setCredentialsProvider(usernamePasswordCredentialsProvider).setRefSpecs(refSpec2).setRemote("origin").call();
//            System.out.println("删除分支成功");
//            System.out.println("--------------");
                //切换本地分支test
                Ref ref = git.checkout().setCreateBranch(false).setName("test").call();
                System.out.println(ref);
                //删除本地分支
                git.branchDelete().setBranchNames("refs/heads/dev_test_dev01").call();
                System.out.println("删除分支成功");
                System.out.println("--------------");

            } catch (Exception e) {

                System.out.println(e);
            }
        }

        void mergeBranch() {
            //权限验证
            UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                    UsernamePasswordCredentialsProvider("**", "**");
            try {
                Git git = Git.open(new File("E:/repo"));
                List<Ref> list = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();//ALL -a 所有分支  REMOTE -r 远程分支

                //新建 03 分支（03==test）
            Ref ref = git.checkout().setCreateBranch(true).setName("dev_test_dev03").call();
            System.out.println(ref);
//
            //切换到本地02
            ref = git.checkout().setCreateBranch(false).setName("dev_test_dev02").call();
            System.out.println(ref);
//
//            //新建 04 分支（04==02）
            Ref ref4 = git.checkout().setCreateBranch(true).setName("dev_test_dev04").call();
            System.out.println(ref4);
//
//            //切换到03
           ref = git.checkout().setCreateBranch(false).setName("dev_test_dev03").call();
            System.out.println(ref);
//
//            03 合并 04
           git.merge().include(ref4).call();

                //推送到远程
                Ref ref = git.checkout().setCreateBranch(false).setName("dev_test_dev04").call();
                System.out.println(ref);
                //git.pull().setCredentialsProvider(usernamePasswordCredentialsProvider).call();

                git.push().add(ref).setCredentialsProvider(usernamePasswordCredentialsProvider).call();

                List<Ref> list2 = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();//ALL -a 所有分支  REMOTE -r 远程分支
                System.out.println("---------------------------");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public String initSynchronizedObject(String param1, String param2) {
            return param1 + param2;
        }

*/



