package ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Response;

public interface Model {
    void updateModel(Properties properties, Response response);
    String getDefaultView();
}
