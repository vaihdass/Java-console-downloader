package ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Response;

public interface Model {
    void updateModel(Properties properties, Response response);
    String getDefaultView();
}
