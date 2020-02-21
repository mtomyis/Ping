package com.tomtomy.myapplication.model;

public class PingModel {
    String ip, gedung, status, jenisrouter, ssid, passwordssid, mac, ipremote, useradmin, passworduseradmin;

    public PingModel(String ip, String gedung, String status, String jenisrouter, String ssid, String passwordssid, String mac, String ipremote, String useradmin, String passworduseradmin) {
        this.ip = ip;
        this.gedung = gedung;
        this.status = status;
        this.jenisrouter = jenisrouter;
        this.ssid = ssid;
        this.passwordssid = passwordssid;
        this.mac = mac;
        this.ipremote = ipremote;
        this.useradmin = useradmin;
        this.passworduseradmin = passworduseradmin;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGedung() {
        return gedung;
    }

    public void setGedung(String gedung) {
        this.gedung = gedung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJenisrouter() {
        return jenisrouter;
    }

    public void setJenisrouter(String jenisrouter) {
        this.jenisrouter = jenisrouter;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPasswordssid() {
        return passwordssid;
    }

    public void setPasswordssid(String passwordssid) {
        this.passwordssid = passwordssid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIpremote() {
        return ipremote;
    }

    public void setIpremote(String ipremote) {
        this.ipremote = ipremote;
    }

    public String getUseradmin() {
        return useradmin;
    }

    public void setUseradmin(String useradmin) {
        this.useradmin = useradmin;
    }

    public String getPassworduseradmin() {
        return passworduseradmin;
    }

    public void setPassworduseradmin(String passworduseradmin) {
        this.passworduseradmin = passworduseradmin;
    }
}
