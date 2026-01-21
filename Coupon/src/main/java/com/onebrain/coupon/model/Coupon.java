package com.onebrain.coupon.model;

import com.onebrain.coupon.exception.CodeMAXSizeException;
import com.onebrain.coupon.exception.CouponDeletedException;
import com.onebrain.coupon.exception.DiscountValidException;
import com.onebrain.coupon.exception.ExpirationDateException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_COUPON")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;

    private BigDecimal discountValue;

    private LocalDate expirationDate;

    @Builder.Default
    private Boolean enable = Boolean.TRUE;

    public void isEnable() {
        if(Boolean.FALSE.equals(this.enable)) {
            throw new CouponDeletedException("O coupon informado já está deletado.");
        }
    }

    public void isValidDate() {
        if(this.expirationDate.isBefore(LocalDate.now())) {
            throw new ExpirationDateException("A data informada não pode estar no passado.");
        }
    }

    public void isValidDiscount() {
        if(this.discountValue.compareTo(BigDecimal.valueOf(0.5)) < 0) {
            throw new DiscountValidException("O Desconto informado deve ser maior que 0.5");
        }
    }

    public void validateCode() {
        String codeWithoutSpecialChar = this.code.replaceAll("[^a-zA-Z0-9]", "");
        if(codeWithoutSpecialChar.length() > 6) {
            throw new CodeMAXSizeException("O código informado é maior que o permitido. O padrão para este campo é de 6 caracteres.");
        }
        if(codeWithoutSpecialChar.length() < 6) {
            throw new CodeMAXSizeException("O código informado é menor que o permitido. O padrão para este campo é de 6 caracteres.");
        }
    }



}
