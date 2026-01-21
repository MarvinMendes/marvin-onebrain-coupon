package com.onebrain.coupon.service.impl;

import com.onebrain.coupon.exception.CouponNotFoundException;
import com.onebrain.coupon.model.Coupon;
import com.onebrain.coupon.repository.CouponRepository;
import com.onebrain.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public Coupon create(Coupon coupon) {
        coupon.isValidDate();
        coupon.isValidDiscount();
        coupon.validateCode();
        return couponRepository.save(coupon);
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        couponRepository.findById(id).ifPresentOrElse(
                coupon -> {
                    coupon.isEnable();
                    coupon.setEnable(Boolean.FALSE);
                    couponRepository.save(coupon);
                }, CouponServiceImpl::handleNotFound);

    }

    private static void handleNotFound() throws CouponNotFoundException {
        throw new CouponNotFoundException("Para o ID informado não foi encontrado um coupon.");
    }
}
