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

package org.apache.commons.vfs.provider.webdav.test;

import org.apache.commons.vfs.impl.test.VfsClassLoaderTests;
import org.apache.commons.vfs.test.AbstractTestSuite;
import org.apache.commons.vfs.test.ProviderTestConfig;
import org.apache.commons.vfs.test.ProviderCacheStrategyTests;
import org.apache.commons.vfs.test.UriTests;
import org.apache.commons.vfs.test.NamingTests;
import org.apache.commons.vfs.test.ContentTests;
import org.apache.commons.vfs.test.ProviderReadTests;
import org.apache.commons.vfs.test.ProviderRandomReadTests;
import org.apache.commons.vfs.test.ProviderWriteTests;
import org.apache.commons.vfs.test.ProviderWriteAppendTests;
import org.apache.commons.vfs.test.ProviderRandomReadWriteTests;
import org.apache.commons.vfs.test.ProviderRenameTests;
import org.apache.commons.vfs.test.ProviderDeleteTests;
import org.apache.commons.vfs.test.LastModifiedTests;
import org.apache.commons.vfs.test.UrlTests;
import org.apache.commons.vfs.test.UrlStructureTests;
import org.apache.commons.vfs.test.ProviderTestSuite;

/**
 * The suite of tests for a file system.
 *
 * @author <a href="http://commons.apache.org/vfs/team-list.html">Commons VFS team</a>
 * @version $Id: WebdavProviderTestSuite.java 773234 2009-05-09 15:27:59Z rgoers $
 */
public class WebdavProviderTestSuite extends ProviderTestSuite
{
    /**
     * Adds the tests for a file system to this suite.
     */
    public WebdavProviderTestSuite(final ProviderTestConfig providerConfig) throws Exception
    {
        this(providerConfig, "", false, false);
    }

    /**
     * Adds the tests for a file system to this suite. Provider has an empty directory.
     */
    public WebdavProviderTestSuite(final ProviderTestConfig providerConfig,
                             final boolean addEmptyDir) throws Exception
    {
        this(providerConfig, "", false, addEmptyDir);
    }



    protected WebdavProviderTestSuite(final ProviderTestConfig providerConfig,
                                final String prefix,
                                final boolean nested,
                                final boolean addEmptyDir)
        throws Exception
    {
        super(providerConfig, prefix, nested, addEmptyDir);
    }

    /**
     * Adds base tests - excludes the nested test cases.
     */
    protected void addBaseTests() throws Exception
    {
        addTests(ProviderCacheStrategyTests.class);
        addTests(UriTests.class);
        addTests(NamingTests.class);
        addTests(ContentTests.class);
        addTests(ProviderReadTests.class);
        addTests(ProviderRandomReadTests.class);
        addTests(ProviderWriteTests.class);
        addTests(ProviderWriteAppendTests.class);
        addTests(ProviderRandomReadWriteTests.class);
        addTests(ProviderRenameTests.class);
        addTests(ProviderDeleteTests.class);
        addTests(LastModifiedTests.class);
        addTests(UrlTests.class);
        addTests(UrlStructureTests.class);
        // The class loader test requires the classes be uploaded to the webdav repo.
        //addTests(VfsClassLoaderTests.class);
    }
}
