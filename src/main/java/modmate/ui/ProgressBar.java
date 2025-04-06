package modmate.ui;

public class ProgressBar {

    public static void print(int current, int total) {
        int progressBarWidth = 50; // Width of the progress bar
        int progress = (int) ((double) current / total * progressBarWidth);

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < progressBarWidth; i++) {
            if (i < progress) {
                bar.append("=");
            } else {
                bar.append(" ");
            }
        }
        bar.append("] ").append(current).append("/").append(total);

        System.out.print("\r" + bar.toString());
    }

}
