package ru.kpfu.itis.vaihdass.customCommands;

import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Request;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.structures.Response;
import ru.kpfu.itis.vaihdass.ConsoleMvccmIO.utils.ArgsParser;

public class ExampleCommand extends Command {
    @Override
    public void execute(Response response, Properties properties, ExpandableProperties expandableProperties) {
        // We can use such already implemented methods inside this command as:

        // print($Text):
        response.setOutput("$Text"); properties.getOutput().updateView(response);

        // input():
        String inputString1 = properties.getInput().requestNewCommand(); // -> Input string, but it's RAW (without trim, parse command and args, etc.)

        // input($Text):
        response.setOutput("$Text");
        response.setLinebreakAfter(false);
        properties.getOutput().updateView(response);
        String inputString2 = properties.getInput().requestNewCommand();

        // Colored strings:
        // (int utils.Color):
        // getColored($Text, AnsiColors.ANSI_$Color.$Type())
        // or just red($Text), black($Text), etc (8 default colors)

        // Remove brackets, first dash, spaces, empty arguments -> ArgsParser.parseInput():
        // Attention! If $Text is "" (empty) -> throws InputEmptyCommandException. Catch it.
        ArgsParser.parseInput(new Request("$Text", properties.getInput()), properties);
        // Now in properties getCommand() and getArgs() <-> first and second & other arguments in $Text

        // Attention! Additional:
        // Look at the internal service class of the extending properties, which is created below.
        // After creating an instance of the ConsoleIOFacade class,
        // use the addNewCustomCommand() method to add this command-an example, for example, like this:
        // addNewCustomCommand("$ExecutionName",
        //      CommandMapEntriesBuilder.getCommandEntry(
        //              new ExampleCommand(),
        //              new SomeNewProperties($Data), <- Attention!
        //              "$DescriptionName",
        //              "$Description"
        //      )
        // );

        // Now the command manager will always give this command these properties.
        // It remains to check that it's them and make a class cast:
        if (!(expandableProperties instanceof SomeNewProperties)) {
            setWrongExpandablePropsView(response);
            return;
        }
        SomeNewProperties additionalData = (SomeNewProperties) expandableProperties;

        Integer data = additionalData.getData(); // -> Our data
        // Do other things here...

        // If you don't want to use the ExpandableProperties class, just replace it with null in the above.
    }

    public static class SomeNewProperties implements ExpandableProperties {
        private final Integer data;

        public SomeNewProperties(Integer data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }
    }
}
