package hackfest_bismaoperation.com.hackfest_bismaoperation.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class APIMuridData extends APIBaseResponse implements Serializable {
    private List<Murid> murid;

    private int id;


    public List<Murid> getMurid() {
        return murid;
    }



    public void setMurid(List<Murid> murid) {
        this.murid = murid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
