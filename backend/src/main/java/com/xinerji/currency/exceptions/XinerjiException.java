package com.xinerji.currency.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XinerjiException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String code;
    private Object[] prmList;
    public XinerjiException(String code, String ... prmList){
        super();
        this.code = code;
        this.prmList = prmList;
    }

}
