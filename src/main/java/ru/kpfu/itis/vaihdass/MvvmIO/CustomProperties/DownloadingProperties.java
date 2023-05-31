package ru.kpfu.itis.vaihdass.MvvmIO.CustomProperties;

import ru.kpfu.itis.vaihdass.Abstractions.DownloadingViewer;
import ru.kpfu.itis.vaihdass.Abstractions.DownloadingsManager;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions.ExpandableProperties;

public class DownloadingProperties implements ExpandableProperties {
    private final DownloadingsManager downloadingsManager;
    private final DownloadingViewer downloadingViewer;

    public DownloadingProperties(DownloadingsManager downloadingsManager, DownloadingViewer downloadingViewer) {
        this.downloadingsManager = downloadingsManager;
        this.downloadingViewer = downloadingViewer;
    }

    public DownloadingsManager getDownloadingsManager() {
        return downloadingsManager;
    }

    public DownloadingViewer getDownloadingViewer() {
        return downloadingViewer;
    }
}
