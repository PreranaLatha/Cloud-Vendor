package com.prerana.rest_demo.service.impl;

import com.prerana.rest_demo.model.CloudVendor;
import com.prerana.rest_demo.repository.CloudVendorRepository;
import com.prerana.rest_demo.service.CloudVendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CloudVendorServiceImplTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;

    @InjectMocks
    private CloudVendorServiceImpl cloudVendorService;

    private CloudVendor vendor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vendor = new CloudVendor("1", "AWS", "Seattle", "1234567890");
    }

    @Test
    void testCreateCloudVendor() {
        when(cloudVendorRepository.save(vendor)).thenReturn(vendor);

        String result = cloudVendorService.createCloudVendor(vendor);

        assertThat(result).isEqualTo("Success");
        verify(cloudVendorRepository, times(1)).save(vendor);
    }

    @Test
    void testUpdateCloudVendor() {
        when(cloudVendorRepository.save(vendor)).thenReturn(vendor);

        String result = cloudVendorService.updateCloudVendor(vendor);

        assertThat(result).isEqualTo("Success");
        verify(cloudVendorRepository, times(1)).save(vendor);
    }

    @Test
    void testDeleteCloudVendor() {
        doNothing().when(cloudVendorRepository).deleteById("1");

        String result = cloudVendorService.deleteCloudVendor("1");

        assertThat(result).isEqualTo("Success");
        verify(cloudVendorRepository, times(1)).deleteById("1");
    }

    @Test
    void testGetCloudVendor() {
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.of(vendor));

        CloudVendor found = cloudVendorService.getCloudVendor("1");

        assertThat(found).isNotNull();
        assertThat(found.getVendorName()).isEqualTo("AWS");
    }

    @Test
    void testGetAllCloudVendors() {
        List<CloudVendor> vendors = Arrays.asList(vendor, new CloudVendor("2", "Azure", "Redmond", "9876543210"));
        when(cloudVendorRepository.findAll()).thenReturn(vendors);

        List<CloudVendor> result = cloudVendorService.getAllCloudVendors();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getVendorName()).isEqualTo("AWS");
    }
}
