package me.zhyd.justauth;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/6/24 13:02
 * @since 1.0.0
 */
public enum JustAuthPlatformInfo {

    /**
     * 平台
     */
    GITEE("Gitee", "https://docs.justauth.whnb.wang/#/oauth/gitee", "", "v1.0.1", false),
    BAIDU("百度", "", "", "v1.0.1", false),
    CODING("coding", "https://docs.justauth.whnb.wang/#/oauth/coding", "", "v1.0.1", false),
    CSDN("CSDN", "", "", "v1.0.1", false),
    DINGTALK("钉钉", "", "", "v1.0.1", false),
    GITHUB("Github", "https://docs.justauth.whnb.wang/#/oauth/github", "", "v1.0.1", false),
    OSCHINA("开源中国", "", "", "v1.0.1", false),
    ALIPAY("支付宝", "https://docs.justauth.whnb.wang/#/oauth/alipay", "", "v1.0.1", false),
    WEIBO("微博", "https://docs.justauth.whnb.wang/#/oauth/weibo", "", "v1.0.1", false),

    DOUYIN("抖音", "", "", "v1.4.0", false),
    ELEME("饿了么", "", "", "v1.12.0", false),
    FACEBOOK("Facebook", "", "", "v1.3.0", false),
    GITLAB("Gitlab", "", "", "v1.11.0", false),
    GOOGLE("Google", "", "", "v1.3.0", false),
    HUAWEI("华为", "", "", "v1.10.0", false),
    JD("京东", "", "", "v1.15.1", false),
    KUJIALE("酷家乐", "https://docs.justauth.whnb.wang/#/oauth/kujiale", "", "v1.11.0", false),
    LINKEDIN("领英", "", "", "v1.4.0", false),
    MEITUAN("美团", "", "", "v1.12.0", false),
    MICROSOFT("微软", "", "", "v1.5.0", false),
    MI("小米", "", "", "v1.5.0", false),
    PINTEREST("Pinterest", "", "", "v1.9.0", false),
    QQ("QQ", "https://docs.justauth.whnb.wang/#/oauth/qq", "", "v1.1.0", false),
    RENREN("人人", "", "", "v1.9.0", false),
    STACK_OVERFLOW("Stack Overflow", "", "", "v1.9.0", false),
    TAOBAO("淘宝", "", "", "v1.2.0", false),
    TEAMBITION("Teambition", "", "", "v1.9.0", false),
    WECHAT_ENTERPRISE("企业微信二维码登录", "", "", "v1.10.0", false),
    WECHAT_MP("微信公众平台", "", "", "v1.14.0", false),
    WECHAT_OPEN("微信开放平台", "https://docs.justauth.whnb.wang/#/oauth/wechat_open", "", "v1.1.0", false),
    TOUTIAO("今日头条", "", "", "v1.6.0-beta", false),
    TWITTER("推特", "https://docs.justauth.whnb.wang/#/oauth/twitter", "", "v1.13.0", false),
    ALIYUN("阿里云", "https://docs.justauth.whnb.wang/#/oauth/aliyun", "", "v1.15.5", false),
    MYGITLAB("自定义的Gitlab", "", "", "v1.13.0", false),
    XMLY("喜马拉雅", "", "", "v1.15.9", true),
    WECHAT_ENTERPRISE_WEB("企业微信网页登录", "", "", "v1.15.9", true),
    FEISHU("飞书", "", "", "1.15.9", true),
    ;

    // 平台名
    private String name;
    // 帮助文档
    private String readme;
    // 官网api文档
    private String apiDoc;
    // 集成该平台的 版本
    private String since;
    private boolean latest;

    JustAuthPlatformInfo(String name, String readme, String apiDoc, String since, boolean latest) {
        this.name = name;
        this.readme = readme;
        this.apiDoc = apiDoc;
        this.since = since;
        this.latest = latest;
    }

    public static List<Map<String, Object>> getPlatformInfos() {
        List<Map<String, Object>> list = new LinkedList<>();
        Map<String, Object> map = null;
        JustAuthPlatformInfo[] justAuthPlatformInfos = JustAuthPlatformInfo.values();
        for (JustAuthPlatformInfo justAuthPlatformInfo : justAuthPlatformInfos) {
            map = new HashMap<>();
            map.put("name", justAuthPlatformInfo.getName());
            map.put("readme", justAuthPlatformInfo.getReadme());
            map.put("apiDoc", justAuthPlatformInfo.getApiDoc());
            map.put("since", justAuthPlatformInfo.getSince());
            map.put("enname", justAuthPlatformInfo.name().toLowerCase());
            map.put("isLatest", justAuthPlatformInfo.isLatest());
            list.add(map);
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public String getReadme() {
        return readme;
    }

    public String getApiDoc() {
        return apiDoc;
    }

    public String getSince() {
        return since;
    }

    public boolean isLatest() {
        return latest;
    }
}
