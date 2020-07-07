package tr.com.khg.userservice.service.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import tr.com.khg.userservice.service.AlbumsServiceClient;

@Component
public class AlbumsServiceClientFallbackFactory implements FallbackFactory<AlbumsServiceClient> {

    @Override
    public AlbumsServiceClient create(Throwable throwable) {
        return new AlbumsServiceClientFallback(throwable);
    }
}
