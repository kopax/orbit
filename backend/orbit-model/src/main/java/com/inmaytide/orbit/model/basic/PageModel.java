package com.inmaytide.orbit.model.basic;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Objects;

/**
 * Some properties about pagation.
 *
 * @author Moss
 * @since September 10, 2017
 */
public class PageModel implements Serializable {

    public static final Sort DEFAULT_SORT = new Sort(Sort.Direction.DESC, "createTime");
    private static final long serialVersionUID = -744865805141356260L;
    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer DEFAULT_SIZE = 10;
    private Integer number;

    private Integer size;


    /**
     * keywords of query.
     */
    private String keywords;

    public Integer getNumber() {
        if (Objects.isNull(number)) {
            number = DEFAULT_NUMBER;
        }
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        if (Objects.isNull(size)) {
            size = DEFAULT_SIZE;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getKeywords() {
        if (null != keywords && !"".equals(keywords.trim())) {
            return String.format("%%%s%%", keywords);
        }
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Pageable toPageable() {
        return toPageable(DEFAULT_SORT);
    }

    public Pageable toPageable(Sort.Direction direction, String... properties) {
        Sort sort = new Sort(direction, properties);
        return toPageable(sort);
    }

    public Pageable toPageable(Sort sort) {
        return new PageRequest(getNumber() - 1, getSize(), sort);
    }
}
