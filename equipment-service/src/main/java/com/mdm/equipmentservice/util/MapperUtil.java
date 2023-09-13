package com.mdm.equipmentservice.util;

import com.mdm.equipmentservice.model.dto.base.FileStorageDto;
import com.mdm.equipmentservice.model.dto.trait.HasAttachments;
import com.mdm.equipmentservice.model.dto.trait.HasAttachmentsOrImages;
import com.mdm.equipmentservice.model.dto.trait.HasId;
import com.mdm.equipmentservice.model.dto.trait.HasImages;
import com.mdm.equipmentservice.model.entity.FileDescription;
import com.mdm.equipmentservice.service.FileStorageService;
import org.springframework.data.util.Streamable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MapperUtil {

    private final FileStorageService fileStorageService;

    public MapperUtil(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public <S extends HasId, D extends HasAttachmentsOrImages> Iterable<D> mapToIterableHasAttachmentsDto(@NonNull Iterable<S> entities,
                                                                                                          @NonNull Class<D> dtoClass,
                                                                                                          @Nullable FileDescription fileDescription, @NonNull
                                                                                                          Function<? super S, ? extends D> converter) {
        if (entities instanceof Streamable<S> streamableEntities) {
            List<Long> entityIds = streamableEntities.stream().map(S::getId).toList();
            List<FileStorageDto> fileStorageDtos =
                    fileStorageService.getAllFilesFromListOfEntityIdsWithoutData(dtoClass.getSimpleName(), entityIds, fileDescription);
            return streamableEntities.map(entity -> toHasAttachmentsOrImagesDto(entity, fileStorageDtos, converter));
        }
        if (entities instanceof Collection<S> entityCollection) {
            List<Long> entityIds = entityCollection.stream().map(S::getId).toList();
            List<FileStorageDto> fileStorageDtos =
                    fileStorageService.getAllFilesFromListOfEntityIdsWithoutData(dtoClass.getSimpleName(), entityIds, fileDescription);
            Stream<D> stream = entityCollection.stream().map(entity -> toHasAttachmentsOrImagesDto(entity, fileStorageDtos, converter));
            if (entities instanceof List<S>) {
                return stream.collect(Collectors.toList());
            } else if (entities instanceof Set<S>) {
                return stream.collect(Collectors.toSet());
            } else {
                throw new RuntimeException("Collection type not supported");
            }
        }
        throw new RuntimeException("Iterable type not supported");
    }

    public <S extends HasId, D extends HasAttachmentsOrImages> D toHasAttachmentsOrImagesDto(S entity, List<FileStorageDto> fileStorageDtos,
                                                                                             @NonNull Function<? super S, ? extends D> converter) {
        D dto = converter.apply(entity);
        fileStorageDtos.forEach(fileStorageDto -> {
            if (fileStorageDto.getAssociatedEntityId().equals(entity.getId())) {
                if (dto instanceof HasAttachments) ((HasAttachments) dto).getAttachments().add(fileStorageDto);
                if (dto instanceof HasImages) ((HasImages) dto).getImages().add(fileStorageDto);
            }
        });
        return dto;
    }
}