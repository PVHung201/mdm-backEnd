package com.mdm.equipmentservice.controller;

import com.mdm.equipmentservice.mapper.EquipmentMapper;
import com.mdm.equipmentservice.model.dto.base.EquipmentDto;
import com.mdm.equipmentservice.model.dto.base.EquipmentExcelDto;
import com.mdm.equipmentservice.model.dto.base.EquipmentSupplyUsageDto;
import com.mdm.equipmentservice.model.dto.form.AttachSupplyForm;
import com.mdm.equipmentservice.model.dto.form.UpsertEquipmentForm;
import com.mdm.equipmentservice.model.dto.fullinfo.EquipmentFullInfoDto;
import com.mdm.equipmentservice.model.dto.list.EquipmentListDto;
import com.mdm.equipmentservice.query.param.GetEquipmentsQueryParam;
import com.mdm.equipmentservice.response.GenericResponse;
import com.mdm.equipmentservice.service.EquipmentService;
import com.mdm.equipmentservice.service.ReadExcelService;
import com.mdm.equipmentservice.service.WriteExcelService;
import com.mdm.equipmentservice.service.impl.ReadExcelServiceImpl;
import com.mdm.equipmentservice.service.impl.WriteExcelServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import static com.mdm.equipmentservice.constant.DateTimeFormatConstant.DATE_TIME_FORMAT;

@RestController
@RequestMapping(value = "/api/v1/equipments")
public class EquipmentController {

    private final EquipmentService equipmentService;

    private final PagedResourcesAssembler<EquipmentListDto> equipmentDtoPagedResourcesAssembler;

    private final PagedResourcesAssembler<EquipmentFullInfoDto> equipmentFullInfoDtoPagedResourcesAssembler;


    private final WriteExcelService<EquipmentExcelDto> writeExcelService;


    private final ReadExcelService<UpsertEquipmentForm> readExcelService;

    private final EquipmentMapper equipmentMapper;

    @Autowired
    public EquipmentController(
            EquipmentService equipmentService, PagedResourcesAssembler<EquipmentListDto> equipmentDtoPagedResourcesAssembler,
            PagedResourcesAssembler<EquipmentFullInfoDto> equipmentFullInfoDtoPagedResourcesAssembler,
            WriteExcelServiceImpl<EquipmentExcelDto> writeExcelService, ReadExcelServiceImpl<UpsertEquipmentForm> readExcelService,
            EquipmentMapper equipmentMapper) {
        this.equipmentService = equipmentService;
        this.equipmentDtoPagedResourcesAssembler = equipmentDtoPagedResourcesAssembler;
        this.equipmentFullInfoDtoPagedResourcesAssembler = equipmentFullInfoDtoPagedResourcesAssembler;
        this.writeExcelService = writeExcelService;
        this.readExcelService = readExcelService;
        this.equipmentMapper = equipmentMapper;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<PagedModel<EntityModel<EquipmentListDto>>>> getEquipments(
            GetEquipmentsQueryParam getEquipmentsQueryParam, Pageable pageable
    ) {
        PagedModel<EntityModel<EquipmentListDto>> entityModels = equipmentDtoPagedResourcesAssembler.toModel(equipmentService.getEquipments(
                getEquipmentsQueryParam,
                pageable
        ));
        return ResponseEntity.ok(new GenericResponse<>(entityModels));
    }

    @GetMapping("/statistics")
    public ResponseEntity<GenericResponse<PagedModel<EntityModel<EquipmentFullInfoDto>>>> statisticEquipments(GetEquipmentsQueryParam getEquipmentsQueryParam,
                                                                                                              Pageable pageable) {
        Page<EquipmentFullInfoDto> fullInfoDtoPage = equipmentService.statisticEquipments(getEquipmentsQueryParam, pageable);
        return ResponseEntity.ok(new GenericResponse<>(equipmentFullInfoDtoPagedResourcesAssembler.toModel(fullInfoDtoPage)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<EquipmentFullInfoDto>> getEquipment(
            @PathVariable(name = "id") Long equipmentId
    ) throws AccessDeniedException {
        EquipmentFullInfoDto equipmentDto = equipmentService.getEquipmentById(equipmentId);
        return ResponseEntity.ok(new GenericResponse<>(equipmentDto));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponse<EquipmentDto>> createEquipment(
            @RequestPart(name = "equipment") UpsertEquipmentForm equipment, @RequestPart(required = false, name = "image") MultipartFile image
    ) throws IOException {
        EquipmentDto equipmentDto = equipmentService.createEquipment(equipment, image);
        return new ResponseEntity<>(new GenericResponse<>(equipmentDto, 201), HttpStatus.CREATED);
    }

    @PostMapping(value = "/create-multiple", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Object>> createMultiple(@RequestBody List<UpsertEquipmentForm> upsertEquipmentForms) {
        equipmentService.createMultipleEquipment(upsertEquipmentForms);
        return new ResponseEntity<>(new GenericResponse<>(null, 201), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponse<EquipmentDto>> updateEquipment(
            @PathVariable(name = "id") Long equipmentId, @RequestPart("equipment") @Valid UpsertEquipmentForm upsertEquipmentForm,
            @RequestPart(required = false, name = "image") MultipartFile image
    ) {
        EquipmentDto equipmentDto = equipmentService.updateEquipment(equipmentId, upsertEquipmentForm, image);
        return ResponseEntity.ok(new GenericResponse<>(equipmentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> deleteEquipment(@PathVariable(name = "id") Long equipmentId) {
        equipmentService.deleteEquipment(equipmentId);
        return new ResponseEntity<>(new GenericResponse<>(null, 204), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete-multiple", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Object>> deleteMultiple(@RequestBody List<Long> equipmentIds) {
        equipmentService.deleteMultipleEquipment(equipmentIds);
        return new ResponseEntity<>(new GenericResponse<>(null, 204), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/excel/export")
    public void export(HttpServletResponse response) throws IOException {
        String localDateTime = LocalDateTime.now().format(DATE_TIME_FORMAT);
        String fileName = "Danh sach thiet bi " + localDateTime + ".xlsx";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=\"" + fileName + "\"";

        response.setHeader(headerKey, headerValue);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        List<EquipmentExcelDto> equipmentExcelDtoList =
                equipmentService.getEquipments(new GetEquipmentsQueryParam(), PageRequest.of(0, 10000)).map(equipmentMapper::toExcelDto).getContent();
        writeExcelService.writeExcelFile(equipmentExcelDtoList, response.getOutputStream(), EquipmentExcelDto.class);
    }

    @PostMapping(value = "/excel/import")
    public ResponseEntity<GenericResponse<Object>> importExcel(MultipartFile excelFile, Long departmentId) throws Exception {
        String excelPath = excelFile.getOriginalFilename();
        InputStream inputStream = excelFile.getInputStream();
        List<UpsertEquipmentForm> list = readExcelService.readExcelFile(inputStream, excelPath, UpsertEquipmentForm.class);
        list.forEach(e -> e.setDepartmentId(departmentId));
        equipmentService.createMultipleEquipment(list);
        return new ResponseEntity<>(new GenericResponse<>(list, 200), HttpStatus.OK);
    }

    @PostMapping(value = "/attach-supplies")
    public ResponseEntity<GenericResponse<EquipmentSupplyUsageDto>> attachSupplies(@RequestBody AttachSupplyForm attachSupplyForm) {
        EquipmentSupplyUsageDto equipmentSupplyUsageDto = equipmentService.attachSupplies(attachSupplyForm);
        return ResponseEntity.ok(new GenericResponse<>(equipmentSupplyUsageDto));
    }
}
