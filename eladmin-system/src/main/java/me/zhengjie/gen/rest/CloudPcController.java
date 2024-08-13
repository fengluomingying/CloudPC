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
package me.zhengjie.gen.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.gen.domain.CloudPc;
import me.zhengjie.gen.service.CloudPcService;
import me.zhengjie.gen.domain.vo.CloudPcQueryCriteria;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.utils.PageResult;

/**
* @author ZhangFengming
* @date 2024-05-10
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "云电脑支撑接口生成管理")
@RequestMapping("/api/cloudPc")
public class CloudPcController {

    private final CloudPcService cloudPcService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('cloudPc:list')")
    public void exportCloudPc(HttpServletResponse response, CloudPcQueryCriteria criteria) throws IOException {
        cloudPcService.download(cloudPcService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询云电脑支撑接口生成")
    @ApiOperation("查询云电脑支撑接口生成")
    @PreAuthorize("@el.check('cloudPc:list')")
    public ResponseEntity<PageResult<CloudPc>> queryCloudPc(CloudPcQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(cloudPcService.queryAll(criteria,page),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增云电脑支撑接口生成")
    @ApiOperation("新增云电脑支撑接口生成")
    @PreAuthorize("@el.check('cloudPc:add')")
    public ResponseEntity<Object> createCloudPc(@Validated @RequestBody CloudPc resources){
        cloudPcService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改云电脑支撑接口生成")
    @ApiOperation("修改云电脑支撑接口生成")
    @PreAuthorize("@el.check('cloudPc:edit')")
    public ResponseEntity<Object> updateCloudPc(@Validated @RequestBody CloudPc resources){
        cloudPcService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除云电脑支撑接口生成")
    @ApiOperation("删除云电脑支撑接口生成")
    @PreAuthorize("@el.check('cloudPc:del')")
    public ResponseEntity<Object> deleteCloudPc(@RequestBody List<Long> ids) {
        cloudPcService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}