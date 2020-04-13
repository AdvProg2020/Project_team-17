package Models;

import java.util.ArrayList;
import java.util.Date;

public class Log {
    private String logId;
    private Date date;
    private static ArrayList<Log> allLogs = new ArrayList<Log>();

    public Log(String logId, Date date) {
        this.logId = logId;
        this.date = date;
        allLogs.add(this);
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
