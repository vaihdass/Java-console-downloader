package ru.kpfu.itis.vaihdass.Implementation;

import ru.kpfu.itis.vaihdass.Abstractions.Downloading;
import ru.kpfu.itis.vaihdass.Abstractions.DownloadingViewer;

import static ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.utils.Color.*;

public class ProgressiveDownloadingViewer implements DownloadingViewer {
    private String completedSign;
    private String crashedSign;
    private char progressCompletedSymbol;
    private char progressSymbol;

    public ProgressiveDownloadingViewer() {
        this("Completed", "Crashed", '\u25a0', '.');
    }

    public ProgressiveDownloadingViewer(String completedSign, String crashedSign, char progressCompletedSymbol, char progressSymbol) {
        this.completedSign = completedSign != null ? completedSign : "";
        this.crashedSign = crashedSign != null ? crashedSign : "";
        this.progressCompletedSymbol = progressCompletedSymbol;
        this.progressSymbol = progressSymbol;
    }

    @Override
    public String toString(Downloading downloading) {
        return purple(downloading.getContentName() + ": ") + getDownloadingStatus(downloading);
    }

    private String getDownloadingStatus(Downloading downloading) {
        if (downloading.getDownloadingStatus() == DownloadingStatus.COMPLETED) {
            return green(completedSign);
        } else if (downloading.getDownloadingStatus() == DownloadingStatus.CRASHED) {
            return red(crashedSign);
        } else {
            return yellow(getStatusBar(calcCompletedSymbolsCount(downloading.getCurrentSize(), downloading.getContentSize())));
        }
    }

    private int calcCompletedSymbolsCount(int currentSize, int contentSize) {
        int result = (int) Math.floor(((float) Math.abs(currentSize)) / Math.abs(contentSize) * 10);
        if (result < 0) result = 0;
        return Math.min(result, 10);
    }

    private String getStatusBar(int completedCount) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < completedCount; i++) {
            result.append(progressCompletedSymbol);
        }
        for (int i = completedCount; i < 10; i++) {
            result.append(progressSymbol);
        }
        result.append("]");
        return result.toString();
    }
}
