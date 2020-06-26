package Models.Request;

import Controller.WriteIntoFile;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Enums.RequestTypeEnum;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Request {
    protected static ArrayList<Request> allRequests = new ArrayList<Request>();
    protected String id;
    protected Seller seller;
    protected RequestTypeEnum type;

    public Request(String id, Seller seller) throws IOException {
        this.id = id;
        this.seller = seller;
        allRequests.add(this);
        WriteIntoFile.writeRequestsIntoFile();
    }

    public abstract void accept();


    //public Manager getManager() {
   //     return manager;
    //}


    public static Request getRequestById(String id) {
        for (Request request : allRequests) {
            if (request.getId().equals(id)) {
                return request;
            }
        }
        return null;
    }

    public static boolean isThereRequestById(String id) {
        for (Request request : allRequests) {
            if (request.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void deleteRequest(Request request) {
        allRequests.remove(request);
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getId() {
        return id;
    }

    @Override
    public abstract String toString();
}