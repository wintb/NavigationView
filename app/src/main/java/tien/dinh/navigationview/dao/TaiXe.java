package tien.dinh.navigationview.dao;

/**
 * Created by VuVanThang on 5/15/2016.
 */
public class TaiXe {

    private String MaTai;
    private String TaiXe;
    private String PhuXe;
    private String BienSo;
    private String SDTTai;

    public String getBienSo() {
        return BienSo;
    }

    public void setBienSo(String bienSo) {
        BienSo = bienSo;
    }

    public String getMaTai() {
        return MaTai;
    }

    public void setMaTai(String maTai) {
        MaTai = maTai;
    }

    public String getPhuXe() {
        return PhuXe;
    }

    public void setPhuXe(String phuXe) {
        PhuXe = phuXe;
    }

    public String getSDTTai() {
        return SDTTai;
    }

    public void setSDTTai(String SDTTai) {
        this.SDTTai = SDTTai;
    }

    public String getTaiXe() {
        return TaiXe;
    }

    public void setTaiXe(String taiXe) {
        TaiXe = taiXe;
    }
}
