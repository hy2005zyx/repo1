package com.yc.teach.common.tag;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class SelectBoxTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String name;
	private String type;
	private String valueName;
	private String labelName;

	private Iterator<?> it;

	@Override
	public int doStartTag() throws JspException {
		if (it != null && it.hasNext()) {
			if (type == null) {
				type = "radio";
			}
			if (name == null) {
				name = type + "_" + (nameNumber++);
			}
			while (it.hasNext()) {
				Object o = it.next();
				if (o != null) {
					if (o instanceof String) {
						//从字符串数组中获取 显示值 和 真实值，显示值 = 真实值
						println("<input type='%s' name='%s' value='%s'>%s", type, name, o, o);
					} else if (o instanceof Entry) {
						//从map中获取 显示值 和 真实值
						Entry<?, ?> e = (Entry<?, ?>) o;
						println("<input type='%s' name='%s' value='%s'>%s", type, name, e.getKey(), e.getValue());
					} else {
						//使用反射获取 显示值 和 真实值
						if (valueName == null) {
							valueName = labelName;
						}
						if (labelName != null && valueName != null) {
							try {
								Field lf = o.getClass().getField(labelName);
								Field vf = o.getClass().getField(valueName);
								lf.setAccessible(true);
								vf.setAccessible(true);
								println("<input type='%s' name='%s' value='%s'>%s", type, name, vf.get(o), lf.get(o));
							} catch (Exception e) {
								e.printStackTrace();
								return EVAL_BODY_AGAIN;
							}
						}
					}
				}
			}
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	private void println(String str, Object... params) throws JspException {
		try {
			pageContext.getOut().println(String.format(str, params));
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	public void setItems(Object items) {
		if (items instanceof String) {
			if (((String) items).contains(":")) {
				String[] ss = ((String) items).split("[,:]");
				Map<String, String> m = new HashMap<String, String>();
				for (int i = 0; i < ss.length - 1; i += 2) {
					m.put(ss[i], ss[i + 1]);
				}
				it = m.entrySet().iterator();
			} else {
				String[] ss = ((String) items).split(",");
				it = Arrays.asList(ss).iterator();
			}
		} else if (items instanceof Collection) {
			it = ((Collection<?>) items).iterator();
		}
	}

	private int nameNumber;

	public void setName(String name) {
		this.name = name;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public void setType(String type) {
		this.type = type;
	}

}
