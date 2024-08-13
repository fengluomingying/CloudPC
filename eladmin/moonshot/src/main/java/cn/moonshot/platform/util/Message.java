package cn.moonshot.platform.util;

import cn.hutool.core.lang.Assert;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Message {

    private String role;

    private String content;

}
