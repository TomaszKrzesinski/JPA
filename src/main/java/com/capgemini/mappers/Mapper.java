package com.capgemini.mappers;

import java.util.List;
import java.util.Set;

public interface Mapper<E, D> {
    D mapToTO(E entity);

    E mapToEntity(D to);

    List<D> mapListToTO(List<E> entityList);

    List<E> mapListToEntity(List<D> toList);

    Set<D> mapSetToTO(Set<E> entitySet);

    Set<E> mapSetToEntity(Set<D> toSet);

}
