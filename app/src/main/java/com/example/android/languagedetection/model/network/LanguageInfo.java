package com.example.android.languagedetection.model.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 14:07 01.03.2018.
 */

/*
* Класс описывающий структуру JSON ответа
* */

public class LanguageInfo {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("usage")
    @Expose
    private String usage;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("iso-639-1")
    @Expose
    private String iso6391;
    @SerializedName("iso-639-2")
    @Expose
    private String iso6392;
    @SerializedName("iso-639-3")
    @Expose
    private String iso6393;
    @SerializedName("ethnologue")
    @Expose
    private String ethnologue;
    @SerializedName("native-speakers")
    @Expose
    private String nativeSpeakers;
    @SerializedName("wikipedia")
    @Expose
    private String wikipedia;

    private String statusInfo;

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso6392() {
        return iso6392;
    }

    public void setIso6392(String iso6392) {
        this.iso6392 = iso6392;
    }

    public String getIso6393() {
        return iso6393;
    }

    public void setIso6393(String iso6393) {
        this.iso6393 = iso6393;
    }

    public String getEthnologue() {
        return ethnologue;
    }

    public void setEthnologue(String ethnologue) {
        this.ethnologue = ethnologue;
    }

    public String getNativeSpeakers() {
        return nativeSpeakers;
    }

    public void setNativeSpeakers(String nativeSpeakers) {
        this.nativeSpeakers = nativeSpeakers;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

}
