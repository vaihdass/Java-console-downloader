package ru.kpfu.itis.vaihdass.MvvmIO.CustomCommands;

import ru.kpfu.itis.vaihdass.Abstractions.Downloading;
import ru.kpfu.itis.vaihdass.Implementation.DefaultDownloading;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.structures.Response;
import ru.kpfu.itis.vaihdass.MvvmIO.CustomProperties.DownloadingProperties;

import static ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.utils.Color.green;
import static ru.kpfu.itis.vaihdass.MvvmIO.ConsoleMvccmIO.utils.Color.red;

public class CreateDownloading extends Command {
    @Override
    public void execute(Response response, Properties properties, ExpandableProperties expandableProperties) {
        if (!(expandableProperties instanceof DownloadingProperties)) {
            setWrongExpandablePropsView(response);
            return;
        }
        DownloadingProperties additionalData = (DownloadingProperties) expandableProperties;

        if (additionalData.getDownloadingsManager() == null || additionalData.getDownloadingViewer() == null) {
            setWrongExpandablePropsView(response);
            return;
        }

        if (properties.getArgs() == null || properties.getArgs().size() < 2
                || properties.getArgs().get(0) == null || properties.getArgs().get(1) == null
        ) {
            response.setOutput(red("The arguments are set incorrectly." +
                    "\nPlease write 'help' to understand how to set them correctly."));
            return;
        }

        try {
            Downloading downloading = new DefaultDownloading(
                    properties.getArgs().get(0),
                    properties.getArgs().get(1),
                    additionalData.getDownloadingViewer()
            );

            additionalData.getDownloadingsManager().startNewDownloading(downloading);
        } catch (IllegalArgumentException e) {
            response.setOutput(red(e.getMessage()));
        }
    }
}
