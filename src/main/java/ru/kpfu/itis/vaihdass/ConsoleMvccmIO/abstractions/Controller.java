package ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Request;

public interface Controller {
    void update(Request request);
}
