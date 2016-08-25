package lt.tieto.msi2016.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {


    public static String withQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                query.append('&');
            }
            try {
                query.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                if (!StringUtils.isEmpty(param.getValue())) {
                    query.append('=');
                    query.append(URLEncoder.encode(param.getValue(), "UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return query.toString();
    }


    public String getEncodedUrl() {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String password = "985ab15610d9e7a17fe60c212157949b";
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");

        Map<String, String> params = new LinkedHashMap<>();
        params.put("projectid", "86909");
        params.put("orderid", orderId);
        params.put("accepturl", "http://localhost:8080/acceptPayment");
        params.put("cancelurl", "http://localhost:8080/cancelPayment");
        params.put("callbackurl", "http://localhost:8080/callback");
        params.put("version", "1.6");
        params.put("test", "1");
        params.put("sign_password", password);
        params.put("amount", "1234");
        params.put("currency", "EUR");
        params.put("paytext", "For awesome drone service");
        //        params.put("lang", "EN");
        String query = withQuery(params);

        String encoded = Base64Utils.encodeToString(query.getBytes());
        String replaced = encoded.replace('/', '_').replace('+', '-');
        byte[] bytes = replaced.concat(password).getBytes();

        md5.update(bytes);
        byte[] signBytes = md5.digest();
        BigInteger hash = new BigInteger(1, signBytes);
        String sign = hash.toString(16);
        System.out.println(sign);

        String url = String.format("https://www.paysera.com/pay?data=%s&sign=%s", replaced, sign);

        System.out.println(url);
        return url;
    }
}
