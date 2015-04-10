package com.kblyumkin.skuServices.services.impl;

import com.kblyumkin.skuServices.beans.*;
import com.kblyumkin.skuServices.services.SkuService;
import com.kblyumkin.skuServices.spaces.SkuManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.concurrent.ExecutionException;

@WebService(endpointInterface = "com.kblyumkin.skuServices.services.SkuService",
        targetNamespace = "http://skuService.kblyumkin.com/users",
        serviceName = "SkuService",
        portName = "SkuServicePort",
        wsdlLocation = "SkuService.wsdl")
public class SkuServiceImpl implements SkuService {
    @Autowired
    SkuManager manager;

    @Override
    public Sku processSku(Sku sku) {
        System.out.println("Received " + sku);
        manager.write(sku);
        return sku;
    }

    @Override
    public SearchResponse searchByDescription(SearchRequest description) throws ExecutionException, InterruptedException {
        return new SearchResponse(manager.searchByDescription(description.getSearchDescription()));
    }

    @Override
    public SearchResultResponse getResult(SearchResultRequest request) throws ExecutionException, InterruptedException {
        return manager.getResult(request.getResultId());
    }
}
