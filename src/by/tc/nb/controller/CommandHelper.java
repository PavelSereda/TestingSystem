package by.tc.nb.controller;

import by.tc.nb.command.Command;
import by.tc.nb.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private Map<String, Command> commands = new HashMap<String, Command>();

    public CommandHelper() {
        commands.put("SHOW_SUBJECTS", new ShowSubjects());
        commands.put("ADD_ANSWER", new AddAnswer());
        commands.put("ADD_TEST", new AddTest());
        commands.put("CHECK_TUTOR", new IsTutor());
        commands.put("SHOW_RESULTS", new ShowResults());

    }

    public Command getCommand(String commandName) {
        Command command;
        command = commands.get(commandName);

        return command;

    }

}
