package ForUsers.GUI.others;


 // 获取图片路径

public class Path {

    private static final String P_Path = "D:\\Java课设\\src\\ForUsers\\Picture\\";

    public static String getRealPath(String relativePath) {
        return P_Path + relativePath;
    }
}
