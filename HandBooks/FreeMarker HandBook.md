#### 使用自定义宏
```bash
# 1. 定义
<#macro timeago  createdtime>
    <#assign ct = (.now?long-createdtime?long)/1000 />
    <#if ct gte 31104000> ${(ct/31104000)?int} 年前
    <#t><#elseif ct gte 2592000> ${(ct/2592000)?int} 个月前
        <#t><#elseif ct gte 86400*2> ${(ct/86400)?int} 天前
        <#t><#elseif ct gte 86400> 昨天
        <#t><#elseif ct gte 3600> ${(ct/3600)?int} 小时前
        <#t><#elseif ct gte 60> ${(ct/60)?int} 分钟前
        <#t><#elseif ct gt 0> ${ct?int} 秒前
        <#t><#else> 刚刚
    </#if>
</#macro>
# 2. 调用
<#-- 贴子创建时间 -->
<a class="last_time pull-right" href="/topic/detail/${t.id}">
    <img class="user_small_avatar" src="/bobo.jpg">
    <#-- 宏调用 -->
    <span class="last_active_time"><@timeago  createdtime = t.createdTime/></span>
</a>
```
