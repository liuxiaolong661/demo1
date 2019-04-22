package com.springboot.demo1;

import com.alibaba.fastjson.JSON;
import com.springboot.demo1.util.HttpClientUtil;
import com.thoughtworks.xstream.XStream;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HttpClientTest {

    @Test
    public void testSendPostDataByMap() throws ClientProtocolException, IOException, Exception {
        String url = "https://www.qianqianxs.com/63/63419";
        String body = HttpClientUtil.getInstance().sendGetData(url, "GBK");

        String startStr = "<div class=\"panel-body\">";
        String splitStr = body.substring(body.indexOf(startStr) + startStr.length());
        String result = splitStr.substring(0, splitStr.indexOf("</div>")).replaceAll("<li>","").replaceAll("</li>", "").replaceAll(" class=\"list-group list-charts\"", "");
//        System.out.println(result);

//        String xx = "<ul><a href=\"1\">11</a><a href=\"2\">22</a><a href=\"3\">33</a></ul>";
        XStream xstream = new XStream();

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{CatalogList.class, CatalogList.class});
        xstream.processAnnotations(CatalogList.class);
        xstream.autodetectAnnotations(true);
        CatalogList o = (CatalogList) xstream.fromXML(result);

        System.out.println(JSON.toJSON(o.getCatalogList()));
        for (int i = 54; i < o.getCatalogList().size(); i++) {
            String href = o.getCatalogList().get(i).getUrl();
            String title = o.getCatalogList().get(i).getValue();
            String book_body = HttpClientUtil.getInstance().sendGetData("https://www.qianqianxs.com"+href, "GBK");
//            System.out.println("https://www.qianqianxs.com"+href);
//            System.out.println(book_body);
            String start_str = "<div class=\"panel-body content-body content-ext\">";
            String split_str = book_body.substring(book_body.indexOf(start_str) + start_str.length());
            String result_str = split_str.substring(0, split_str.indexOf("</div>"));
//            System.out.println(result_str.replaceAll("<br />", ""));

            writeDataToFile("\r\n\r\n" + title + "\r\n" + result_str.replaceAll("<br />", ""));
            System.out.println("https://www.qianqianxs.com"+href + "[" + title + "]----->ok");
            Thread.sleep(10000);
        }

    }

    public void writeDataToFile(String str) throws Exception{
        //文件目录
        File file = new File("f://folder//b.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        byte[] strToBytes = str.getBytes();
        FileOutputStream  o=new FileOutputStream(file,true);
        o.write(strToBytes);
        o.flush();
        o.close();
    }
}
