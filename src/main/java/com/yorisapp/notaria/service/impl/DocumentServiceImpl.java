package com.yorisapp.notaria.service.impl;

import com.yorisapp.notaria.service.DocumentService;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Override
    public void uploadDocument(String pDocument) {
        /*Request vRequest = this.getRequestById(pRequestId);
        if (vRequest.getFlowType().equals(Constants.OP_ACCOUNT_FLOW)){
            DomainValue vDomainValueDocument = domainValueService.getDomainValueByDomainCodeAndCodeValue(Constants.TYPE_PATH, Constants.PATH_DOCUMENT, Constants.STATE_ACTIVE);
            Image vImage = new Image();
            String vFileName = vImage.convertBase64toFile(vDomainValueDocument.getCharValue()+File.separator+pRequestId, pDocument, pDocumentType);
            BackUpDocument vBackUpDocument = new BackUpDocument();
            vBackUpDocument.setDocumentType(pDocumentType);
            vBackUpDocument.setFileName(vFileName);
            vBackUpDocument.setRequest(vRequest);
            backUpDocumentRepository.save(vBackUpDocument);
        } else{
            DomainValue vDomainValueDocument = domainValueService.getDomainValueByDomainCodeAndCodeValue(Constants.TYPE_PATH, Constants.PATH_CREDIT_DOCUMENT, Constants.STATE_ACTIVE);
            Document vDocument = documentRepository.getDocumentByRequestIdAndDocumentTypeId(pRequestId, pDocumentType, Constants.BANK_CREDIT_FLOW).orElse(null);
            if (vDocument != null){
                Image vImage = new Image();
                String vFileName = vImage.convertBase64toFile(vDomainValueDocument.getCharValue()+File.separator+pRequestId, pDocument, pDocumentType);
                vDocument.setFileName(vFileName);
                vDocument.setStateEvaluation(Constants.STATE_PENDING);
                documentRepository.save(vDocument);
            }
            DocumentLog vDocumentLog = documentLogRepository.getDocumentByRequestIdAndDocumentTypeId(vRequest.getAbstractaCode(),vDocument.getDocumentTypeId(), Constants.BANK_CREDIT_FLOW).orElse(null);
            vDocumentLog.setStateEvaluation(Constants.INTEGRATION_LOG_STATE_OK);

            documentLogRepository.save(vDocumentLog);
            requestService.updateStateByRequestId(vRequest.getId(),Constants.STATE_CORRECTED);*/
    }
}
