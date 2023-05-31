package ru.kpfu.itis.vaihdass;

import ru.kpfu.itis.vaihdass.Abstractions.DownloadingViewer;
import ru.kpfu.itis.vaihdass.Abstractions.DownloadingsManager;
import ru.kpfu.itis.vaihdass.Abstractions.iApp;
import ru.kpfu.itis.vaihdass.Implementation.DefaultDownloadingsManager;
import ru.kpfu.itis.vaihdass.Implementation.ProgressiveDownloadingViewer;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.ConsoleIOFacade;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.utils.CommandMapEntriesBuilder;
import ru.kpfu.itis.vaihdass.MvvmIO.CustomCommands.CreateDownloading;
import ru.kpfu.itis.vaihdass.MvvmIO.CustomCommands.DownloadingsInfo;
import ru.kpfu.itis.vaihdass.MvvmIO.CustomProperties.DownloadingProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.utils.Color.yellow;

public class App implements iApp {
    private ConsoleIOFacade consoleIO;
    private DownloadingsManager downloadingsManager;
    private DownloadingViewer downloadingViewer;

    public static void main(String[] args) {
        iApp app = new App();
    }

    public App() {
        init();
        start();
    }

    @Override
    public void init() {
        consoleIO = new ConsoleIOFacade(
                yellow("This is a program for downloading files from the network." +
                        "\nTo get information about the commands, write 'help'."),
                () -> yellow(">>> "),
                System.in,
                null
        );

        downloadingsManager = new DefaultDownloadingsManager();
        downloadingViewer = new ProgressiveDownloadingViewer();

        consoleIO.addNewCustomCommand("download",
                CommandMapEntriesBuilder.getCommandEntry(
                        new CreateDownloading(),
                        new DownloadingProperties(downloadingsManager, downloadingViewer),
                        "Download",
                        "Downloads a file from the network to a specific directory." +
                                "\nTo use, write 'download <URL (Network resource link)> <Path (Downloading directory)>'.")
        );

        consoleIO.addNewCustomCommand("info",
                CommandMapEntriesBuilder.getCommandEntry(
                        new DownloadingsInfo(),
                        new DownloadingProperties(downloadingsManager, downloadingViewer),
                        "Info",
                        "Displays information about uploaded/crashed files.")
        );
    }

    @Override
    public void start() {
        consoleIO.run();
    }
}