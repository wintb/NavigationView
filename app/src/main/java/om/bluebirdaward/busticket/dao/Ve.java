package om.bluebirdaward.busticket.dao;

/**
 * Created by VuVanThang on 5/24/2016.
 */
public class Ve {
    private String MaVe;
    private String MaChuyen;
    private String MaTai;
    private String SoGhe;
    private String SDTKhach;

    public void setMaChuyen(String maChuyen) {
        MaChuyen = maChuyen;
    }

    public void setMaTai(String maTai) {
        MaTai = maTai;
    }

    public void setMaVe(String maVe) {
        MaVe = maVe;
    }

    public void setSDTKhach(String SDTKhach) {
        this.SDTKhach = SDTKhach;
    }

    public void setSoGhe(String soGhe) {
        SoGhe = soGhe;
    }

    public String getMaChuyen() {
        return MaChuyen;

    }

    public String getMaTai() {
        return MaTai;
    }

    public String getMaVe() {
        return MaVe;
    }

    public String getSDTKhach() {
        return SDTKhach;
    }

    public String getSoGhe() {
        return SoGhe;
    }
}
