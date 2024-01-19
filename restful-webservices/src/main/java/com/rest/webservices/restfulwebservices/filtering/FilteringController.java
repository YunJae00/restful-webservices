package com.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// 정적 필터링, 동적 필터링 예를들어 비밀번호같은걸 빼주고 싶다

	// 정적필터링은 그냥 빼느걸 정해주는거, 동적은 상황에 따라 빼주는걸 정하는거
	// 정적 @JsonIgnore 붙여주면 안옴
//	@GetMapping("/filtering")
//	public SomeBean filtering() {
//		return new SomeBean("value1", "value2", "value3");
//	}
//
//	// 예를들어 list를 반환해주고싶다
//	@GetMapping("/filtering-list")
//	public List<SomeBean> filteringList() {
//		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value11", "value22", "value33"));
//	}
//	

	// 동적 필터링 rest api별로 빼주고 싶은게 다를 수 있음
	@GetMapping("/filtering") // field2
	public MappingJacksonValue filtering() {

		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		// someBean 을 필터링 하려고 MappingJacksonValue 를 사용
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		// 필터를 설정 가능

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2"); // field1 3만
																											// 출력하게 해줌
		// FilterProvider 여러 필터를 정의할 수 있게 해줌
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		mappingJacksonValue.setFilters(filters);
		// 그리고 @JsonFilter를 bean에 적어줘야 인식가능
		return mappingJacksonValue;
	}

	@GetMapping("/filtering-list") // field2, field3
	public MappingJacksonValue filteringList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		mappingJacksonValue.setFilters(filters);

		return mappingJacksonValue;
	}

}
