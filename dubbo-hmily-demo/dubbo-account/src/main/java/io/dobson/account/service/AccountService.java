package io.dobson.account.service;

import io.dobson.account.entity.AccountInfo;
import org.dromara.hmily.annotation.Hmily;

public interface AccountService {
    @Hmily
    boolean pay(AccountInfo accountInfo);
}
