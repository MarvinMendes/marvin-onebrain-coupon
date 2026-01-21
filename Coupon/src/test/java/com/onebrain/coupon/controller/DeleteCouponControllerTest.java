package com.onebrain.coupon.controller;

import com.onebrain.coupon.model.Coupon;
import com.onebrain.coupon.repository.CouponRepository;
import com.onebrain.coupon.service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteCouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponService couponService;

    private Long existingCouponId;

    @BeforeEach
    void setUp() {
        couponRepository.deleteAll();

        Coupon coupon = Coupon.builder()
                .code("PROMO6")
                .discountValue(new BigDecimal("20.00"))
                .expirationDate(LocalDate.now().plusDays(30))
                .build();

        Coupon savedCoupon = couponRepository.save(coupon);
        this.existingCouponId = savedCoupon.getId();
    }

    @Test
    @DisplayName("Should return 200 OK when deleting an existing coupon")
    void shouldReturn200WhenDeletingExistingCoupon() throws Exception {
        mockMvc.perform(delete("/coupon/{id}", existingCouponId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return 304 Not Modified when coupon is already deleted or not found")
    void shouldReturn304WhenCouponNotFoundOrAlreadyDeleted() throws Exception {
        couponService.delete(existingCouponId);

        mockMvc.perform(delete("/coupon/{id}", existingCouponId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotModified());
    }

}
