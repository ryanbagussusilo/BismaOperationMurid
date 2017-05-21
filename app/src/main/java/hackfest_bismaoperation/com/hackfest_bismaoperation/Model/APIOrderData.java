package hackfest_bismaoperation.com.hackfest_bismaoperation.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ryan Bagus Susilo on 5/13/2017.
 */
public class APIOrderData extends APIBaseResponse implements Serializable {


    /**
     * status : true
     * status_code : 200
     * respon : [{"id":1,"id_pengajar":"1","id_murid":"1","status":"Order","tanggal":null,"jam":null,"created_at":"2017-05-21 18:55:06","updated_at":"2017-05-21 18:55:06"}]
     */

    @SerializedName("status")
    private boolean statusX;
    private int status_code;
    private List<ResponBean> respon;

    public boolean isStatusX() {
        return statusX;
    }

    public void setStatusX(boolean statusX) {
        this.statusX = statusX;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public List<ResponBean> getRespon() {
        return respon;
    }

    public void setRespon(List<ResponBean> respon) {
        this.respon = respon;
    }

    public static class ResponBean {
        /**
         * id : 1
         * id_pengajar : 1
         * id_murid : 1
         * status : Order
         * tanggal : null
         * jam : null
         * created_at : 2017-05-21 18:55:06
         * updated_at : 2017-05-21 18:55:06
         */

        private int id;
        private String id_pengajar;
        private String id_murid;
        @SerializedName("status")
        private String statusX;
        private Object tanggal;
        private Object jam;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getId_pengajar() {
            return id_pengajar;
        }

        public void setId_pengajar(String id_pengajar) {
            this.id_pengajar = id_pengajar;
        }

        public String getId_murid() {
            return id_murid;
        }

        public void setId_murid(String id_murid) {
            this.id_murid = id_murid;
        }

        public String getStatusX() {
            return statusX;
        }

        public void setStatusX(String statusX) {
            this.statusX = statusX;
        }

        public Object getTanggal() {
            return tanggal;
        }

        public void setTanggal(Object tanggal) {
            this.tanggal = tanggal;
        }

        public Object getJam() {
            return jam;
        }

        public void setJam(Object jam) {
            this.jam = jam;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
