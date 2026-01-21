package com.onebrain.coupon.service;

import static org.junit.jupiter.api.Assertions.*;

import com.onebrain.coupon.exception.CodeMAXSizeException;
import com.onebrain.coupon.exception.DiscountValidException;
import com.onebrain.coupon.exception.ExpirationDateException;
import com.onebrain.coupon.model.Coupon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class CouponUseCaseMockitoTest {

    @Spy
    private Coupon coupon;

    @Test
    @DisplayName("Sucesso: Deve validar código alfanumérico corretamente")
    void shouldValidateCodeSuccess() {
        coupon.setCode("PR@MO10");

        assertDoesNotThrow(() -> coupon.validateCode());
    }

    @Test
    @DisplayName("Erro: Deve falhar quando o código limpo for menor que 6")
    void shouldFailWhenCodeShort() {
        coupon.setCode("A#1");

        assertThrows(CodeMAXSizeException.class, () -> coupon.validateCode());
    }

    @Test
    @DisplayName("Erro: Deve falhar quando o desconto for menor que 0.5")
    void shouldFailWhenDiscountInvalid() {
        coupon.setDiscountValue(new BigDecimal("0.1"));

        assertThrows(DiscountValidException.class, () -> coupon.isValidDiscount());
    }

    @Test
    @DisplayName("Erro: Deve falhar quando a data for no passado")
    void shouldFailWhenDateInPast() {
        coupon.setExpirationDate(LocalDate.now().minusDays(5));

        assertThrows(ExpirationDateException.class, () -> coupon.isValidDate());
    }

    @Test
    @DisplayName("Sucesso: Validação de data futura")
    void shouldValidateDateSuccess() {
        coupon.setExpirationDate(LocalDate.now().plusDays(1));

        assertDoesNotThrow(() -> coupon.isValidDate());
    }


}
