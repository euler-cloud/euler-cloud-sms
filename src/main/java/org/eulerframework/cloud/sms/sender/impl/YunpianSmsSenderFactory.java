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
import org.eulerframework.cloud.sms.sender.SmsSenderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnClass(YunpianClient.class)
public class YunpianSmsSenderFactory implements SmsSenderFactory {
    private SmsSender smsSender = null;

    @Autowired
    private YunpianConfig yunpianConfig;

    @Override
    public SmsSender getSmsSender() {
        if(this.smsSender != null) {
            return this.smsSender;
        }

        synchronized (this) {
            if(this.smsSender != null) {
                return this.smsSender;
            }

            this.smsSender = new YunpianSmsSender(this.yunpianConfig);
            return this.smsSender;
        }
    }
}
