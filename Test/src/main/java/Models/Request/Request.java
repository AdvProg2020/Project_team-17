package Models.Request;

import java.util.ArrayList;

public class Request {
    protected static ArrayList<Request> allRequests= new ArrayList<>();
    protected String requestId;

    public Request(String requestId) {
        this.requestId = requestId;
        allRequests.add(this);
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getRequestId() {
        return requestId;
    }
    //fek konam in method ro bayad abstract konim(accept request)

    public void acceptRequest() {
    }

    public static Request getRequestById(String requestId) {
        for (Request request : allRequests) {
            if (request.getRequestId().equals(requestId)) {
                return request;
            }
        }
        return null;
    }

    public static void removeRequest(Request request) {
        allRequests.remove(request);
    }
}


