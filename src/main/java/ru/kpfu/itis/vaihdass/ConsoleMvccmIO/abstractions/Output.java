package ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Response;

public interface Output {
    void updateView(Response response) throws IllegalArgumentException;
}
