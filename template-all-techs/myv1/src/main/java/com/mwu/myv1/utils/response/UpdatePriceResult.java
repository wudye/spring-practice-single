package com.mwu.myv1.utils.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePriceResult {
    private Boolean status;
    private String msg;
}
