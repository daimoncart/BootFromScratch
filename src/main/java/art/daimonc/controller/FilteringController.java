package art.daimonc.controller;

import art.daimonc.bean.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path="/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean newBean = new SomeBean("value1", "value2", "value3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("NewBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(newBean);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(path="/filtering-list")
    public List<SomeBean> retrieveSomeBeanList(){
        return Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
    }
}
