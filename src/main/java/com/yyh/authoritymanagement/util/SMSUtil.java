package com.yyh.authoritymanagement.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.httpclient.HTTPException;
import com.yyh.authoritymanagement.config.SMSConfig;
import com.yyh.authoritymanagement.util.common.ClientFactory;

import java.io.IOException;
import java.util.ArrayList;

public class SMSUtil {
    private static SmsSingleSender ssender= ClientFactory.getSsender();
    public static String sendMessage(String phone,String yzm){
        ArrayList<String> params=new ArrayList<>();
        params.add("");
        params.add("");
        params.add(yzm);
        params.add("联盟");
        try {
            return ssender.sendWithParam("86",phone, SMSConfig.SMS_TEMPLATEID1,  params, SMSConfig.SMSSIGN,"","").toString();
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
