package hackfest_bismaoperation.com.hackfest_bismaoperation.Model;

import java.io.Serializable;

/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class APIBaseResponse implements Serializable {


    /**
     * status : true
     * status_code : 200
     * message : Register Berhasil
     * respon : {"nama_depan":"moprh","nama_belakang":"morph","tempat_lahir":"semarang","telepon":"0889362673","kelamin":"Pria","tanggal_lahir":"1996-10-8","email":"ryan2009@yahoo.co.id","alamat":"jln ambar123","longitude":null,"latitude":null,"username":"morph","password":"$2y$10$TkhWguzduBZ7G8a4Por8HeOBKliE71tFZn3rN6VNZdkwLov3bX3i6","profil":"http://bismaapi.bismaoperation.id/public/image/murid/Foto Ryan.jpg","updated_at":"2017-06-01 09:46:38","created_at":"2017-06-01 09:46:38","id":5}
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
         * nama_depan : moprh
         * nama_belakang : morph
         * tempat_lahir : semarang
         * telepon : 0889362673
         * kelamin : Pria
         * tanggal_lahir : 1996-10-8
         * email : ryan2009@yahoo.co.id
         * alamat : jln ambar123
         * longitude : null
         * latitude : null
         * username : morph
         * password : $2y$10$TkhWguzduBZ7G8a4Por8HeOBKliE71tFZn3rN6VNZdkwLov3bX3i6
         * profil : http://bismaapi.bismaoperation.id/public/image/murid/Foto Ryan.jpg
         * updated_at : 2017-06-01 09:46:38
         * created_at : 2017-06-01 09:46:38
         * id : 5
         */

        private String nama_depan;
        private String nama_belakang;
        private String tempat_lahir;
        private String telepon;
        private String kelamin;
        private String tanggal_lahir;
        private String email;
        private String alamat;
        private Object longitude;
        private Object latitude;
        private String username;
        private String password;
        private String profil;
        private String updated_at;
        private String created_at;
        private int id;

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

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProfil() {
            return profil;
        }

        public void setProfil(String profil) {
            this.profil = profil;
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
