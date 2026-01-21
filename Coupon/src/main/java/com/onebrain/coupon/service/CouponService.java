package com.onebrain.coupon.service;

import com.onebrain.coupon.model.Coupon;
import lombok.SneakyThrows;

public interface CouponService {
    Coupon create(Coupon coupon);

    @SneakyThrows
    void delete(Long id);
}
