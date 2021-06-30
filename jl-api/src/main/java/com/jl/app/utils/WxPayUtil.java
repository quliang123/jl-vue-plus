package com.jl.app.utils;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.jl.app.config.pay.WxPayProperties;
import com.jl.system.domain.bo.AlumnaAddBo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: weixin-java-pay
 * @description:  微信支付xml解析工具
 * @author: liang qu
 * @create: 2021-06-10 16:02
 **/

public class WxPayUtil {

  /**
   * 将xml 转 map
   * @param xmlString
   * @return
   */
  public static Map<String, String> parse(String xmlString) {
    Map<String, String> xmlMap = new HashMap<>();
    Document doc = null;
    try {
      doc = DocumentHelper.parseText(xmlString);
      Element rootElement = doc.getRootElement();
      List<Element> elements = rootElement.elements();
      xmlMap= elements.stream().collect(Collectors.toMap(Element::getName, Element::getText));
    }catch (DocumentException e){
      e.printStackTrace();
    }
    return xmlMap;
  }

  public static void main(String[] args) {
    String xml = "<xml>\n" +
      "  <appid>111</appid>\n" +
      "  <mch_id>1111</mch_id>\n" +
      "  <sub_appid>3333</sub_appid>\n" +
      "  <sub_mch_id>222</sub_mch_id>\n" +
      "  <nonce_str>1623311142714</nonce_str>\n" +
      "  <sign>77AFA050C54F991D22CE236218EABFFA</sign>\n" +
      "  <body>222</body>\n" +
      "  <out_trade_no>111</out_trade_no>\n" +
      "  <total_fee>100</total_fee>\n" +
      "  <spbill_create_ip>123.12.12.123</spbill_create_ip>\n" +
      "  <notify_url>www.baidu.com</notify_url>\n" +
      "  <trade_type>JSAPI</trade_type>\n" +
      "</xml>";
    Map<String, String> parse = parse(xml);
    System.out.println("parse = " + parse.toString());
  }


}
