package ru.kpfu.itis.vaihdass.MvvmIO.CustomCommands;

import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Response;
import ru.kpfu.itis.vaihdass.MvvmIO.CustomProperties.DownloadingProperties;

public class DownloadingsInfo extends Command {
    @Override
    public void execute(Response response, Properties properties, ExpandableProperties expandableProperties) {
        if (!(expandableProperties instanceof DownloadingProperties)) {
            setWrongExpandablePropsView(response);
            return;
        }
        DownloadingProperties additionalData = (DownloadingProperties) expandableProperties;

        if (additionalData.getDownloadingsManager() == null) {
            setWrongExpandablePropsView(response);
            return;
        }

        response.setOutput(additionalData.getDownloadingsManager().getDownloadingsInfo());
    }
}
