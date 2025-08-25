package com.prerana.cloudvendor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prerana.rest_demo.controller.CloudVendorController;
import com.prerana.rest_demo.model.CloudVendor;
import com.prerana.rest_demo.service.CloudVendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CloudVendorService cloudVendorService;

    private CloudVendor vendor;

    @BeforeEach
    void setup() {
        vendor = new CloudVendor("1", "AWS", "Seattle", "1234567890");
    }

    @Test
    void testCreateCloudVendor() throws Exception {
        Mockito.when(cloudVendorService.createCloudVendor(any(CloudVendor.class)))
                .thenReturn("Cloud Vendor Created Successfully");

        mockMvc.perform(post("/cloudvendor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vendor)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cloud Vendor Created Successfully"));
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        Mockito.when(cloudVendorService.getCloudVendor("1")).thenReturn(vendor);

        mockMvc.perform(get("/cloudvendor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorId").value("1"))
                .andExpect(jsonPath("$.vendorName").value("AWS"));
    }

    @Test
    void testGetAllCloudVendors() throws Exception {
        List<CloudVendor> vendors = Arrays.asList(vendor, new CloudVendor("2", "Azure", "Hyderabad", "67890"));
        Mockito.when(cloudVendorService.getAllCloudVendors()).thenReturn(vendors);

        mockMvc.perform(get("/cloudvendor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vendorName").value("AWS"))
                .andExpect(jsonPath("$[1].vendorName").value("Azure"));
    }

    @Test
    void testUpdateCloudVendor() throws Exception {
        Mockito.when(cloudVendorService.updateCloudVendor(any(CloudVendor.class)))
                .thenReturn("Cloud Vendor Updated Successfully");

        vendor.setVendorName("AWS-Updated");

        mockMvc.perform(put("/cloudvendor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vendor)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cloud Vendor Updated Successfully"));
    }

    @Test
    void testDeleteCloudVendor() throws Exception {
        Mockito.when(cloudVendorService.deleteCloudVendor("1"))
                .thenReturn("Cloud Vendor Deleted Successfully");

        mockMvc.perform(delete("/cloudvendor/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cloud Vendor Deleted Successfully"));
    }
}
