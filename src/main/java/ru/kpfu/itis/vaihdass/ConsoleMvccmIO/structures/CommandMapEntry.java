package ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures;

import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions.ExpandableProperties;

public class CommandMapEntry {
    private Command command;
    private CommandInformation commandInformation;
    private ExpandableProperties expandableProperties;

    public CommandMapEntry(Command command, CommandInformation commandInformation, ExpandableProperties expandableProperties) {
        this.command = command;
        this.commandInformation = commandInformation;
        this.expandableProperties = expandableProperties;
    }

    public Command getCommand() {
        return command;
    }

    public CommandInformation getCommandInformation() {
        return commandInformation;
    }

    public ExpandableProperties getExpandableProperties() {
        return expandableProperties;
    }
}
