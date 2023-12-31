package com.mdm.equipmentservice;


import com.mdm.equipmentservice.exception.ResourceNotFoundException;
import com.mdm.equipmentservice.model.repository.EquipmentRepository;
import com.mdm.equipmentservice.service.impl.EquipmentServiceImpl;
import com.mdm.equipmentservice.util.UniqueValidationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DemoTest {
    @Mock
    private EquipmentRepository repository;
    @Mock
    private UniqueValidationUtil uniqueValidationUtil;
    @InjectMocks
    private EquipmentServiceImpl service;



    @Test
    public void whenGetEquipmentByIdWithInvalidId_thenThrowResourceNotFoundException() {
        // Given
        Long id = 1L;
        String expectedMessage = "equipmentNotFound";
        // When
        when(repository.findById(id)).thenThrow(new ResourceNotFoundException(expectedMessage));
        // Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getEquipmentById(id));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void whenCreateEquipmentWithValidationFailed_thenThrowValidationFailedException() {

    }
}
