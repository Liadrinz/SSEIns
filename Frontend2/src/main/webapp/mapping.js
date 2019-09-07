var options = {
    sex: [
        {
            value: '男',
            label: '男'
        },
        {
            value: '女',
            label: '女'
        }
    ],
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
    ],
    status: [
        {
            value: '本科生在读',
            label: '本科生在读'
        },
        {
            value: '硕士在读',
            label: '硕士在读'
        },
        {
            value: '博士在读',
            label: '博士在读'
        },
        {
            value: '已工作',
            label: '已工作'
        },
        {
            value: '已出国',
            label: '已出国'
        },
        {
            value: '已退休',
            label: '已退休'
        },
        {
            value: '已去世',
            label: '已去世'
        }
    ],
    provinces: [
        {
            value: '北京市',
            label: '北京市'
        },
        {
            value: '天津市',
            label: '天津市'
        },
        {
            value: '上海市',
            label: '上海市'
        },
        {
            value: '重庆市',
            label: '重庆市'
        },
        {
            value: '河北省',
            label: '河北省'
        },
        {
            value: '山西省',
            label: '山西省'
        },
        {
            value: '辽宁省',
            label: '辽宁省'
        },
        {
            value: '吉林省',
            label: '吉林省'
        },
        {
            value: '黑龙江省',
            label: '黑龙江省'
        },
        {
            value: '江苏省',
            label: '江苏省'
        },
        {
            value: '浙江省',
            label: '浙江省'
        },
        {
            value: '安徽省',
            label: '安徽省'
        },
        {
            value: '福建省',
            label: '福建省'
        },
        {
            value: '江西省',
            label: '江西省'
        },
        {
            value: '山东省',
            label: '山东省'
        },
        {
            value: '河南省',
            label: '河南省'
        },
        {
            value: '湖北省',
            label: '湖北省'
        },
        {
            value: '湖南省',
            label: '湖南省'
        },
        {
            value: '广东省',
            label: '广东省'
        },
        {
            value: '海南省',
            label: '海南省'
        },
        {
            value: '四川省',
            label: '四川省'
        },
        {
            value: '贵州省',
            label: '贵州省'
        },
        {
            value: '云南省',
            label: '云南省'
        },
        {
            value: '陕西省',
            label: '陕西省'
        },
        {
            value: '甘肃省',
            label: '甘肃省'
        },
        {
            value: '青海省',
            label: '青海省'
        },
        {
            value: '台湾省',
            label: '台湾省'
        },
        {
            value: '内蒙古自治区',
            label: '内蒙古自治区'
        },
        {
            value: '广西壮族自治区',
            label: '广西壮族自治区'
        },
        {
            value: '西藏自治区',
            label: '西藏自治区'
        },
        {
            value: '宁夏回族自治区',
            label: '宁夏回族自治区'
        },
        {
            value: '新疆维吾尔自治区',
            label: '新疆维吾尔自治区'
        }
    ]
}

var pkMapping = {
    "student": "SId",
    "klass": "KId",
    "notice": "NId",
    "comment": "CId",
    "album": "albId",
    "filesse": "FId"
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
    },
    "inNotice": (obj) => {
        if (obj === null) return "notice";
        return {
            "id": obj["NId"],
            "name": obj["text"]
        }
    },
    "inStudent": (obj) => {
        if (obj === null) return "student";
        return {
            "id": obj["SId"],
            "name": obj["name"]
        }
    },
    "inAlbum": (obj) => {
        if (obj === null) return "album";
        return {
            "id": obj["albId"],
            "name": obj["albTitle"]
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
    "password": "密码",
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
    'albIntro': '相册简介',
    "type": "相册类型",
    "grade": "所属年级",
    "inKlass": "所属班级",
    "photos": "照片"
}

var klassMapping = {
    "grade": "年级",
    "num": "班级号",
}

var filesseMapping = {
    "url": "图片",
    "type": "文件类型",
    "inNotice": "动态照片",
    "inAlbum": "相册照片",
    "inStudent": "用户头像"
}