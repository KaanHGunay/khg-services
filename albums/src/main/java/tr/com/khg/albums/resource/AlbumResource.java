package tr.com.khg.albums.resource;

import com.google.common.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.khg.albums.domain.Album;
import tr.com.khg.albums.domain.model.AlbumResponseModel;
import tr.com.khg.albums.service.AlbumService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/{id}/albums")
public class AlbumResource {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AlbumService albumService;

    public AlbumResource(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
        })
    public List<AlbumResponseModel> userAlbums(@PathVariable String id) {

        List<AlbumResponseModel> returnValue = new ArrayList<>();

        List<Album> albumsEntities = albumService.getAlbums(id);

        if(albumsEntities == null || albumsEntities.isEmpty())
        {
            return returnValue;
        }

        Type listType = new TypeToken<List<AlbumResponseModel>>(){}.getType();

        returnValue = new ModelMapper().map(albumsEntities, listType);
        logger.info("Returning " + returnValue.size() + " albums");
        return returnValue;
    }
}
