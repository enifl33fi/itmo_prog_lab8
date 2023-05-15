package inputWorkers;

import GUI.WorkingWindow;
import commands.CommandType;
import exceptions.WrongCommandException;
import managers.RequestManager;
import network.requests.Request;

public class RequestParser {
    private final RequestManager requestManager;

    public RequestParser(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public Request parse(WorkingWindow workingWindow, String line){
        String[] requestParts = line.split(" ");
        if (requestParts.length > 2) {
            throw new WrongCommandException();
        }
        CommandType curCommandType = CommandType.getByName(requestParts[0]);
        try {
            return  requestManager.getRequest(curCommandType).get(workingWindow, requestParts[1]);
        } catch (IndexOutOfBoundsException ignored) {
            return requestManager.getRequest(curCommandType).get(workingWindow, null);
        }

    }
}
