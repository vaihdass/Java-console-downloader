package ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Response;

public interface Output {
    void updateView(Response response) throws IllegalArgumentException;
}
