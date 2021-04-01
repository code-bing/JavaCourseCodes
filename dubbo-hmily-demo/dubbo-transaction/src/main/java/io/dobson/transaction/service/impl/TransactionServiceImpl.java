package io.dobson.transaction.service.impl;

import io.dobson.account.entity.AccountInfo;
import io.dobson.account.service.AccountService;
import io.dobson.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @DubboReference(version = "1.0.0")
    private AccountService accountService;

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public void pay() {
        transactionA();
        transactionB();
    }

    private void transactionB() {
        AccountInfo account = AccountInfo.builder().id(2).rmb_balance(7L).usd_balance(-1L).build();
        accountService.pay(account);
    }

    private void transactionA() {
        AccountInfo account = AccountInfo.builder().id(1).rmb_balance(-7L).usd_balance(1L).build();
        accountService.pay(account);
    }

    public void confirmMethod() {
        log.info("confirm method invoked");
    }

    public void cancelMethod() {
        log.info("cancel method invoked");
    }
}
