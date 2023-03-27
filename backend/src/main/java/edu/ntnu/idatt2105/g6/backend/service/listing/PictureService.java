package edu.ntnu.idatt2105.g6.backend.service.listing;

import edu.ntnu.idatt2105.g6.backend.dto.listing.PictureDTO;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.ItemNotFoundException;
import edu.ntnu.idatt2105.g6.backend.mapper.listing.PictureMapper;
import edu.ntnu.idatt2105.g6.backend.repo.listing.PictureGalleryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureGalleryRepository pictureGalleryRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public PictureDTO loadAllImagesByItemId(Long itemId) {
        return PictureMapper.toPictureDTO(pictureGalleryRepository.findAllByItem_ItemId(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId)));
    }

}
