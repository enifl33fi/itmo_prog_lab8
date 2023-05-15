package commands;

import collections.InteractiveCollection;
import collections.UsersCollection;
import network.Response;
import network.requests.Request;

import java.security.NoSuchAlgorithmException;

public class CheckIdCommand extends Command {
    public CheckIdCommand(InteractiveCollection curCol, UsersCollection usersCol){
        super(curCol, usersCol);
        this.commandType = CommandType.CHECK_ID;
    }
    @Override
    public Response execute(Request req) {
        Response res = new Response("Incorrect request1.");
        try {
            if (this.usersCol.checkUser(req.getUserContainer())){
                if (req.hasArg() && !req.hasElement()){
                    res.setResponseLine(curCol.checkId(Long.parseLong(req.getArg()), req.getUserContainer().getLeft()));
                }
            }else {
                res.setExit(true);
            }
        } catch (NoSuchAlgorithmException e) {
            res.setResponseLine("Server exception while handling login and password");
        }
        return res;
    }
}
