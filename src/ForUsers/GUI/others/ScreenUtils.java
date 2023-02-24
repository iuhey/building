package ForUsers.GUI.others;

import java.awt.*;

 // 获取电脑屏幕的宽度和高度，以便页面弹出在屏幕中央

public class ScreenUtils {
    // 获取宽度
    public static int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    // 获取高度
    public static int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
