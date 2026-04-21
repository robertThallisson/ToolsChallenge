package com.toolsjavachallenge.entity;


import com.toolsjavachallenge.entity.enums.TipoPagamento;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormaPagamento {


    private TipoPagamento tipoPagamento;

    private Integer parcelas;

}
