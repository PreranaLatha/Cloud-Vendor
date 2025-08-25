package com.prerana.rest_demo.controller;


import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import com.prerana.rest_demo.model.CloudVendor;
import com.prerana.rest_demo.service.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.prerana.rest_demo.response.ResponseHandler;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController
{
    CloudVendorService cloudVendorService;

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    // Read Specific Cloud Vendor Details from DB
    @GetMapping("/{vendorId}")
    @ApiOperation(value ="Cloud vendor id", notes="Provide cloud vendor details",
            response = ResponseEntity.class)
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId)
    {
        return ResponseHandler.responseBuilder("Requested Vendor Details are given here",
                HttpStatus.OK, cloudVendorService.getCloudVendor(vendorId));
    }

    // Read All Cloud Vendor Details from DB
    @GetMapping("/")
    public List<CloudVendor> getAllCloudVendorDetails()
    {
        return cloudVendorService.getAllCloudVendors();
    }

    @PostMapping("/")
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor)
    {
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor Created Successfully";
    }

    @PutMapping("/{vendorId}")
    public ResponseEntity<Object> updateCloudVendorDetails(
            @PathVariable("vendorId") String vendorId,
            @RequestBody CloudVendor cloudVendor)
    {
        cloudVendor.setVendorId(vendorId); // ensure vendorId is set
        cloudVendorService.updateCloudVendor(cloudVendor);
        return ResponseHandler.responseBuilder(
                "Cloud Vendor Updated Successfully",
                HttpStatus.OK,
                cloudVendor
        );
    }

    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId)
    {
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud Vendor Deleted Successfully";
    }
}