package cn.edu.cmu.vo;

import cn.edu.cmu.domain.Xm;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: jshand
 * @Date: 2018/11/4 19:47
 * @site https://github.com/jshand
 * @Project cmu
 * @Version 1.0
 */
public class XmExt extends Xm {

    private String sqStatus;
    private String sqjlId;

    public XmExt() {
    }

    public XmExt(String sqStatus, String sqjlId) {
        this.sqStatus = sqStatus;
        this.sqjlId = sqjlId;
    }

    public XmExt(String xmId, String xmlx, String xmzm, String glxmmcid, String xmmc, String xmbh, Date xmkssj, Date smjssj, BigDecimal xmjlts, String xmcc, String jfly, String zjje, String xmgk, String xmzzjh, String jlmbjgmc, BigDecimal fybz, String zysm, String sfxzrs, BigDecimal jhrs, String xmnjxz, String yyyq, String xmzyxz, String gsyxdm, String gsyxmc, String gsxsdm, String gsxsxm, String operatorCode, String status, String valid, Date createTime, String sqStatus, String sqjlId) {
        super(xmId, xmlx, xmzm, glxmmcid, xmmc, xmbh, xmkssj, smjssj, xmjlts, xmcc, jfly, zjje, xmgk, xmzzjh, jlmbjgmc, fybz, zysm, sfxzrs, jhrs, xmnjxz, yyyq, xmzyxz, gsyxdm, gsyxmc, gsxsdm, gsxsxm, operatorCode, status, valid, createTime);
        this.sqStatus = sqStatus;
        this.sqjlId = sqjlId;
    }

    public String getSqStatus() {
        return sqStatus;
    }

    public void setSqStatus(String sqStatus) {
        this.sqStatus = sqStatus;
    }

    public String getSqjlId() {
        return sqjlId;
    }

    public void setSqjlId(String sqjlId) {
        this.sqjlId = sqjlId;
    }

    @Override
    public String toString() {
        return "XmExt{" +
                "sqStatus='" + sqStatus + '\'' +
                ", sqjlId='" + sqjlId + '\'' +
                '}';
    }
}
