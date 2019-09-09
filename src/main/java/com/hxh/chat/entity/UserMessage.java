package com.hxh.chat.entity;

import lombok.Data;

/**
 * @Author: H_xinghai
 * @Date: 2019/9/6 16:31
 * @Description:
 */
@Data
public class UserMessage {

    private String userId;

    private String userName;

    private String toUserId;

    private String toUserName;

    private String contextType;

    private String context;

    public UserMessage(String userName, String contextType) {
        this.userName = userName;
        this.contextType = contextType;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", contextType='" + contextType + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
