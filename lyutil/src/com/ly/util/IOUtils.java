package com.ly.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
	 * ѭ����ȡ�ı�����Ĭ��UTF-8�ַ�������
	 * @param is
	 * @return
	 */
	public static Iterable<String> readText(InputStream is) {
		return readText(is, "utf-8");
	}

	/**
	 * ѭ����ȡ�ı���
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

}
