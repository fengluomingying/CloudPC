/*
*  Copyright 2019-2023 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.gen.service.impl;

import me.zhengjie.gen.domain.CloudPc;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.gen.service.CloudPcService;
import me.zhengjie.gen.domain.vo.CloudPcQueryCriteria;
import me.zhengjie.gen.mapper.CloudPcMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.zhengjie.utils.PageUtil;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import me.zhengjie.utils.PageResult;

/**
* @description 服务实现
* @author ZhangFengming
* @date 2024-05-10
**/
@Service
@RequiredArgsConstructor
public class CloudPcServiceImpl extends ServiceImpl<CloudPcMapper, CloudPc> implements CloudPcService {

    private final CloudPcMapper cloudPcMapper;

    @Override
    public PageResult<CloudPc> queryAll(CloudPcQueryCriteria criteria, Page<Object> page){
        return PageUtil.toPage(cloudPcMapper.findAll(criteria, page));
    }

    @Override
    public List<CloudPc> queryAll(CloudPcQueryCriteria criteria){
        return cloudPcMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CloudPc resources) {
        save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CloudPc resources) {
        CloudPc cloudPc = getById(resources.getId());
        cloudPc.copy(resources);
        saveOrUpdate(cloudPc);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<Long> ids) {
        removeBatchByIds(ids);
    }

    @Override
    public void download(List<CloudPc> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CloudPc cloudPc : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("时间", cloudPc.getTime());
            map.put("客户名称", cloudPc.getCustomerName());
            map.put("归属区县", cloudPc.getCounty());
            map.put("问题分类", cloudPc.getProblemCategory());
            map.put("支撑进度", cloudPc.getTechnicalSupportProgress());
            map.put("是否解决", cloudPc.getIsResolved());
            map.put("支撑状态", cloudPc.getSupportState());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}