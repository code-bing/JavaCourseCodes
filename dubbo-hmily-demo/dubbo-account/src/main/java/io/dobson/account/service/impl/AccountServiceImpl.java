package io.dobson.account.service.impl;

import io.dobson.account.entity.AccountInfo;
import io.dobson.account.mapper.AccountInfoMapper;
import io.dobson.account.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DubboService(version = "1.0.0")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountInfoMapper accountInfoMapper;

    @Override
    public boolean pay(AccountInfo accountInfo) {
        return accountInfoMapper.exchange(accountInfo);
    }
}
