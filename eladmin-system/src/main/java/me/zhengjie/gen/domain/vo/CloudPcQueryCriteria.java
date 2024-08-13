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
package me.zhengjie.gen.domain.vo;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

/**
* @author ZhangFengming
* @date 2024-05-10
**/
@Data
public class CloudPcQueryCriteria{
    private String customerName;
    private String county;
    private String problemCategory;
    private String isResolved;
    private String supportState;
    private List<Timestamp> time;
}