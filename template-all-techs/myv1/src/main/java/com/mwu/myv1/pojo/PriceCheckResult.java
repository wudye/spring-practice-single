package com.mwu.myv1.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceCheckResult {
    private Long id;
    private boolean status;
}
