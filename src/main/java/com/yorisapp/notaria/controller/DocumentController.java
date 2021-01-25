package com.yorisapp.notaria.controller;

import com.yorisapp.notaria.service.DocumentService;
import com.yorisapp.notaria.service.dto.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/documents")
public class DocumentController {
    private final DocumentService documentService;
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponseDto uploadDocument(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        return documentService.uploadDocument(file, request);
    }
}
