package com.meheedihasaan.springbootredis.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.TreeMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationArgs {
    private int pageNo;

    private int pageSize;

    private String sortBy;

    private String sortOrder;

    private Map<String, Object> filters = new TreeMap<>();
}
