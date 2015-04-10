package com.kblyumkin.skuServices.spaces;

import com.gigaspaces.async.AsyncResult;
import com.j_spaces.core.client.SQLQuery;
import com.kblyumkin.skuServices.beans.Sku;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DescriptionSearchTask implements DistributedTask<ArrayList<Sku>, List<Sku>> {
    private String parameter;
    private long id;

    @TaskGigaSpace
    private transient GigaSpace gigaSpace;

    public DescriptionSearchTask(String parameter) {
        this.parameter = parameter;
        id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    @Override
    public List<Sku> reduce(List<AsyncResult<ArrayList<Sku>>> asyncResults) throws Exception {
        List<Sku> result = new ArrayList<>();
        for (AsyncResult<ArrayList<Sku>> asyncResult : asyncResults) {
            if (asyncResult.getException() != null) {
                throw asyncResult.getException();
            }
            Thread.sleep(10000);
            result.addAll(asyncResult.getResult());
        }
        return result;
    }

    @Override
    public ArrayList<Sku> execute() throws Exception {
        ArrayList<Sku> result = new ArrayList<>();
        result.addAll(Arrays.asList(gigaSpace.readMultiple(
                new SQLQuery<Sku>(Sku.class, "skuDescription like '%" + parameter + "%'"))));
        System.out.println("Found " + result.size() + " results");
        return result;
    }
}
