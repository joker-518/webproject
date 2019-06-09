package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092700605105";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCh29BW0XrFlUGEgC0jJoN3nKPhlrhfAjsLjtali+EOA1pYhlyvvyvn+zFr9mIpWUsEBEO8ByJzl1h8MMxejnW1vDgn7TVDqxnjzvxXdFEbuYr7NP/eOUG24uyYHvopmPSxQVRcbYiNwMDCja1FGDLT0ToE2IPtzimb4emNJUis9pyWBj98dIPSlTPbSlFv/4ViKkszfvDU9hp1nwdy7MM+OgOGGCE/iFuO8lLOxxnb651PFGLtO7Q+dpVLxLNRHv6/TVwiV6Q2Aty5uC7mTd8BpTXzfBsE6wZ/QUacSE3ARh+1Jey/9rhWGbnTs8lQNh2WehDoq9uHtDeSCNFA9KTnAgMBAAECggEAXszxZLhC8VDvU90DEvBu1fLv5aQyjqsuzCE4RTP9eAistObViLYu0CvHEZX4foFnN44L4ihHnG5LjEnEmalU9kGsnm4H4X9opY5JLCvbiaKN7X5oUs80VxroOl0CZrTItL0v3QbmkSgRfqIelzaKJpgIJGhwNSuoIT4bXek1fN/wuUPB9OM/4yHT12jT/i/2TgwqmA0tTheUf9TI25LAUG67rRTHCGptDNkwXS7jzAvUrQQhSw2ciMCfrYZUTvI8OXwTMopJq2VVvpW3/WRpLiwAiz//lMP0/m9bC6zPsmcUNXkqWN8sc9gyr+Hbrua5C4eMdhlCUlRVacBjDlNC+QKBgQDhfuIST+CedmNbRwuHiUFvMXu1nabIOpNZrThHR3i2rEDTTZ+tGWIqN4zQwMxO+Kr+crI80mjhcbHNKHX72LGirAjW90Qm7pzTBDqHj3nHUsgDcSRUNR6cWoE8RTqz2/prp10EPRKRFYNnGvw5lokQ2FY+j4zwSPEWERYtEp1aXQKBgQC3wR/fkPE1DHjeBQZdLA57QZlI+VTYTaE/MjUdBFc0i2rMdn3huU9usmWqqwGHVBKC1LjBAr+DEPlprhab9TSknmnRHtkeVlZ1I1t5+xA0nEhBHPkWYLEGiQ29qclRk0/vKqWb5q/N1lWuVji6ZZtqpPdUvc1N4u28Y+r1qVOwEwKBgDFlLfh87uNHY7RW97uU4XSODl9WTXaG/kginbVffp3lVb3zvwqYCNzelkYUNa0G484hKdIQ9VCNKzmZp16ntSHBNZ6BQ7iThF7lCj0vvKlhBNdcLW0UxcPSP4UIVTeY9F4op0OKbUrCv6oSSzYCN7ws55eEsobgM4915rmw3i8xAoGAHIX46Z45Iu/tryD+jOXy1YUoQyCY3/6k4gdsITX0R27AxD48ahZ20wsUJvrmgNg3C2Hzz9oaWkD/Rj1Euy7D3i3M5S4xq7jhMMutPnEPYetEd/8EV0npEMejf2ZknqoMRWbMUDft4Di+EhLGOYDeMB6tWdUgkk+Acbbc2QdQu0sCgYAQ5Z6kNTR2IjyCrZamWHj0OF1UFxbulWUbaPktH9IEpN17y/Hp1gX8RS7rhMHH4FK+VQLK/8BWPaoQU6eXePVagLPDPUj5NDBlV4Uh4QHJaSAbiQxMkgtMJ089l5Ks5Puo6/IKYbsAITXeZLjVYnXrRK+uh+bB0qboApfE78/A7g==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9OJUqInToUX/jlvh5LZCuRIsQFUfaZ8xm4aNF7xCiEi/FC8dGD+GhxfTvz5pAA57MPgrK+dvHdfF09SWhiByNN+8qEXKLrO7vm+N1zJyRwMFN4WoN8Y6xFvZc/21zDP9dCXeX7XL7UbYs6avL0wz0s4bNCbaco4u8Dh1H11gEMBk1exWlsISWD8gXOwOqlEeXz0snG+rPjuKyyUfsE8BIvI2SRqgd5NYaeSsqcxgzVZl0o7Bg1MRsMvM4WonckrL/uNAGAzOP5pn5Y+GpQ4jsrj5LiBNR8/xDIAUhQDzBBTusG6v34lEZwcDBC4V+iygLaC77GsZ+4oPm7skNxcrhwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/store_v5/OrderServlet?method=callBack";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 日志路径
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

