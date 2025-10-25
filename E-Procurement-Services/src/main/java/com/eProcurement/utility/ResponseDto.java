package com.eProcurement.utility;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    private String responseMessege;
    private Integer responseCode;
    private Object data;
    private List dataList;

}
