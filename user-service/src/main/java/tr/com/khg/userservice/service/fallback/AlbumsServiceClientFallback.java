package tr.com.khg.userservice.service.fallback;

import org.springframework.stereotype.Component;
import tr.com.khg.userservice.domain.ui.AlbumsResponseModel;
import tr.com.khg.userservice.service.AlbumsServiceClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumsServiceClientFallback implements AlbumsServiceClient {

    @Override
    public List<AlbumsResponseModel> getAlbums(String id) {
        return new ArrayList<>();
    }
}
