package com.citylist.backend.dao;

import javax.persistence.criteria.Predicate;

import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.citylist.backend.entities.City;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 ** @BMN 2021
 **
 **/
public class CitySpecification implements Specification<City> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpecSearchCriteria criteria;

	public CitySpecification(final SpecSearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SpecSearchCriteria getCriteria() {
		return criteria;
	}

//TODO: we will try to fix this duplication later on
	@Override
	public Predicate toPredicate(final Root<City> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		switch (criteria.getSearchCriteriaUI().getSearchOperationString()) {
		case "EQUALITY":
			return builder.equal(root.get(criteria.getSearchCriteriaUI().getKey()),
					criteria.getSearchCriteriaUI().getValue());

		case "LIKE":
			return builder.like(root.get(criteria.getSearchCriteriaUI().getKey()),
					criteria.getSearchCriteriaUI().getValue().toString());
		case "startsWith":
			return builder.like(root.get(criteria.getSearchCriteriaUI().getKey()),
					criteria.getSearchCriteriaUI().getValue() + "%");
		case "endsWith":
			return builder.like(root.get(criteria.getSearchCriteriaUI().getKey()),
					"%" + criteria.getSearchCriteriaUI().getValue());
		case "contains":
			return builder.like(root.get(criteria.getSearchCriteriaUI().getKey()),
					"%" + criteria.getSearchCriteriaUI().getValue() + "%");
		default:
			return null;
		}
	}

}