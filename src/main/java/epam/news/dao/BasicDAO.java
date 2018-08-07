package epam.news.dao;

import epam.news.model.entity.Basic;
import epam.news.model.entity.News;

import javax.transaction.Transactional;
import java.util.List;

public interface BasicDAO<T extends Basic> {
    List<T> read();

    T update(T model);

    T delete(T model);

    T create(T model);

}
