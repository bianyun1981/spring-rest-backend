package com.silentcloud.springrest.service.impl.mapper.lib;

import com.silentcloud.springrest.model.entity.lib.Publisher;
import com.silentcloud.springrest.service.api.dto.lib.PublisherDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
 * 出版社 Mapper
 *
 * @author bianyun
 */
@Mapper
public interface PublisherMapper extends BaseMapper<Long, Publisher, PublisherDto> {
}

