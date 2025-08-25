package com.prerana.rest_demo.repository;

import com.prerana.rest_demo.RestDemoApplication;
import com.prerana.rest_demo.model.CloudVendor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = RestDemoApplication.class)
public class CloudVendorRepositoryTest {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;

    @Test
    void testSaveVendor() {
        CloudVendor vendor = new CloudVendor("1", "AWS", "Bangalore", "xxxx");
        cloudVendorRepository.save(vendor);

        CloudVendor savedVendor = cloudVendorRepository.findById("1").orElse(null);
        assertThat(savedVendor).isNotNull();
        assertThat(savedVendor.getVendorName()).isEqualTo("AWS");
    }
}
