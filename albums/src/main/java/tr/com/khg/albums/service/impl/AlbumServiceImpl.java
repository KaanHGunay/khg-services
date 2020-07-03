package tr.com.khg.albums.service.impl;

import org.springframework.stereotype.Service;
import tr.com.khg.albums.domain.Album;
import tr.com.khg.albums.service.AlbumService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Override
    public List<Album> getAlbums(String userId) {
        List<Album> returnValue = new ArrayList<>();

        Album albumEntity = new Album();
        albumEntity.setUserId(userId);
        albumEntity.setAlbumId("album1Id");
        albumEntity.setDescription("album 1 description");
        albumEntity.setId(1L);
        albumEntity.setName("album 1 name");

        Album albumEntity2 = new Album();
        albumEntity2.setUserId(userId);
        albumEntity2.setAlbumId("album2Id");
        albumEntity2.setDescription("album 2 description");
        albumEntity2.setId(2L);
        albumEntity2.setName("album 2 name");

        returnValue.add(albumEntity);
        returnValue.add(albumEntity2);

        return returnValue;
    }
}
