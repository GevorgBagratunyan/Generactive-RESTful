package com.generactive.service.specification;

import com.generactive.service.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {

    private SearchCriteria searchCriteria;

    public GenericSpecification(SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        String argument = searchCriteria.getArgument();

        //Search argument case
        if(argument!=null) {
            switch (searchCriteria.getSearchOperation()) {
                case EQUALITY:
                    predicates.add(criteriaBuilder.equal(root.get(searchCriteria.getKey()), argument));
                case GREATER_THAN:
                    predicates.add(criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), (Comparable) argument));
                case IN:
                    predicates.add(root.get(searchCriteria.getKey()).in(argument));
                case LIKE:
                    predicates.add(criteriaBuilder.like(root.get(searchCriteria.getKey()), argument));
                case LESS_THAN:
                    predicates.add(criteriaBuilder.lessThan(root.get(searchCriteria.getKey()), (Comparable) argument));
                case NEGATION:
                    predicates.add(criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), argument));
            }
        }



        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
