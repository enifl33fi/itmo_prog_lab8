package GUI;

import client.Client;
import network.Response;
import network.requests.ShowRequest;

public class DataSynchronizer implements Runnable{
    private volatile long lastUpdTime = 0;
    private final Client client;
    private volatile boolean work;
    private final WorkingWindow workingWindow;

    public DataSynchronizer(Client client, WorkingWindowAdapter workingWindowAdapter) {
        this.client = client;
        this.workingWindow = workingWindowAdapter;
    }


    @Override
    public void run() {
        try{
            while (work) {
                ShowRequest showRequest = new ShowRequest();
                showRequest.setLastUpdTime(this.lastUpdTime);
                Response res = client.sendReqGetRes(showRequest);
                if (res.hasData()) {
                    workingWindow.setData(res.getData());
                    lastUpdTime = res.getLastUpdTime();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void setWork(boolean work) {
        this.work = work;
    }
}
