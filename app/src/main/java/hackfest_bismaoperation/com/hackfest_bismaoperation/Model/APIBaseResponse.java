package hackfest_bismaoperation.com.hackfest_bismaoperation.Model;

import java.io.Serializable;

/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class APIBaseResponse implements Serializable {
    private int status;
    private String message;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
