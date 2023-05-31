package ru.kpfu.itis.vaihdass.Abstractions;

public interface DownloadingsManager {
    void startNewDownloading(Downloading downloading);
    String getDownloadingsInfo();
}
