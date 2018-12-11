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
    private YunpianClient yunpianClient;
    private YunpianConfig yunpianConfig;

    public YunpianSmsSender(YunpianConfig yunpianConfig) {
        this.yunpianConfig = yunpianConfig;
        this.yunpianClient = new YunpianClient(this.yunpianConfig.getApiKey()).init();
    }

    @Override
    public void sendSms(String mobile, String templateId, Map<String, String> params) {
        String template = this.yunpianConfig.getTemplates().get(templateId);

        String msg = template;
        Set<String> paramNames = params.keySet();
        for(String paramName : paramNames) {
            msg = msg.replace("#" + paramName + "#", params.get(paramName));
        }

        Map<String, String> sendParam = this.yunpianClient.newParam(2);
        sendParam.put(YunpianClient.MOBILE, mobile);
        sendParam.put(YunpianClient.TEXT, msg);

        this.logger.debug("mobile: {}, msg: {}", mobile, msg);

        this.yunpianClient.sms().single_send(sendParam);
    }
}
