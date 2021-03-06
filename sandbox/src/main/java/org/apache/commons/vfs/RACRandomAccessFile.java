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

package org.apache.commons.vfs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Encapsulates a {@link RandomAccessContent} instance, allowing it to be used
 * as a {@link RandomAccessFile} instance.
 *
 * @author Elifarley Callado Coelho Cruz <elifarley@gmail.com>
 * @version 1.1, $LastChangedRevision: 1163 $, $LastChangedDate: 2006-12-13 03:18:57 +0000 (Qua, 13 Dez 2006) $
 */
public class RACRandomAccessFile extends RandomAccessFile implements RandomAccessContent
{
	private RandomAccessContent rac;
	protected final byte[] singleByteBuf = new byte[1];

	private static File createTempFile() throws IOException
	{
		return File.createTempFile("fraf", "");
	}

	private void deleteTempFile(File tempFile)
	{
		try
		{
			super.close();
		}
		catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
		finally
		{
			tempFile.delete();
		}
	}

	public RACRandomAccessFile(RandomAccessContent rac) throws IOException
	{
		this(createTempFile());
		this.rac = rac;
	}

	private RACRandomAccessFile(File tempFile) throws IOException
	{
		super(tempFile, "r");
		deleteTempFile(tempFile);
	}

	public long getFilePointer() throws IOException
	{
		return this.rac.getFilePointer();
	}

	public void seek(long pos) throws IOException
	{
		this.rac.seek(pos);
	}

	public int skipBytes(int n) throws IOException
	{
		return this.rac.skipBytes(n);
	}

	public long length() throws IOException
	{
		return this.rac.length();
	}

	/* (non-Javadoc)
		 * @see org.ecc.base.io.FilterRandomAccessFile#setLength(long)
		 */
	public void setLength(long newLength) throws IOException
	{
		throw new IOException("Underlying RandomAccessContent instance length cannot be modified.");
	}

	public InputStream getInputStream() throws IOException
	{
		return this.rac.getInputStream();
	}

	public void close() throws IOException
	{
		this.rac.close();
	}


	/**
	 * @see java.io.RandomAccessFile#read(byte[])
	 */
	public final int read(byte[] b) throws IOException
	{
		return read(b, 0, b.length);
	}

	/**
	 * @see java.io.RandomAccessFile#read()
	 */
	public final int read() throws IOException
	{
		final byte[] buf = this.singleByteBuf;
		int count = read(buf, 0, 1);
		return count < 0 ? -1 : (buf[0] & 0xFF);
	}

	public int read(byte[] b, int off, int len) throws IOException
	{
		this.rac.readFully(b, off, len);
		return len;
	}

	/**
	 * @see java.io.RandomAccessFile#write(int)
	 */
	public final void write(int b) throws IOException
	{
		final byte[] buf = this.singleByteBuf;
		buf[0] = (byte) b;
		write(buf, 0, 1);
	}

	/**
	 * @see java.io.RandomAccessFile#write(byte[])
	 */
	public final void write(byte[] b) throws IOException
	{
		write(b, 0, b.length);
	}

	/**
	 * @see java.io.RandomAccessFile#write(byte[],int,int)
	 */
	public void write(byte[] b, int off, int len) throws IOException
	{
		this.rac.write(b, off, len);
	}

}
