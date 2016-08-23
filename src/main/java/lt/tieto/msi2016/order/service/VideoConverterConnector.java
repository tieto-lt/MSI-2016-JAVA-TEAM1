package lt.tieto.msi2016.order.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VideoConverterConnector {

    @Autowired
    private RestTemplate restTemplate;

    public byte[] convertVideo(byte[] video) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "video/h264");
        HttpEntity<byte[]> entity = new HttpEntity<>(video, headers);

        ResponseEntity<byte[]> exchange =
                restTemplate.exchange("http://192.168.16.172:9000/video/mp4", HttpMethod.POST, entity, byte[].class);
        return exchange.getBody();
    }
}
