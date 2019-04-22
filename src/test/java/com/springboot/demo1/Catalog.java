package com.springboot.demo1;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("a")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = { "value" })
public class Catalog {

//    private CatalogType catalogType;
    @XStreamAsAttribute
    @XStreamAlias("href")
    private String url;

    private String value;
}
