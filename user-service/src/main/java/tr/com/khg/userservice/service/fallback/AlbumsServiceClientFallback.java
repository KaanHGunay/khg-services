package tr.com.khg.userservice.service.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.com.khg.userservice.domain.ui.AlbumsResponseModel;
import tr.com.khg.userservice.service.AlbumsServiceClient;

import java.util.ArrayList;
import java.util.List;

public class AlbumsServiceClientFallback implements AlbumsServiceClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Throwable throwable;

    public AlbumsServiceClientFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public List<AlbumsResponseModel> getAlbums(String id) {
        logger.error("Occurred while getting albums: {}", throwable.getMessage());
        return new ArrayList<>();
    }
}
