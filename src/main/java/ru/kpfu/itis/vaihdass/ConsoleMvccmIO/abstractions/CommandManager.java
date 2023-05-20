package ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.CommandMapEntry;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Response;

import java.util.Map;

public interface CommandManager {
    void executeCommand(Response response, Properties properties, ExpandableProperties expandableProperties);

    void setCommand(String executionName, CommandMapEntry commandMapEntry);

    void removeCommand(String commandName);

    Command getCommand(String commandName);

    CommandMapEntry getCommandMapEntry(String commandName);

    Map<String, CommandMapEntry> getCommands();
}
