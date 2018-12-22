package com.goli.heroben.pay.wechat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class MyConfig implements WXPayConfig{

    private byte[] certData;

    public MyConfig() throws Exception {

    }

    public String getAppID() {
        return "wx7778f706918e714e";
    }

    public String getMchID() {
        return "1234903602";
    }

    public String getKey() {
        return "3tQx7WszXXgMzSEyLFO2JP0YsjFz5MHz";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}