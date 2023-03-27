package edu.ntnu.idatt2105.g6.backend.dto.listing;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record PictureDTO(@NonNull List<byte[]> pictures) {
}
