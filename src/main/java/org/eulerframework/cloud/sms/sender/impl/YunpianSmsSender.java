/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eulerframework.cloud.sms.sender.impl;

import com.yunpian.sdk.YunpianClient;
import org.eulerframework.cloud.sms.conf.YunpianConfig;
import org.eulerframework.cloud.sms.sender.SmsSender;
import org.eulerframework.common.base.log.LogSupport;

import java.util.Map;
import java.util.Set;

public class YunpianSmsSender extends LogSupport implements SmsSender {
    private YunpianConfig yunpianConfig;

    public YunpianSmsSender(YunpianConfig yunpianConfig) {
        this.yunpianConfig = yunpianConfig;
    }

    @Override
    public void sendSms(String mobile, String templateId, Map<String, String> params) {
        String template = this.yunpianConfig.getTemplates().get(templateId);

        String msg = template;
        Set<String> paramNames = params.keySet();
        for(String paramName : paramNames) {
            msg = msg.replace("#" + paramName + "#", params.get(paramName));
        }

        this.logger.debug("mobile: {}, msg: {}", mobile, msg);
        this.sendSms(mobile, msg);
    }

    private void sendSms(String mobile, String msg) {
        YunpianClient clnt = null;
        try {
            // 初始化clnt,使用单例方式
            clnt = new YunpianClient(this.yunpianConfig.getApiKey()).init();

            // 发送短信API
            Map<String, String> param = clnt.newParam(2);
            param.put(YunpianClient.MOBILE, mobile);
            param.put(YunpianClient.TEXT, msg);
            clnt.sms().single_send(param);
            // Result<SmsSingleSend> r = clnt.sms().single_send(param);
            // 获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

            // 账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().*
            // 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().*
            // 隐私通话:clnt.call().*

        } finally {
            if (clnt != null) {
                // 释放clnt
                clnt.close();
            }
        }
    }
}
