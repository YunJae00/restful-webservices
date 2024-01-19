package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//bean 에도 적용 가능
//@JsonIgnoreProperties("field1") (정적 필터링)
@JsonFilter("SomeBeanFilter") // 괄호 안의 이름 동일해야함 (동적 필터링)
public class SomeBean {

	private String field1;

	// @JsonIgnore (정적 필터링)
	private String field2;

	private String field3;

	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public String getField2() {
		return field2;
	}

	public String getField3() {
		return field3;
	}

	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}
}
