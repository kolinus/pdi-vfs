/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.apache.commons.vfs.provider;

import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileType;

/**
 * A file name for layered files.
 *
 * @author <a href="mailto:adammurdoch@apache.org">Adam Murdoch</a>
 * @version $Revision: 804548 $ $Date: 2009-08-15 22:12:32 -0400 (Sat, 15 Aug 2009) $
 */
public class LayeredFileName extends AbstractFileName
{
    private final FileName outerUri;

    public LayeredFileName(final String scheme,
                           final FileName outerUri,
                           final String path,
                           final FileType type)
    {
        super(scheme, path, type);
        this.outerUri = outerUri;
    }

    /**
     * Returns the URI of the outer file.
     * @return The FileName.
     */
    public FileName getOuterName()
    {
        return outerUri;
    }

    /**
     * Create a FileName.
     * @param path The file URI.
     * @param type The FileType.
     * @return The FileName.
     */
    public FileName createName(String path, FileType type)
    {
        return new LayeredFileName(getScheme(), getOuterName(), path, type);
    }

    protected void appendRootUri(StringBuffer buffer, boolean addPassword)
    {
        buffer.append(getScheme());
        buffer.append(":");
        buffer.append(getOuterName().getURI());
        buffer.append("!");
    }
}
