package cn.moonshot.platform;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.moonshot.platform.util.Message;
import cn.moonshot.platform.util.MoonshotAiUtils;
import cn.moonshot.platform.util.RoleEnum;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ApiTest {

    @Test
    void getModelList() {
        System.out.println(MoonshotAiUtils.getModelList());
    }

    @Test
    void uploadFile() {
        System.out.println(MoonshotAiUtils.uploadFile(FileUtil.file("/C:\\Users\\USER520580\\Desktop\\金华云电脑支撑.xlsx")));
    }

    @Test
    void getFileList() {
        System.out.println(MoonshotAiUtils.getFileList());
    }

    @Test
    void deleteFile() {
        System.out.println(MoonshotAiUtils.deleteFile("cq2g0v2tnn0t4t6mdm90"));
        System.out.println(MoonshotAiUtils.getFileList());
    }

    @Test
    void getFileContent() {
        System.out.println(MoonshotAiUtils.getFileContent("cq2g0v2tnn0t4t6mdm90"));
    }

    @Test
    void getFileDetail() {
        System.out.println(MoonshotAiUtils.getFileDetail("cq2g0v2tnn0t4t6mdm90"));
    }

    @Test
    void estimateTokenCount() {
        List<Message> messages = CollUtil.newArrayList(
                new Message(RoleEnum.system.name(), "你是kimi AI"),
                new Message(RoleEnum.user.name(), "hello")
        );
        System.out.println(MoonshotAiUtils.estimateTokenCount("moonshot-v1-8k", messages));
    }

    @Test
    void chat(){
        List<Message> messages = CollUtil.newArrayList(
                new Message(RoleEnum.system.name(), "你是kimi AI"),
                new Message(RoleEnum.user.name(), "刚才上传的售后支撑台账中，客户操作不当的占比最大，请深入分析这个问题类别下的台账记录，给出相关建议")
        );
        MoonshotAiUtils.chat("moonshot-v1-8k",messages);
    }

    @Test
    void analysis(){
        String file = MoonshotAiUtils.uploadFile(FileUtil.file("/C:\\Users\\USER520580\\Desktop\\金华云电脑支撑1.xlsx"));
        JSONObject jsonObject = JSONObject.parseObject(file);
        String fileId = jsonObject.getString("id");
        String fileContent = MoonshotAiUtils.getFileContent(fileId);

        List<Message> messages = CollUtil.newArrayList(
                new Message(RoleEnum.system.name(), fileContent),
                new Message(RoleEnum.user.name(), "刚才上传的售后支撑台账中，客户操作不当的占比最大，请深入分析这个问题类别下的台账记录，给出相关建议")
        );

//        MoonshotAiUtils.chatAnalysis("moonshot-v1-128k",messages);
        MoonshotAiUtils.chat("moonshot-v1-128k",messages);
//        System.out.println(MoonshotAiUtils.chatAnalysis("moonshot-v1-128k",messages));
    }

}
