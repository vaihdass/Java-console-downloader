package ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Request;

public interface Controller {
    void update(Request request);
}
