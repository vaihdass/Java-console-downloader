package ru.kpfu.itis.vaihdass.ConsoleMvccmIO.commands;

import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Response;

public class ExitCommand extends Command {
    @Override
    public void execute(Response response, Properties properties, ExpandableProperties expandableProperties) {
        System.exit(0);
    }
}
