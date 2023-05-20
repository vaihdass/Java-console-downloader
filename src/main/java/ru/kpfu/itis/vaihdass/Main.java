package ru.kpfu.itis.vaihdass;

import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.ConsoleIOFacade;

import static ru.kpfu.itis.vaihdass.ConsoleMvccmIO.utils.Color.yellow;

public class Main {
    public static void main(String[] args) {
        ConsoleIOFacade consoleIO = new ConsoleIOFacade(yellow("Text..."), () -> yellow(">>> "), System.in);
        consoleIO.run();
    }
}