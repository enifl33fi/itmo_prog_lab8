package GUI.tablePage;

import GUI.ArgumentType;
import GUI.DataSynchronizer;
import GUI.WorkingWindow;
import GUI.WorkingWindowAdapter;
import GUI.dialogs.CategoryAskerDialog;
import GUI.dialogs.ElementAskerDialog;
import GUI.dialogs.IdAskerDialog;
import GUI.dialogs.StringAskerDialog;
import client.Client;
import commands.CommandType;
import custom.RequestButton;
import element.CollectionPart;
import element.SpaceMarine;
import exceptions.NullFieldException;
import general.validator.GeneralValidator;
import network.Response;
import network.requests.RemoveByIdRequest;
import network.requests.Request;
import network.requests.ShowRequest;
import network.requests.UpdateRequest;

import javax.swing.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class TablePageLogic extends WorkingWindowAdapter {
    private TablePageModel model = new TablePageModel(this);
    private final Client client;
    private final GeneralValidator validator = new GeneralValidator();
    private Thread synchThread;
    private DataSynchronizer dataSynchronizer;

    public TablePageLogic(Client client){
        this.client = client;

    }
    public void execute(RequestButton button){
        client.sendReqGetRes(button.getCommandType());
    }
    public void help(){
        client.sendReqGetRes(CommandType.HELP);
    }
    @Override
    public void raiseCustomDialog(ArgumentType type, Request req){
        switch (type){
            case CATEGORY -> new CategoryAskerDialog(req);
            case STRING -> new StringAskerDialog(req);
            case ID -> new IdAskerDialog(req, this);
            case ELEMENT -> new ElementAskerDialog(req);

        }
    }
    @Override
    public void setIt(){
        dataSynchronizer = new DataSynchronizer(client, this);
        dataSynchronizer.setWork(true);
        synchThread = new Thread(dataSynchronizer);
        synchThread.start();
        model.setUserName(client.getLogin());
        model.setVisible(true);
    }
    @Override
    public String checkId(String idStr){
        try{
            validator.validateId(idStr);
            return client.checkId(idStr);
        } catch (NullFieldException | NumberFormatException e){
            return e.getMessage();
        }
    }
    @Override
    public void goBack(){
        dataSynchronizer.setWork(false);
        model.clearSortAndFilter();
        model.dispose();
        client.loadStartPage();
    }
    @Override
    public void goFurther(){
        dataSynchronizer.setWork(false);
        model.clearSortAndFilter();
        model.dispose();
        client.loadVisualizationPage();
    }
    public void setData(LinkedList<CollectionPart> data){
        model.setData(data);
    }
    public Client getClient(){
        return client;
    }
    public void setTableUpdate(boolean isUpdating){
        if(isUpdating){
            dataSynchronizer.setWork(true);
            synchThread = new Thread(dataSynchronizer);
            synchThread.start();
        } else {
            dataSynchronizer.setWork(false);
        }
    }
    public void applyChanges(Set<Integer> changedRows){
        for (Integer row:changedRows){
            try {

                SpaceMarine spaceMarine = validator.validateSpaceMarine(Arrays.stream(model.getChangedElements(row))
                        .map((o) -> (o != null) ? o.toString() : "")
                        .toArray(String[]::new));
                UpdateRequest updateRequest = new UpdateRequest(null);
                updateRequest.setArg(String.valueOf(spaceMarine.getId()));
                updateRequest.setElement(spaceMarine);
                client.sendReqGetRes(updateRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void doRemove(String idStr){
        RemoveByIdRequest request = new RemoveByIdRequest();
        request.setArg(idStr);
        client.sendReqGetRes(request);
    }
}
