package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author wuchang006
 * date 2022-09-16:14
 * note: 测试常驻线程
 */

@Component
public class DataProcessCron implements Runnable {



    @PostConstruct
    public void init(){
        //启动线程实例
        new Thread(this).start();
    }

    private static boolean isWorkDay = false;
    private static boolean hasSend = false;

    public static int COUNT_DAY = 81;


    @Override
    public void run() {
        synchronized(this){
            if("0".equals(judgeDate())) {
                isWorkDay = true;
            }
            while (true){
                executeTask();
            }
        }
    }

    public void executeTask(){
        try {
            Thread.sleep(20000);
            if (sendTime() && !hasSend) {
                hasSend = true;
                COUNT_DAY = COUNT_DAY - 1;
                String message = String.format("各位老板，倒计时：%s天", COUNT_DAY);
                MessageDto.Text text = new MessageDto.Text();
                text.setContent(message);
                MessageDto messageDto = MessageDto.builder().msgtype("text").text(text).build();
                postHttp(messageDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private boolean sendTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour == 1) {
            isWorkDay = "0".equals(judgeDate());
            hasSend = false;
        }
        if (isWorkDay) {
            int minute = calendar.get(Calendar.MINUTE);
            if(hour == 12 && minute == 10) {
                return true;
            }
        } else {
            Thread.sleep(3000000);
        }
        return false;
    }

    @SneakyThrows
    private void postHttp(MessageDto messageDto) {
        String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=d0a20723-e5c0-4e6a-9fb6-184217261efc";
        // 2.请求参数JSON格式
        String json = JSONObject.toJSONString(messageDto);
//        String json = JSON.toJSONString(map);
        // 3.创建连接与设置连接参数
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(postUrl);
        StringEntity entity = new StringEntity(json, "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        // 4.发起请求与接收返回值
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("调用服务端异常.");
        }
        HttpEntity res = response.getEntity();
        String resultData = EntityUtils.toString(res);
        System.out.println("从服务端返回结果: " + resultData);
        // 5.关闭连接

        httpClient.close();
    }

    public static String request(String httpArg) {
        String httpUrl = "http://tool.bitefu.net/jiari/";
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?d=" +httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String judgeDate() {
        //获取当前时间，格式为yyyyMMdd
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");

        String httpArg = f.format(new Date());
        String jsonResult = request(httpArg);
        // 0上班  1周末 2节假日
        if ("0".equals(jsonResult)) {
            jsonResult = "0";
        }
        if ("1".equals(jsonResult)) {
            jsonResult = "1";
        }
        if ("2".equals(jsonResult)) {
            jsonResult = "2";
        }
        return jsonResult;
    }
}

