package tr.com.khg.userservice.service.utils;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/*
    Feign Request'leri esnasında yaşanabilecek olan hataların exception'ları
    için kendimize özgü hata denetimi
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final Environment environment;

    public FeignErrorDecoder(Environment environment) {
        this.environment = environment;
    }

    /**
     * Feign Hata Handler'ı
     *
     * @param s Feigni çağırdığımız metot ismi (Feign olarak işaretlenen interfacedeki isim)
     * @param response Service'ten alınan cevap
     * @return Fırtatılacak hata
     */
    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()) {
            case 400:
                // Add here
                break;
            case 404:
                return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                        environment.getProperty("albums.exceptions.not-found"));
            default:
                return new Exception(response.reason());
        }

        return null;
    }

}
