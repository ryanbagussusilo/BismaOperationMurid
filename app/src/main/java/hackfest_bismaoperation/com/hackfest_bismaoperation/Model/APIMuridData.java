package hackfest_bismaoperation.com.hackfest_bismaoperation.Model;

import java.io.Serializable;

/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class APIMuridData  implements Serializable {

    /**
     * status : true
     * status_code : 200
     * message : Login Berhasil
     * respon : {"id":1,"nama_depan":"babar","nama_belakang":"sar","tempat_lahir":"rumah sakit","alamat":"babarsari yogya","telepon":"1253456","kelamin":"cowok sejati","tanggal_lahir":"0000-00-00","email":"dfgh@dfgh.com","longitude":"80854","latitude":"85543","profil":"http://www.bisma.bluecrawler.com/public/image/murid/deletepost.png"}
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
         * id : 1
         * nama_depan : babar
         * nama_belakang : sar
         * tempat_lahir : rumah sakit
         * alamat : babarsari yogya
         * telepon : 1253456
         * kelamin : cowok sejati
         * tanggal_lahir : 0000-00-00
         * email : dfgh@dfgh.com
         * longitude : 80854
         * latitude : 85543
         * profil : http://www.bisma.bluecrawler.com/public/image/murid/deletepost.png
         */

        private int id;
        private String nama_depan;
        private String nama_belakang;
        private String tempat_lahir;
        private String alamat;
        private String telepon;
        private String kelamin;
        private String tanggal_lahir;
        private String email;
        private String longitude;
        private String latitude;
        private String profil;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNama_depan() {
            return nama_depan;
        }

        public void setNama_depan(String nama_depan) {
            this.nama_depan = nama_depan;
        }

        public String getNama_belakang() {
            return nama_belakang;
        }

        public void setNama_belakang(String nama_belakang) {
            this.nama_belakang = nama_belakang;
        }

        public String getTempat_lahir() {
            return tempat_lahir;
        }

        public void setTempat_lahir(String tempat_lahir) {
            this.tempat_lahir = tempat_lahir;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getTelepon() {
            return telepon;
        }

        public void setTelepon(String telepon) {
            this.telepon = telepon;
        }

        public String getKelamin() {
            return kelamin;
        }

        public void setKelamin(String kelamin) {
            this.kelamin = kelamin;
        }

        public String getTanggal_lahir() {
            return tanggal_lahir;
        }

        public void setTanggal_lahir(String tanggal_lahir) {
            this.tanggal_lahir = tanggal_lahir;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getProfil() {
            return profil;
        }

        public void setProfil(String profil) {
            this.profil = profil;
        }
    }
}
