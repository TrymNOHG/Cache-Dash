package edu.ntnu.idatt2105.g6.backend.mapper.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.PictureDTO;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.PictureGallery;

import java.util.List;

public class PictureMapper {

    public static PictureDTO toPictureDTO(List<PictureGallery> pictures) {
        return PictureDTO
                .builder()
                .pictures(pictures.stream().map(PictureGallery::getPicture).toList())
                .build();
    }

    public static PictureGallery toPictureGallery(byte[] picture, Item item){
        return PictureGallery
                .builder()
                .item(item)
                .picture(picture)
                .build();
    }

}
