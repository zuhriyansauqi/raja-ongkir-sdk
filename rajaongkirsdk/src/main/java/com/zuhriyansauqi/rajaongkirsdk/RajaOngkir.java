package com.zuhriyansauqi.rajaongkirsdk;

import android.content.Context;

/**
 * Created by zuhriyansauqi on 3/11/17.
 */

public class RajaOngkir implements RajaOngkirBase {

    // MARK: - Private constructor
    private RajaOngkir() { }

    private RajaOngkir(Init init) {
        this.baseUrl = init.baseUrl;
        this.apiKey = init.apiKey;
        this.context = init.context;
    }

    // MARK: - Fields
    private Context context;
    private String baseUrl;
    private String apiKey;

    // MARK: - Getters
    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    // MARK: - Builder class
    public static class Init {

        private Context context;
        private String baseUrl;
        private String apiKey;

        public Init(Context context) {
            this.context = context;
        }

        public Init withAccountType(AccountType type) {
            switch (type) {
                case STARTER:
                    this.baseUrl = TYPE_STARTER;
                    break;
                case BASIC:
                    this.baseUrl = TYPE_BASIC;
                    break;
                case PRO:
                    this.baseUrl = TYPE_PRO;
                    break;
            }

            return this;
        }

        public Init andApiKey(String key) {
            this.apiKey = key;
            return this;
        }

        public RajaOngkir create() {
            return new RajaOngkir(this);
        }
    }
}
