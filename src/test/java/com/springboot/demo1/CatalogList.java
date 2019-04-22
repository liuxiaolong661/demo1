package com.springboot.demo1;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ul")
public class CatalogList {

//    @XStreamImplicit(itemFieldName="a")
//    private List<Catalog> catalogList;
    @XStreamImplicit(itemFieldName = "a")
    private List<Catalog> catalogList;
}
