package br.com.uniritter.tasima.arqweb.marsroverkata.application;

import br.com.uniritter.tasima.arqweb.marsroverkata.domain.CommandService;

public class Rover {

    private CommandService commandService;

    public void setCommandService(CommandService value) {
        commandService = value;
    }
    public CommandService getCommandService() {
        return commandService;
    }

    public Rover(CommandService commandServiceValue) {
        setCommandService(commandServiceValue);
    }

    public void receiveCommands(String commands) throws Exception {
        for (char command : commands.toCharArray()) {
            if (!receiveSingleCommand(command)) {
                break;
            }
        }
    }

    public boolean receiveSingleCommand(char command) throws Exception {
        switch(Character.toUpperCase(command)) {
            case 'F':
                return getCommandService().moveForward();
            case 'B':
                return getCommandService().moveBackward();
            case 'L':
                getCommandService().changeDirectionLeft();
                return true;
            case 'R':
                getCommandService().changeDirectionRight();
                return true;
            default:
                throw new Exception("Command " + command + " is unknown.");
        }
    }

    public String getPosition() {
        return getCommandService().toString();
    }

}