package commands;

import collections.InteractiveCollection;
import collections.UsersCollection;
import dataBases.DataBasesManager;
import network.Response;
import network.requests.Request;

import java.security.NoSuchAlgorithmException;

public class ShowCommand extends Command {
    public ShowCommand(InteractiveCollection curCol, UsersCollection usersCol) {
        super(curCol, usersCol);
        this.description =
                "show : print all items of the collection as string output in the standard output";
        this.commandType = CommandType.SHOW;
    }

    @Override
    public Response execute(Request req) {
        Response res = new Response("Incorrect response. Can't show elements.");
        if (!req.hasArg() && !req.hasElement()){
            if (req.getLastUpdTime() < curCol.getLastUpdTime()){
                res.setResponseLine("got data");
                res.setData(curCol.show());
                res.setLastUpdTime(curCol.getLastUpdTime());
            }
            else {
                res.setResponseLine("client has last version");
            }
        }

        return res;
    }
}
