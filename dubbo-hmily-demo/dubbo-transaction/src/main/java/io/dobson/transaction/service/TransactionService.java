package io.dobson.transaction.service;

import org.dromara.hmily.annotation.Hmily;

public interface TransactionService {
    @Hmily
    void pay();
}
