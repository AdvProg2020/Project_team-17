package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Enums.RequestTypeEnum;

import java.util.ArrayList;

public abstract class Request {
    protected static ArrayList<Request> allRequests = new ArrayList<Request>();
    protected String id;
    protected Manager manager;
    protected Seller seller;
    protected RequestTypeEnum type;

    public Request(String id, Seller seller, Manager manager) {
        this.id = id;
        this.seller = seller;
        this.manager = manager;
        allRequests.add(this);
    }

    public abstract void accept();


    public Manager getManager() {
        return manager;
    }


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