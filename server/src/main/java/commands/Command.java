package commands;

import collections.InteractiveCollection;
import collections.UsersCollection;
import dataBases.DataBasesManager;
import network.Response;
import network.requests.Request;

public abstract class Command {
    protected CommandType commandType;
    protected String description = null;
    protected InteractiveCollection curCol;
    protected UsersCollection usersCol;

    public Command(InteractiveCollection curCol, UsersCollection usersCol) {
        this.curCol = curCol;
        this.usersCol = usersCol;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public abstract Response execute(Request req);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}