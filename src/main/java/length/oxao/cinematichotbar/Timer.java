package length.oxao.cinematichotbar;

public class Timer {

    public static int timer = -1;
    public static void SetTimer(int time) {
        timer = time*20;
    }

    public static int getTiming() {
        return setPropertiess.getIProperty("timing");
    }
}
