package com.ly.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

public class IOUtils {
	public static PrintWriter buildPrintWriter(OutputStream os) {
		return new PrintWriter(new OutputStreamWriter(os));
	}

	public static BufferedReader buildBufferedReader(InputStream is) {
		return new BufferedReader(new InputStreamReader(is));
	}

	/**
	 * 以文本方式读入输入流，并逐行输出，默认UTF-8编码
	 * @param is
	 * @return
	 */
	public static Iterable<String> readText(InputStream is) {
		return readText(is, "utf-8");
	}

	/**
	 * 以文本方式读入输入流，并逐行输出
	 * @param is
	 * @param charset
	 * @return
	 */
	public static Iterable<String> readText(InputStream is, String charset) {
		BufferedReader br = buildBufferedReader(is);
		return new Iterable<String>() {
			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					String line = null;

					@Override
					public boolean hasNext() {
						try {
							line = br.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return line != null;
					}

					@Override
					public String next() {
						try {
							return charset != null ? new String(line.getBytes(), charset) : line;
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
							return null;
						}
					}
				};
			}
		};
	}

	/**
	 * 关闭资源
	 * @param closies
	 */
	public static void close(Closeable... closies) {
		for (Closeable c : closies) {
			if (c != null) {
				try {
					c.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void send(OutputStream out, InputStream in) throws IOException {
		send(out, in, 0, 0);
	}

	public static void send(OutputStream out, InputStream in, long pos, long maxSize) throws IOException {
		byte[] buffer = new byte[1024];
		int count;
		if (pos > 0) {
			in.skip(pos);
		}
		long size = 0;
		while ((count = in.read(buffer, 0, buffer.length)) > 0) {
			if (maxSize > 0 && size + count > maxSize) {
				count = (int) (maxSize - size);
			}
			out.write(buffer, 0, count);
			size += count;
		}
	}

	public static void save(RandomAccessFile out, InputStream in, long pos, long maxSize) throws IOException {
		byte[] buffer = new byte[1024];
		int count;
		if (pos > 0) {
			in.skip(pos);
			out.seek(pos);
		}
		long size = 0;
		while ((count = in.read(buffer, 0, buffer.length)) > 0) {
			if (maxSize > 0 && size + count > maxSize) {
				count = (int) (maxSize - size);
			}
			out.write(buffer, 0, count);
			size += count;
		}
	}

	public static void println(String msg, Object... objs) {
		System.out.println(String.format(msg, objs));
	}

	public static void print(String msg, Object... objs) {
		System.out.print(String.format(msg, objs));
	}

}
