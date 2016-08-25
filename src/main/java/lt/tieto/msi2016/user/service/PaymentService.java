package lt.tieto.msi2016.user.service;

import lt.tieto.msi2016.transaction.repository.PaymentsRepository;
import lt.tieto.msi2016.transaction.repository.model.PaymentsDb;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Value("${hostname:localhost:8080}")
    private  String hostname;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private SecurityService securityService;


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


    @Transactional
    public String getEncodedUrl(Long amountCents) {

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
        params.put("accepturl", String.format("http://%s/acceptPayment", hostname));
        params.put("cancelurl", String.format("http://%s/cancelPayment", hostname));
        params.put("callbackurl", String.format("http://%s/callback", hostname));
        params.put("version", "1.6");
        params.put("test", "1");
        params.put("sign_password", password);
        params.put("amount", amountCents.toString());
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

        PaymentsDb paymentsDb = new PaymentsDb();
        paymentsDb.setUserId(securityService.getCurrentUser().getId());
        paymentsDb.setAmount(new BigDecimal(amountCents / 100));
        paymentsDb.setStatus(PaymentsDb.Status.Pending);
        paymentsDb.setPayseraOrderId(orderId);
        paymentsRepository.create(paymentsDb);

        return url;
    }
}
