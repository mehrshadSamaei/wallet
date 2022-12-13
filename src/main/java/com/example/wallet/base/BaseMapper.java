package com.example.wallet.base;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {
    E convertDToE(D d);

    D convertEToD(E e);

    List<E> convertListDToE(List<D> dList);

    List<D> convertListEToD(List<E> eList);
}
