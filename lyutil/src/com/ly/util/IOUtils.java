package com.ly.util;

import java.io.BufferedInputStream;
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

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static PrintWriter buildPrintWriter(OutputStream os) {
		return new PrintWriter(new OutputStreamWriter(os));
	}

	public static BufferedReader buildBufferedReader(InputStream is) {
		return new BufferedReader(new InputStreamReader(is));
	}

	public static BufferedReader buildBufferedReader(InputStream is, String charset) {
		try {
			return new BufferedReader(new InputStreamReader(is, charset));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 以文本方式读入输入流，并逐行输出
	 * @param is
	 * @param charset
	 * @return
	 * @throws IOException 
	 */
	public static Iterable<String> eachLine(InputStream is, String... charsets) {
		String charset = charsets.length > 0 ? charsets[0] : null;
		BufferedReader br;
		if (charset == null) {
			br = buildBufferedReader(is);
		} else {
			br = buildBufferedReader(is, charset);
		}
		return new Iterable<String>() {
			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {

					String line;

					@Override
					public boolean hasNext() {
						// 为了避免阻塞，使用 available 方法判断有无数据可读
						try {
							line = br.readLine();
							return line != null;
						} catch (IOException e) {
							e.printStackTrace();
							return false;
						}
					}

					@Override
					public String next() {
						String ret = line;
						line = null;
						return ret;
					}
				};
			}
		};
	}

	public static Iterable<String> eachString(InputStream is, String... charsets) {
		String charset = charsets.length > 0 ? charsets[0] : null;
		BufferedInputStream bis = new BufferedInputStream(is);
		return new Iterable<String>() {
			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					byte[] buffer = new byte[1024];
					int length;
					int availableSize = 1;

					@Override
					public boolean hasNext() {
						try {
							if (availableSize > 0) {
								length = bis.read(buffer);
								/**
								 * 第一次 read() 之前 available() 返回的是 0
								 * 因此要在 read() 之后才能调用 available()
								 */
								availableSize = bis.available();
								return length > 0;
							} else {
								return false;
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						return false;
					}

					@Override
					public String next() {
						try {
							return charset == null ? new String(buffer, 0, length)
									: new String(buffer, 0, length, charset);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
							return null;
						}
					}
				};
			}
		};
	}

	public static String readString(InputStream in, String... charsets) {
		StringBuilder sb = new StringBuilder();
		for (String s : eachString(in, charsets)) {
			sb.append(s);
		}
		return sb.toString();
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
		while ((count = in.read(buffer)) > 0) {
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

	public static void println(InputStream in, String... charsets) throws IOException {
		for (String line : eachLine(in, charsets)) {
			System.out.println(line);
		}
	}

	public static void printlnAll(Object... msgs) {
		for (Object msg : msgs) {
			println("" + msg);
		}
	}
}
