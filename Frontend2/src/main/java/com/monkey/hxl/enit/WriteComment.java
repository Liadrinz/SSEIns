/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.hxl.enit;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author ahuan
 */
public class WriteComment {
    @JSONField(name="notice")
    protected String notice;
    @JSONField(name="content")
    protected String content;
    @JSONField(name="commenter")
    protected String commenter;

    public WriteComment() {
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
    
}
