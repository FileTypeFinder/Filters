/*
 *    Copyright 2014 - 2016 Yannick Watier
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.filetypefinder.filters;

import org.filetypefinder.core.Filter;
import org.filetypefinder.core.FilterProperties;

import java.util.Arrays;

/**
 * Created by Yannick on 3/29/2016.
 */

@FilterProperties(mimeType = "application/x-java-class")
public class ClassImpl implements Filter {

    private static byte header[] = {
            (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE
    };

    public boolean detect(byte[] bytes) {
        return !(bytes == null || bytes.length < 4) && Arrays.equals(new byte[] {bytes[0], bytes[1], bytes[2], bytes[3]}, header);
    }
}