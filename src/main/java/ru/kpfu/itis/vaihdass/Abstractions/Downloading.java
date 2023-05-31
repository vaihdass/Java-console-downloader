package ru.kpfu.itis.vaihdass.Abstractions;

import ru.kpfu.itis.vaihdass.Implementation.DownloadingStatus;

public abstract class Downloading extends Thread implements Runnable {
    public abstract void run();
    public abstract String getContentName();
    public abstract int getContentSize();
    public abstract int getCurrentSize();

    public abstract DownloadingStatus getDownloadingStatus();
}
