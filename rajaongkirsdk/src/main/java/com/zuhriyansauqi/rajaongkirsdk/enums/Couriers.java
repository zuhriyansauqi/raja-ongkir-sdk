package com.zuhriyansauqi.rajaongkirsdk.enums;

/**
 * Created by zuhriyansauqi on 3/13/17.
 */

public enum Couriers {

    JALUR_NUGRAHA_EKAKURIR ("jne"),
    POS_INDONESIA ("pos"),
    CITRA_VAN_TITIPAN_KILAT ("tiki"),
    RPX_HOLDING ("rpx"),
    EKA_SARI_LORENA ("esl"),
    PRIORITY_CARGO_AND_PACKAGE ("pcp"),
    PANDU_LOGISTICS ("pandu"),
    WAHANA_PRESTASI_LOGISTIK ("wahana"),
    SICEPAT_EXPRESS ("sicepat"),
    J_AND_T_EXPRESS ("jnt"),
    PAHALA_KENCANA_EXPRESS ("pahala"),
    CAHAYA_LOGISTIK ("cahaya"),
    SAP_EXPRESS ("sap"),
    JET_EXPRESS ("jet"),
    INDAH_LOGISTIC ("indah"),
    TWENTY_ONE_EXPRESS ("dse"),
    SOLUSI_EXPRESS ("slis"),
    FIRST_LOGISTICS ("first");

    private String value;

    private Couriers(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
