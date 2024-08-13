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
package me.zhengjie.gen.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import java.sql.Timestamp;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* @description /
* @author ZhangFengming
* @date 2024-05-10
**/
@Data
@TableName("cloud_pc")
public class CloudPc implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "时间")
    private Timestamp time;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "归属区县")
    private String county;

    @ApiModelProperty(value = "问题分类")
    private String problemCategory;

    @ApiModelProperty(value = "支撑进度")
    private String technicalSupportProgress;

    @ApiModelProperty(value = "是否解决")
    private String isResolved;

    @ApiModelProperty(value = "支撑状态")
    private String supportState;

    public void copy(CloudPc source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
