package io.dobson.account.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@Data
public class AccountInfo implements Serializable {
    private Integer id;
    private Long accountId;
    private Long usd_balance;
    private Long rmb_balance;
    private Date updateTime;

}
