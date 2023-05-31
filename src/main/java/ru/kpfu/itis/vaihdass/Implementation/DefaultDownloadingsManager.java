package ru.kpfu.itis.vaihdass.Implementation;

import ru.kpfu.itis.vaihdass.Abstractions.Downloading;
import ru.kpfu.itis.vaihdass.Abstractions.DownloadingsManager;

import java.util.LinkedList;
import java.util.List;

import static ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.utils.Color.green;
import static ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.utils.Color.purple;

public class DefaultDownloadingsManager implements DownloadingsManager {
    private List<Downloading> downloadings;

    public DefaultDownloadingsManager() {
        downloadings = new LinkedList<>();
    }

    @Override
    public void startNewDownloading(Downloading downloading) {
        downloadings.add(downloading);
        downloading.start();
    }

    @Override
    public String getDownloadingsInfo() {
        if (downloadings.size() == 0) {
            return purple("No downloading yet...");
        }

        StringBuilder result = new StringBuilder();
        result.append(green("~~~ Downloading: ~~~\n"));
        for (final Downloading downloading : downloadings) {
            result.append(downloading.toString()).append("\n");
        }
        return result.toString();
    }
}
