package com.cron.utils.parser.field;

import com.cron.utils.model.field.CronFieldName;
import com.cron.utils.model.field.CronField;
import com.cron.utils.model.field.constraint.FieldConstraints;
import org.apache.commons.lang3.Validate;

import java.util.Comparator;

/*
 * Copyright 2014 jmrozanec
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Copyright 2014 jmrozanec
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Represents a cron field.
 */
public class CronParserField {
    private CronFieldName field;
    private FieldParser parser;

    /**
     * Constructor
     * @param fieldName - CronFieldName instance
     * @param constraints - FieldConstraints instance, not null.
     *                    If null, a NullPointerException will be raised.
     */
    public CronParserField(CronFieldName fieldName, FieldConstraints constraints) {
        this.field = Validate.notNull(fieldName, "CronFieldName must not be null");
        this.parser = new FieldParser(Validate.notNull(constraints, "FieldConstraints must not be null"));
    }

    /**
     * Returns field name
     * @return CronFieldName, never null
     */
    public CronFieldName getField() {
        return field;
    }

    /**
     * Parses a String cron expression
     * @param expression - cron expression
     * @return parse result as CronFieldParseResult instance - never null.
     * May throw a RuntimeException if cron expression is bad.
     */
    public CronField parse(String expression) {
        return new CronField(field, parser.parse(expression));
    }

    /**
     * Create a Comparator that compares CronField instances using CronFieldName value.
     * @return Comparator<CronField> instance, never null.
     */
    public static Comparator<CronParserField> createFieldTypeComparator() {
        return new Comparator<CronParserField>() {
            @Override
            public int compare(CronParserField o1, CronParserField o2) {
                return o1.getField().getOrder() - o2.getField().getOrder();
            }
        };
    }
}
