package com.yorisapp.notaria.service.impl;

import com.yorisapp.notaria.model.entity.Document;
import com.yorisapp.notaria.service.DocumentService;
import com.yorisapp.notaria.service.DomainValueService;
import com.yorisapp.notaria.service.dto.CommonResponseDto;
import com.yorisapp.notaria.service.dto.domainValue.DomainValueQueryDto;
import com.yorisapp.notaria.util.Constants;
import com.yorisapp.notaria.util.Upload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DomainValueService domainValueService;


    public DocumentServiceImpl(DomainValueService domainValueService){
        this.domainValueService = domainValueService;
    }
    @Override
    public CommonResponseDto uploadDocument(MultipartFile pFile, HttpServletRequest pHttpServletRequest) {
        DomainValueQueryDto vDomainValueQueryDto = domainValueService.getDomainValueByDomainAndCodeAndState(Constants.PATH, Constants.PATH_DOCUMENT, Constants.STATE_ACTIVE);
        Document vDocument = new Document();
        try{
            String vFileName = pFile.getOriginalFilename();
            String pPath = vDomainValueQueryDto.getCharValue()+File.pathSeparator+vFileName;
            Upload.saveFile(pFile.getInputStream(),pPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommonResponseDto vCommonResponseDto = new CommonResponseDto();
        vCommonResponseDto.setMessage("Mensaje");
        vCommonResponseDto.setStatus("info");
        return vCommonResponseDto;
    }
}
