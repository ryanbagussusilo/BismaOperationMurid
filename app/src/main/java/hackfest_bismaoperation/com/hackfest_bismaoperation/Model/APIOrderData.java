package hackfest_bismaoperation.com.hackfest_bismaoperation.Model;

import java.io.Serializable;

/**
 * Created by Ryan Bagus Susilo on 5/13/2017.
 */
public class APIOrderData extends APIBaseResponse implements Serializable {

    private int id_pengajar;
    private int id_murid;

    public int getId_pengajar() {
        return id_pengajar;
    }

    public void setId_pengajar(int id_pengajar) {
        this.id_pengajar = id_pengajar;
    }

    public int getId_murid() {
        return id_murid;
    }

    public void setId_murid(int id_murid) {
        this.id_murid = id_murid;
    }


}
