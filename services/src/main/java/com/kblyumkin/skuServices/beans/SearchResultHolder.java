package com.kblyumkin.skuServices.beans;

import com.gigaspaces.async.AsyncFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchResultHolder {
    private AsyncFuture<List<Sku>> taskResult;
    private Status status;

    public SearchResultHolder(AsyncFuture<List<Sku>> taskResult) {
        this.status = Status.IN_PROCESS;
        this.taskResult = taskResult;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Sku> getResult() throws ExecutionException, InterruptedException {
        return taskResult.get();
    }

    public enum Status {IN_PROCESS, ERROR, COMPLETED}
}
