package com.kblyumkin.skuServices.services;

import com.kblyumkin.skuServices.beans.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.concurrent.ExecutionException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
        use = SOAPBinding.Use.LITERAL,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface SkuService {
    @WebMethod(action="#processSku")
    @WebResult(name = "response")
    Sku processSku(@WebParam(name="processSku") Sku sku);

    @WebMethod(action = "#searchByDescription")
    @WebResult(name = "response")
    SearchResponse searchByDescription(@WebParam(name="searchByDescription") SearchRequest description) throws ExecutionException, InterruptedException;

    @WebMethod(action = "#getResult")
    @WebResult(name = "response")
    SearchResultResponse getResult(@WebParam(name="getResult") SearchResultRequest request) throws ExecutionException, InterruptedException;
}
