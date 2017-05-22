package hackfest_bismaoperation.com.hackfest_bismaoperation.Model;

import java.io.Serializable;

/**
 * Created by Ryan Bagus Susilo on 5/22/2017.
 */
public class APITambahOrder implements Serializable{


    /**
     * status : true
     * status_code : 200
     * message : Data Berhasil Disimpan
     * respon : {"id_pengajar":"5","id_murid":"1","status":"Order","updated_at":"2017-05-22 03:38:48","created_at":"2017-05-22 03:38:48","id":4}
     */

    private boolean status;
    private int status_code;
    private String message;
    private ResponBean respon;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponBean getRespon() {
        return respon;
    }

    public void setRespon(ResponBean respon) {
        this.respon = respon;
    }

    public static class ResponBean {
        /**
         * id_pengajar : 5
         * id_murid : 1
         * status : Order
         * updated_at : 2017-05-22 03:38:48
         * created_at : 2017-05-22 03:38:48
         * id : 4
         */

        private String id_pengajar;
        private String id_murid;
        private String status;
        private String updated_at;
        private String created_at;
        private int id;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
