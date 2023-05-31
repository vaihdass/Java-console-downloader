package ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.commands;

import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Response;

public class ExitCommand extends Command {
    @Override
    public void execute(Response response, Properties properties, ExpandableProperties expandableProperties) {
        System.exit(0);
    }
}
