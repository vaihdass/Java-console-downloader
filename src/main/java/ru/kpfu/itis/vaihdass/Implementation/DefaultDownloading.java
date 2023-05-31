package ru.kpfu.itis.vaihdass.Implementation;

import ru.kpfu.itis.vaihdass.Abstractions.Downloading;
import ru.kpfu.itis.vaihdass.Abstractions.DownloadingViewer;
import ru.kpfu.itis.vaihdass.Abstractions.DownloadingsManager;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultDownloading extends Downloading {
    private URL source;
    private Path destination;
    private String name;
    private int contentSize;
    private int currentSize = 0;
    private DownloadingStatus downloadingStatus = DownloadingStatus.NOT_STARTED;
    private DownloadingViewer downloadingViewer;

    public DefaultDownloading(String sourceUrl, String destinationPath, DownloadingViewer downloadingViewer) throws IllegalArgumentException {
        this.downloadingViewer = downloadingViewer;

        try {
            this.source = new URL(sourceUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Source's URL is incorrect.", e);
        }
        try {
            contentSize = source.openConnection().getContentLength();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to get file data.", e);
        }
        if (source.getFile().equals("")) {
            throw new IllegalArgumentException("The file doesn't exist.");
        }
        if (contentSize == -1) {
            throw new IllegalArgumentException("The file doesn't exist/there's no access to it/the file size is too large.\n");
        }

        try {
            this.destination = Paths.get(destinationPath);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Destination's path is incorrect.", e);
        }
        if (destination.toFile().isFile()) {
            throw new IllegalArgumentException("Destination's path must be a directory for the file.");
        }

        for (int i =  this.source.getPath().length() - 1; i >= 0; i--) {
            if (this.source.getPath().charAt(i) == '/') {
                name = this.source.getPath().substring(i + 1);
                break;
            }
        }
    }

    @Override
    public void run() {
        Path filepath;
        try {
            filepath = Paths.get(destination.toString() + '/' + name);
            int attempt = 1;
            while (Files.exists(filepath)) {
                filepath = Paths.get(destination.toString() + "/ (" + attempt + ") " + name);
                attempt++;
            }
        } catch (InvalidPathException | SecurityException e) {
            downloadingStatus = DownloadingStatus.CRASHED;
            return;
        }

        try {
            downloadingStatus = DownloadingStatus.IN_PROGRESS;
            Files.createFile(filepath);
            try (InputStream sourceInStream = source.openStream();
                 OutputStream destinationOutStream = new BufferedOutputStream(Files.newOutputStream(filepath));
            ) {
                int data;
                int dataAccumulated = 0;
                int accumulatedBound = 64;
                while ((data = sourceInStream.read()) != -1) {
                    destinationOutStream.write(data);
                    dataAccumulated++;
                    if (dataAccumulated >= accumulatedBound) {
                        currentSize += dataAccumulated;
                        dataAccumulated = 0;
                        destinationOutStream.flush();
                    }
                }
                currentSize = contentSize;
            }
        } catch (Exception e) {
            downloadingStatus = DownloadingStatus.CRASHED;
            return;
        }

        downloadingStatus = DownloadingStatus.COMPLETED;
    }

    @Override
    public String getContentName() {
        return name;
    }

    @Override
    public int getContentSize() {
        return contentSize;
    }

    @Override
    public int getCurrentSize() {
        return currentSize;
    }

    @Override
    public DownloadingStatus getDownloadingStatus() {
        return downloadingStatus;
    }

    @Override
    public String toString() {
        if (downloadingViewer == null) return "";
        return downloadingViewer.toString(this);
    }
}
