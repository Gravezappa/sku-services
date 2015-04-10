package com.kblyumkin.skuServices.spaces;

import com.gigaspaces.async.AsyncFuture;
import com.gigaspaces.async.AsyncFutureListener;
import com.gigaspaces.async.AsyncResult;
import com.j_spaces.core.client.SQLQuery;
import com.kblyumkin.skuServices.beans.SearchResultHolder;
import com.kblyumkin.skuServices.beans.SearchResultResponse;
import com.kblyumkin.skuServices.beans.Sku;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.openspaces.extensions.QueryExtension.max;

public class SkuManager {

    @Autowired
    private GigaSpace gigaspace;

    private Map<Long, SearchResultHolder> resultMap = new HashMap<>();

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

    public long searchByDescription(String description) throws InterruptedException, ExecutionException {
        final DescriptionSearchTask task = new DescriptionSearchTask(description);
        AsyncFuture<List<Sku>> result = gigaspace.execute(task);
        resultMap.put(task.getId(), new SearchResultHolder(result));
        result.setListener(new AsyncFutureListener<List<Sku>>() {
            @Override
            public void onResult(AsyncResult<List<Sku>> listAsyncResult) {
                SearchResultHolder holder = resultMap.get(task.getId());
                holder.setStatus(SearchResultHolder.Status.COMPLETED);
            }
        });
        return task.getId();
    }

    public SearchResultResponse getResult(Long taskId) throws ExecutionException, InterruptedException {
        SearchResultHolder holder = resultMap.get(taskId);
        SearchResultResponse result = new SearchResultResponse();
        result.setStatus(holder.getStatus());
        if (SearchResultHolder.Status.COMPLETED.equals(holder.getStatus())) {
            result.setResult(holder.getResult());
        }
        return result;
    }

}
