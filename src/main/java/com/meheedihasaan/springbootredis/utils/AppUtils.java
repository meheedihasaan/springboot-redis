package com.meheedihasaan.springbootredis.utils;

import com.meheedihasaan.springbootredis.models.dto.PaginationArgs;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

@UtilityClass
public class AppUtils {

    public static Pageable getPageable(PaginationArgs paginationArgs) {
        Pageable pageable;
        String sortBy = paginationArgs.getSortBy();
        int pageNo = paginationArgs.getPageNo();
        int pageSize = paginationArgs.getPageSize();

        if(sortBy != null && !sortBy.isEmpty()) {
            if (paginationArgs.getSortOrder().equals("asc")) {
                pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
            } else {
                pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
            }
        } else {
            pageable = PageRequest.of(pageNo, pageSize);
        }

        return pageable;
    }

    public static Map<String, Object> getFilters(Map<String, Object> filters) {
        filters.remove("pageNo");
        filters.remove("pageSize");
        filters.remove("sortBy");
        filters.remove("sortOrder");
        return filters;
    }
}
