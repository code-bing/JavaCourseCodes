package io.dobson.account.mapper;

import io.dobson.account.entity.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountInfoMapper {
    @Update("update account_info set usd_balance = usd_balance + #{usd_balance}, rmb_balance = rmb_balance + #{rmb_balance}" +
            " where usd_balance >= #{usd_balance} and rmb_balance >= #{rmb_balance} and id = #{id}")
    boolean exchange(AccountInfo accountInfo);
}
