package com.kblyumkin.skuServices.spaces;

import com.gigaspaces.async.AsyncFuture;
import com.j_spaces.core.client.SQLQuery;
import com.kblyumkin.skuServices.beans.Sku;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.openspaces.extensions.QueryExtension.max;

public class SkuManager {

    @Autowired
    private GigaSpace gigaspace;

    @Transactional(propagation = Propagation.REQUIRED)
    public void write(Sku sku) {
        sku.setId(getNextId() + 1);
        System.out.println("Writing " + sku);
        gigaspace.write(sku);
    }

    private Long getNextId() {
        SQLQuery<Sku> query = new SQLQuery<Sku>(Sku.class, "id > 0");
        return max(gigaspace, query, "id");
    }

    public List<Sku> searchByDescription(String description) throws InterruptedException, ExecutionException {
        AsyncFuture<List<Sku>> result = gigaspace.execute(new DescriptionSearchTask(description));
        while (!result.isDone()) {
            Thread.sleep(100);
        }
        System.out.println("For request with description = " + description + " found " + result.get().size() + " results");
        System.out.println(Arrays.toString(result.get().toArray()));
        return result.get();
    }

}
