package com.sai628.androidutils.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.google.common.truth.Truth.assertThat;
import static com.sai628.androidutils.utils.EncodeUtils.base64Decode;
import static com.sai628.androidutils.utils.EncodeUtils.base64Encode;
import static com.sai628.androidutils.utils.EncodeUtils.base64Encode2String;
import static com.sai628.androidutils.utils.EncodeUtils.htmlEncode;
import static com.sai628.androidutils.utils.EncodeUtils.urlDecode;
import static com.sai628.androidutils.utils.EncodeUtils.urlEncode;


/**
 * @author Sai
 * @ClassName: EncodeUtilsTest
 * @Description: EncodeUtil 单元测试
 * @date 30/03/2017 16:01
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class EncodeUtilsTest
{
    String encodeInput = "https://www.google.com";
    String urlEncodeString = "https%3A%2F%2Fwww.google.com";

    String base64Input = "sai100628@gmail.com";
    String base64EncodeString = "c2FpMTAwNjI4QGdtYWlsLmNvbQ==";

    String html = "<html>" +
            "<head>" +
            "<title>我的第一个 HTML 页面</title>" +
            "</head>" +
            "<body>" +
            "<p>body 元素的内容会显示在浏览器中。</p>" +
            "<p>title 元素的内容会显示在浏览器的标题栏中。</p>" +
            "</body>" +
            "</html>";
    String encodeHtml = "&lt;html&gt;&lt;head&gt;&lt;title&gt;&#25105;&#30340;&#31532;&#19968;&#20010; HTML &#39029;&#38754;&lt;/title&gt;&lt;/head&gt;&lt;body&gt;&lt;p&gt;body &#20803;&#32032;&#30340;&#20869;&#23481;&#20250;&#26174;&#31034;&#22312;&#27983;&#35272;&#22120;&#20013;&#12290;&lt;/p&gt;&lt;p&gt;title &#20803;&#32032;&#30340;&#20869;&#23481;&#20250;&#26174;&#31034;&#22312;&#27983;&#35272;&#22120;&#30340;&#26631;&#39064;&#26639;&#20013;&#12290;&lt;/p&gt;&lt;/body&gt;&lt;/html&gt;";


    @Test
    public void testUrlEncode() throws Exception
    {
        assertThat(urlEncode(encodeInput)).isEqualTo(urlEncodeString);
        assertThat(urlEncode(encodeInput, "UTF-8")).isEqualTo(urlEncodeString);
        assertThat(urlEncode(encodeInput, "UNKNOWN")).isNotEqualTo(urlEncodeString);
    }


    @Test
    public void testUrlDecode() throws Exception
    {
        assertThat(urlDecode(urlEncodeString)).isEqualTo(encodeInput);
        assertThat(urlDecode(urlEncodeString, "UTF-8")).isEqualTo(encodeInput);
        assertThat(urlDecode(urlEncodeString, "")).isNotEqualTo(encodeInput);
    }


    @Test
    public void testBase64Encode() throws Exception
    {
        assertThat(base64Encode(base64Input)).isEqualTo(base64EncodeString.getBytes());
        assertThat(base64Encode(base64Input.getBytes())).isEqualTo(base64EncodeString.getBytes());
        assertThat(base64Encode2String(base64Input.getBytes())).isEqualTo(base64EncodeString);
        assertThat(base64Decode(base64EncodeString)).isEqualTo(base64Input.getBytes());
        assertThat(base64Decode(base64EncodeString.getBytes())).isEqualTo(base64Input.getBytes());
    }


    @Test
    public void testHtmlEncode() throws Exception
    {
        assertThat(htmlEncode(html)).isEqualTo(encodeHtml);
    }
}
