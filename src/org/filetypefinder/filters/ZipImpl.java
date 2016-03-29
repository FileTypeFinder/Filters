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
 * Created by Yannick on 2/16/2016.
 */

@FilterProperties(mimeType = "application/zip", childs = {"JarImpl", "WarImpl"})
public class ZipImpl implements Filter {
    public static final byte[] HEADER_ARCHIVE_MAGIC_NUMBER = {0x50, 0x4B, 0x03, 0x04};
    public static final byte[] HEADER_EMPTY_MAGIC_NUMBER = {0x50, 0x4B, 0x05, 0x06};
    public static final byte[] HEADER_SPANNED_MAGIC_NUMBER = {0x50, 0x4B, 0x07, 0x08};


    public boolean detect(byte[] bytes) {
        if (bytes.length < 4 || bytes.length < 22) { //Minimum zip file(empty) size = 22 bytes
            return false;
        }
        byte[] headers = {bytes[0], bytes[1], bytes[2], bytes[3]};

        return Arrays.equals(HEADER_ARCHIVE_MAGIC_NUMBER, headers) ||
                Arrays.equals(HEADER_EMPTY_MAGIC_NUMBER, headers) ||
                Arrays.equals(HEADER_SPANNED_MAGIC_NUMBER, headers);
    }
}
