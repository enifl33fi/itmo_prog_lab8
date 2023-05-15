package commands;

import collections.InteractiveCollection;
import collections.UsersCollection;
import network.Response;
import network.requests.Request;

public class CheckLoginCommand extends Command {
    public CheckLoginCommand(InteractiveCollection curCol, UsersCollection usersCol){
        super(curCol, usersCol);
        this.commandType = CommandType.CHECK_LOGIN;
    }
    @Override
    public Response execute(Request req) {
        if (!req.hasArg() && !req.hasElement()){
            return usersCol.checkLogin(req.getUserContainer().getLeft());
        }
        return new Response("Incorrect request.");
    }
}
