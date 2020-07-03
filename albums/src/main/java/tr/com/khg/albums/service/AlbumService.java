package tr.com.khg.albums.service;

import tr.com.khg.albums.domain.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAlbums(String userId);

}
