package com.tabularize.app.contoller;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.tabularize.app.model.Report;
import com.tabularize.app.services.ReportService;
import com.tabularize.app.wrapper.ResponseWrapper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("api/file")
@CrossOrigin(origins = "*") 
public class FileUploadController {
	
	
	@Autowired
	ReportService reportService;
	
    @PostMapping("/upload")
    public ResponseWrapper<Void> uploadFile(@RequestParam("file") MultipartFile file, @NotBlank @NotNull @RequestHeader("Authorization") String authorizationHeader) {
        try {
            reportService.uploadFile(file, authorizationHeader);
        	return new ResponseWrapper<Void>(HttpStatus.OK, "Successfully Stored Report", null);
		} catch (Exception e) {
			return new ResponseWrapper<Void>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null); 
		}

    }
    
    @GetMapping("/getAll")
    public ResponseWrapper<List<Report>> uploadFile(@NotBlank @NotNull  @NotBlank @NotNull @RequestHeader("Authorization") String authorizationHeader) {
        try {
            List<Report> reportDetails = reportService.getAllReports(authorizationHeader);
        	return new ResponseWrapper<List<Report>>(HttpStatus.OK, "Fetched Report Details", reportDetails);
		} catch (Exception e) {
			return new ResponseWrapper<List<Report>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null); 
		}

    }
   
}

