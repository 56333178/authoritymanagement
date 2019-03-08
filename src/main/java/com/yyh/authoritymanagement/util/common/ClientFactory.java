package com.yyh.authoritymanagement.util.common;
import com.github.qcloudsms.SmsSingleSender;
import com.yyh.authoritymanagement.config.SMSConfig;

public class ClientFactory {
    private static SmsSingleSender ssender;
    public static SmsSingleSender getSsender() {
        if (ssender == null) {
            ssender = new SmsSingleSender(Integer.valueOf(SMSConfig.SMS_APP_ID), SMSConfig.SMS_APP_KEY);
        }
        return ssender;
    }


}
