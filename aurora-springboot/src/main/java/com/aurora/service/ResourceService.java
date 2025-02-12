package com.aurora.service;

import com.aurora.dto.LabelOptionDTO;
import com.aurora.dto.ResourceDTO;
import com.aurora.entity.Resource;
import com.aurora.vo.ConditionVO;
import com.aurora.vo.ResourceVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    void importSwagger();

    void saveOrUpdateResource(ResourceVO resourceVO);

    void deleteResource(Integer resourceId);

    List<ResourceDTO> listResources(ConditionVO conditionVO);

    List<LabelOptionDTO> listResourceOption();
}
