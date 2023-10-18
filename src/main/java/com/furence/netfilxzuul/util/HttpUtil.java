package com.furence.netfilxzuul.util;

import com.netflix.zuul.context.RequestContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtil {

    public static String getResponseBody(RequestContext ctx){

        InputStream responseDataStream = ctx.getResponseDataStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseDataStream));
        StringBuilder responseBody = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
        } catch (IOException e) {
            // 예외 처리
        } finally {
            // 응답 스트림을 다시 설정해야 합니다.
            ctx.setResponseDataStream(responseDataStream);
        }

        return responseBody.toString();
    }


}
