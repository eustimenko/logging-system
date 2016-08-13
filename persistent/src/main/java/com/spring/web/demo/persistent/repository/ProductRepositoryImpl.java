package com.spring.web.demo.persistent.repository;

import com.spring.web.demo.persistent.entity.Product;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductCustomRepository {

    private final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    public List<Product> findByParameters(Product params) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        final Root<Product> from = cq.from(Product.class);

        try {
            final List<Predicate> predicates = createPredicatesByParam(cb, from, params);
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
            }
        } catch (RuntimeException e) {
            logger.error("Criteria query build error", e);
            return Collections.emptyList();
        }

        cq.select(from);

        return em.createQuery(cq).getResultList();
    }

    private List<Predicate> createPredicatesByParam(CriteriaBuilder cb, Root<Product> from, Product param) {
        final List<Predicate> predicates = new ArrayList<>();

        if (param.getId() != null) {
            predicates.add(cb.equal(from.<Long>get("id"), param.getId()));
        }

        if (!StringUtils.isEmpty(param.getTitle())) {
            predicates.add(like(cb, from.<String>get("title"), param.getTitle()));
        }

        if (!StringUtils.isEmpty(param.getSku())) {
            predicates.add(like(cb, from.<String>get("sku"), param.getSku()));
        }

        if (!StringUtils.isEmpty(param.getDescription())) {
            predicates.add(like(cb, from.<String>get("description"), param.getDescription()));
        }

        return predicates;
    }

    private Predicate like(CriteriaBuilder cb, Expression<String> expression, String pattern) {
        return cb.like(cb.lower(expression), "%" + pattern.toLowerCase() + "%");
    }
}
