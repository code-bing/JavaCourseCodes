package io.dobson.account.service.impl;

import io.dobson.account.entity.AccountInfo;
import io.dobson.account.mapper.AccountInfoMapper;
import io.dobson.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DubboService(version = "1.0.0")
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountInfoMapper accountInfoMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public boolean pay(AccountInfo accountInfo) {
        return accountInfoMapper.exchange(accountInfo);
    }

    public void confirmMethod (){
        log.info("confirm 交易操作================");
    }

    public void cancelMethod(){
        log.info("cancel 交易操作==================");
    }
}
