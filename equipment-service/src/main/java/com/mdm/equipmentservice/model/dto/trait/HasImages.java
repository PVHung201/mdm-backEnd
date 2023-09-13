package com.mdm.equipmentservice.model.dto.trait;

import com.mdm.equipmentservice.model.dto.base.FileStorageDto;

import java.util.List;

public interface HasImages extends HasAttachmentsOrImages {

    List<FileStorageDto> getImages();
}
