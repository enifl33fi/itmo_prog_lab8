package network.requests;

import GUI.ArgumentType;
import GUI.WorkingWindow;
import commands.CommandType;
import element.ElementParser;
import element.ElementValidator;
import inputWorkers.InputHandler;

public class RemoveLowerRequest extends Request {
    private final transient ElementParser elementParser = new ElementParser();
    private final transient InputHandler inputHandler;
    private final transient ElementValidator elementValidator = new ElementValidator();

    public RemoveLowerRequest(InputHandler inputHandler){
        super(CommandType.REMOVE_LOWER);
        this.inputHandler = inputHandler;

    }

    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        if (workingWindow != null){
            workingWindow.raiseCustomDialog(ArgumentType.ELEMENT, this);
            if (hasElement()) {
                return this;
            }
            return null;
        } else {
            this.setElement(elementValidator.validateSpaceMarine(inputHandler.readElem(), this.getUserContainer().getLeft()));
            return this;
        }
    }
}
