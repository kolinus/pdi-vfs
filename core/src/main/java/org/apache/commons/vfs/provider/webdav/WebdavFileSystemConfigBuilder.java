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

package org.apache.commons.vfs.provider.webdav;

import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.provider.http.HttpFileSystemConfigBuilder;

/**
 * Configuration options for WebDav.
 *
 * @author <a href="http://commons.apache.org/vfs/team-list.html">Commons VFS team</a>
 * @version $Revision: 804886 $ $Date: 2009-08-17 03:38:49 -0400 (Mon, 17 Aug 2009) $
 */
public final class WebdavFileSystemConfigBuilder extends HttpFileSystemConfigBuilder
{
    private static final WebdavFileSystemConfigBuilder BUILDER = new WebdavFileSystemConfigBuilder();

    private WebdavFileSystemConfigBuilder()
    {
        super("webdav.");
    }

    public static HttpFileSystemConfigBuilder getInstance()
    {
        return BUILDER;
    }

    /**
     * The user name to be associated with changes to the file.
     * @param opts The FileSystem options
     * @param creatorName The creator name to be associated with the file.
     */
    public void setCreatorName(FileSystemOptions opts, String creatorName)
    {
        setParam(opts, "creatorName", creatorName);
    }

    /**
     * Return the user name to be associated with changes to the file.
     * @param opts The FileSystem options
     * @return The creatorName.
     */
    public String getCreatorName(FileSystemOptions opts)
    {
        return getString(opts, "creatorName");
    }

    /**
     * Whether to use versioning.
     * @param opts The FileSystem options.
     * @param versioning true if versioning should be enabled.
     */
    public void setVersioning(FileSystemOptions opts, boolean versioning)
    {
        setParam(opts, "versioning", Boolean.valueOf(versioning));
    }

    /**
     * The cookies to add to the request.
     * @param opts The FileSystem options.
     * @return true if versioning is enabled.
     */
    public boolean isVersioning(FileSystemOptions opts)
    {
        return getBoolean(opts, "versioning", false);
    }

    /**
     * @return The Webdav FileSystem Class object.
     */
    protected Class getConfigClass()
    {
        return WebdavFileSystem.class;
    }
}
