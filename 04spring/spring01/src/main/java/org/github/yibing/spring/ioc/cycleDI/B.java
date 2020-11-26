package org.github.yibing.spring.ioc.cycleDI;

import org.springframework.stereotype.Service;

@Service
public class B {
    public B(C c) {
    }
}
