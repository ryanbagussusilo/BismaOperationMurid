package hackfest_bismaoperation.com.hackfest_bismaoperation.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Ryan Bagus Susilo on 5/22/2017.
 */
public class APIOrderListPengajar implements Serializable{


    /**
     * status : true
     * status_code : 200
     * message : Data Ditemukan
     * respon : [{"id":3,"id_pengajar":[{"id":1,"id_pengajar":"20170519173939","nama_depan":"Subagio","nama_belakang":"Bahagia","alamat":"Jl Babarsari 18","telepon":"087837863110","kelamin":"Pria","tempat_lahir":"Gombong Kebumen Jawa Tengah","tanggal_lahir":"1996-10-08","profil":"http://www.bisma.bluecrawler.com/public/image/pengajar/17475406_120300002722112740_141495971_o.jpg","status":"On","email":"ryan2009bs@gmail.com","username":"subagio","password":"$2y$10$VJYzSvpuyrgsULHai5yxN.ofvQyq9mjZHo9BrJem2bKfDeixcbxlG","longitude":null,"latitude":null,"harga":"100000","billing":"800000","riwayat_pendidikan":null,"mata_pelajaran":null,"created_at":"2017-05-19 17:39:39","updated_at":"2017-05-22 06:57:35"}],"id_murid":"1","status":"Order","tanggal":null,"jam":null,"total_biaya":"0","created_at":"2017-05-22 05:57:40","updated_at":"2017-05-22 05:57:40"},{"id":4,"id_pengajar":[{"id":5,"id_pengajar":"20170521211412","nama_depan":"Dharma","nama_belakang":"Bekti","alamat":"Jrakah","telepon":"085740126916","kelamin":"Pria","tempat_lahir":"Purworejo","tanggal_lahir":"1996-06-16","profil":"http://bismaoperation.id/uploads/Pengajar/Profil/Dharma_dharma.jpg","status":"On","email":"dharmabekti@gmail.com","username":"dharma","password":"$2y$10$gPF95Ur1NFHkG/wDoSuA4e2FMtRBwYxaoHZImitNOlWYOUnI7YTOa","longitude":null,"latitude":null,"harga":"50000","billing":null,"riwayat_pendidikan":"SMA","mata_pelajaran":"Matematika SD, Matematika SMP, Matematika SMA","created_at":"2017-05-21 14:14:12","updated_at":"2017-05-22 07:02:09"}],"id_murid":"1","status":"Order","tanggal":null,"jam":null,"total_biaya":"0","created_at":"2017-05-22 16:19:49","updated_at":"2017-05-22 16:19:49"}]
     */

    private boolean status;
    private int status_code;
    private String message;
    private List<ResponBean> respon;

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

    public List<ResponBean> getRespon() {
        return respon;
    }

    public void setRespon(List<ResponBean> respon) {
        this.respon = respon;
    }

    public static class ResponBean {
        /**
         * id : 3
         * id_pengajar : [{"id":1,"id_pengajar":"20170519173939","nama_depan":"Subagio","nama_belakang":"Bahagia","alamat":"Jl Babarsari 18","telepon":"087837863110","kelamin":"Pria","tempat_lahir":"Gombong Kebumen Jawa Tengah","tanggal_lahir":"1996-10-08","profil":"http://www.bisma.bluecrawler.com/public/image/pengajar/17475406_120300002722112740_141495971_o.jpg","status":"On","email":"ryan2009bs@gmail.com","username":"subagio","password":"$2y$10$VJYzSvpuyrgsULHai5yxN.ofvQyq9mjZHo9BrJem2bKfDeixcbxlG","longitude":null,"latitude":null,"harga":"100000","billing":"800000","riwayat_pendidikan":null,"mata_pelajaran":null,"created_at":"2017-05-19 17:39:39","updated_at":"2017-05-22 06:57:35"}]
         * id_murid : 1
         * status : Order
         * tanggal : null
         * jam : null
         * total_biaya : 0
         * created_at : 2017-05-22 05:57:40
         * updated_at : 2017-05-22 05:57:40
         */

        private int id;
        private String id_murid;
        private String status;
        private String tanggal;
        private Integer jam;
        private String total_biaya;
        private String created_at;
        private String updated_at;
        private List<IdPengajarBean> id_pengajar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public Integer getJam() {
            return jam;
        }

        public void setJam(Integer jam) {
            this.jam = jam;
        }

        public String getTotal_biaya() {
            return total_biaya;
        }

        public void setTotal_biaya(String total_biaya) {
            this.total_biaya = total_biaya;
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

        public List<IdPengajarBean> getId_pengajar() {
            return id_pengajar;
        }

        public void setId_pengajar(List<IdPengajarBean> id_pengajar) {
            this.id_pengajar = id_pengajar;
        }

        public static class IdPengajarBean {
            /**
             * id : 1
             * id_pengajar : 20170519173939
             * nama_depan : Subagio
             * nama_belakang : Bahagia
             * alamat : Jl Babarsari 18
             * telepon : 087837863110
             * kelamin : Pria
             * tempat_lahir : Gombong Kebumen Jawa Tengah
             * tanggal_lahir : 1996-10-08
             * profil : http://www.bisma.bluecrawler.com/public/image/pengajar/17475406_120300002722112740_141495971_o.jpg
             * status : On
             * email : ryan2009bs@gmail.com
             * username : subagio
             * password : $2y$10$VJYzSvpuyrgsULHai5yxN.ofvQyq9mjZHo9BrJem2bKfDeixcbxlG
             * longitude : null
             * latitude : null
             * harga : 100000
             * billing : 800000
             * riwayat_pendidikan : null
             * mata_pelajaran : null
             * created_at : 2017-05-19 17:39:39
             * updated_at : 2017-05-22 06:57:35
             */

            private int id;
            private String id_pengajar;
            private String nama_depan;
            private String nama_belakang;
            private String alamat;
            private String telepon;
            private String kelamin;
            private String tempat_lahir;
            private String tanggal_lahir;
            private String profil;
            private String status;
            private String email;
            private String username;
            private String password;
            private String longitude;
            private String latitude;
            private String harga;
            private String billing;
            private String riwayat_pendidikan;
            private String mata_pelajaran;
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

            public String getTempat_lahir() {
                return tempat_lahir;
            }

            public void setTempat_lahir(String tempat_lahir) {
                this.tempat_lahir = tempat_lahir;
            }

            public String getTanggal_lahir() {
                return tanggal_lahir;
            }

            public void setTanggal_lahir(String tanggal_lahir) {
                this.tanggal_lahir = tanggal_lahir;
            }

            public String getProfil() {
                return profil;
            }

            public void setProfil(String profil) {
                this.profil = profil;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
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

            public String getHarga() {
                return harga;
            }

            public void setHarga(String harga) {
                this.harga = harga;
            }

            public String getBilling() {
                return billing;
            }

            public void setBilling(String billing) {
                this.billing = billing;
            }

            public String getRiwayat_pendidikan() {
                return riwayat_pendidikan;
            }

            public void setRiwayat_pendidikan(String riwayat_pendidikan) {
                this.riwayat_pendidikan = riwayat_pendidikan;
            }

            public String getMata_pelajaran() {
                return mata_pelajaran;
            }

            public void setMata_pelajaran(String mata_pelajaran) {
                this.mata_pelajaran = mata_pelajaran;
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
}
