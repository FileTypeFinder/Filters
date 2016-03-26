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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Yannick on 2/16/2016.
 */

@FilterProperties(mimeType = "application/x-java-war", parent = "org.filetypefinder.filters.ZipImpl")
public class WarImpl implements Filter {

    public boolean detect(byte[] bytes) {
        boolean haveWebInf = false, haveMetaInf = false;

        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bytes));

        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();

            while (nextEntry != null) {
                String name = nextEntry.getName();

                if ("WEB-INF/".equalsIgnoreCase(name)) {
                    haveWebInf = true;
                }

                if ("META-INF/".equalsIgnoreCase(name)) {
                    haveMetaInf = true;
                }

                nextEntry = zipInputStream.getNextEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return haveWebInf && haveMetaInf;
    }
}
