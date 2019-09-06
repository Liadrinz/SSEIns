var options = {
    relationship: [
        {
            value: '单身',
            label: '单身'
        },
        {
            value: '已订婚',
            label: '已订婚'
        },
        {
            value: '未婚',
            label: '未婚'
        },
        {
            value: '已婚',
            label: '已婚'
        },
        {
            value: '有同性伴侣',
            label: '有同性伴侣'
        },
        {
            value: '有异性伴侣',
            label: '有异性伴侣'
        },
        {
            value: '交往中，但保留交友空间',
            label: '交往中，但保留交友空间'
        },
    ]
}

var pkMapping = {
    "student": "SId",
    "klass": "KId",
    "notice": "NId",
    "comment": "CId",
    "album": "albId"
}

var fkMapping = {
    "klass": (obj) => {
        if (obj === null) return "klass";
        return {
            "id": obj["KId"],
            "name": obj["grade"] + " " + obj["num"]
        }
    },
    "inKlass": (obj) => {
        if (obj === null) return "klass";
        return {
            "id": obj["KId"],
            "name": obj["grade"] + " " + obj["num"]
        }
    },
    "publisher": (obj) => {
        if (obj === null) return "student";
        return {
            "id": obj["SId"],
            "name": obj["name"]
        }
    },
    "commenter": (obj) => {
        if (obj === null) return "student";
        return {
            "id": obj["SId"],
            "name": obj["name"]
        }
    },
    "notice": (obj) => {
        if (obj === null) return "notice";
        return {
            "id": obj["NId"],
            "name": obj["text"]
        }
    }
}

var studentMapping = {
    "avatar": "头像",
    "buptId": "学号",
    "klass": "班级",
    "location": "所在地",
    "company": "所在公司",
    "email": "邮箱",
    "ethnic": "民族",
    "intro": "个人简介",
    "klass": "班级",
    "name": "姓名",
    "sex": "性别",
    "status": "当前状态",
    "tel": "手机",
    "birthday": "生日",
    "relationship": "情感状况",
};

var noticeMapping = {
    "publishTime": "发布时间",
    "publisher": "发布者",
    "text": "内容",
    "photos": "照片"
}

var commentMapping = {
    "commentTime": "发表时间",
    "commenter": "评论者",
    "content": "内容",
    "notice": "所属动态"
}

var albumMapping = {
    "albTitle": "相册标题",
    "type": "相册类型",
    "grade": "所属年级",
    "inKlass": "所属班级",
    "photos": "照片"
}

var klassMapping = {
    "grade": "年级",
    "num": "班级号",
}
