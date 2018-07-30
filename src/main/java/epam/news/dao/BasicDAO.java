package epam.news.dao;

import epam.news.model.entity.Basic;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

public interface BasicDAO<T extends Basic> {
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    List<T> read();

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void update(T model);

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void delete(T model);

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void create(T model);

}
