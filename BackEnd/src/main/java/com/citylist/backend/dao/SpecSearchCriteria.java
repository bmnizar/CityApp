package com.citylist.backend.dao;

import com.citylist.backend.shared.SearchCriteriaUI;

/**
 ** @BMN 2021
 **
 **/

public class SpecSearchCriteria {

	private SearchCriteriaUI searchCriteriaUI;
	private boolean orPredicate;

	public SpecSearchCriteria() {

	}

	public SpecSearchCriteria(SearchCriteriaUI searchCriteriaUI) {
		super();
		this.searchCriteriaUI = searchCriteriaUI;
	}

	public SpecSearchCriteria(final String orPredicate, SearchCriteriaUI searchCriteriaUI) {
		super();
		this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);
		this.searchCriteriaUI = searchCriteriaUI;
	}

	public SpecSearchCriteria(String key, String operation, String prefix, String value, String suffix) {
		SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
		if (op != null) {
			if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
				final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
				final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

				if (startWithAsterisk && endWithAsterisk) {
					op = SearchOperation.CONTAINS;
				} else if (startWithAsterisk) {
					op = SearchOperation.ENDS_WITH;
				} else if (endWithAsterisk) {
					op = SearchOperation.STARTS_WITH;
				}
			}
		}

		this.searchCriteriaUI.setKey(key);
		this.searchCriteriaUI.setSearchOperationString(op.toString());
		this.searchCriteriaUI.setValue(value);
	}

	public SearchCriteriaUI getSearchCriteriaUI() {
		return searchCriteriaUI;
	}

	public void setSearchCriteriaUI(SearchCriteriaUI searchCriteriaUI) {
		this.searchCriteriaUI = searchCriteriaUI;
	}

	public boolean isOrPredicate() {
		return orPredicate;
	}

	public void setOrPredicate(boolean orPredicate) {
		this.orPredicate = orPredicate;
	}

}