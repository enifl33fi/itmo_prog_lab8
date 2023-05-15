package network.requests;

import GUI.ArgumentType;
import GUI.WorkingWindow;
import commands.CommandType;
import element.ElementParser;
import element.ElementValidator;
import inputWorkers.InputHandler;

public class UpdateRequest extends Request {
    private final transient ElementParser elementParser = new ElementParser();
    private final transient InputHandler inputHandler;
    private final transient ElementValidator elementValidator = new ElementValidator();

    public UpdateRequest(InputHandler inputHandler){
        super(CommandType.UPDATE);
        this.inputHandler = inputHandler;

    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        if (workingWindow != null){
            workingWindow.raiseCustomDialog(ArgumentType.ID, this);
            if (hasArg()) {
                workingWindow.raiseCustomDialog(ArgumentType.ELEMENT, this);
                if (hasElement()) {
                    return this;
                }
                return null;
            }
            return null;
        } else {
            Long.parseLong(arg);
            this.setArg(arg);
            this.setElement(elementValidator.validateSpaceMarine(inputHandler.readElem(), this.getUserContainer().getLeft()));
            return this;
        }
    }
}
