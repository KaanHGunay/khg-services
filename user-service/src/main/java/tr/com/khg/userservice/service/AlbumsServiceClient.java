package tr.com.khg.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tr.com.khg.userservice.domain.ui.AlbumsResponseModel;
import tr.com.khg.userservice.service.fallback.AlbumsServiceClientFallbackFactory;

import java.util.List;

@FeignClient(name = "album-service", fallbackFactory = AlbumsServiceClientFallbackFactory.class)
public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albums")
    public List<AlbumsResponseModel> getAlbums(@PathVariable String id);

}
