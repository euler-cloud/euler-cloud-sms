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
package org.eulerframework.cloud.sms.entity;

import org.eulerframework.web.core.base.entity.NonIDEntity;

import javax.persistence.Id;

public class SmsTemplate extends NonIDEntity<SmsTemplate, String> {
    @Id
    private String id;
    private String sign;
    private String template;
    private String isp;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(SmsTemplate o) {
        return this.getId().compareTo(o.getId());
    }
}
