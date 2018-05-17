<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Easyui界面</title>
<link rel="stylesheet" type="text/css" href="${basePath }/css/ly.css">
<link rel="stylesheet" type="text/css" href="${basePath }/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath }/easyui/themes/icon.css">
<script type="text/javascript" src="${basePath }/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${basePath }/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath }/easyui/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
td {
	padding: 1px 3px;
	color: #333
}

li {
	padding: 2px;
}

.row li {
	display: table-row;
}

.row li * {
	display: table-cell;
}

#demo li {
	height: 30px;
}

#demo li * {
	vertical-align: middle;
}

.easyui-linkbutton {
	margin-top: 3px
}
</style>
<script type="text/javascript">
	/** 动态添加tab，使用iframe显示内容 **/
	function addTab(title, href, icon) {
		var tt = $('#tabs');
		if (tt.tabs('exists', title)) {//如果tab已经存在,则选中并刷新该tab            
			tt.tabs('select', title);
			refreshTab({
				tabTitle : title,
				url : href
			});
		} else {
			var content;
			if (href) {
				content = '<iframe scrolling="no" frameborder="0"  src="'
						+ href + '" style="width:100%;height:100%;"></iframe>';
			} else {
				content = '未实现';
			}
			tt.tabs('add', {
				title : title,
				closable : true,
				content : content,
				iconCls : icon || 'icon-search'
			});
		}
	}
</script>
</head>
<body>
	<!-- style="background: url(images/bg.jpg);background-size:cover" -->
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north'" style="overflow: hidden;">
			<table style="width: 100%">
				<tr>
					<td width="20px">
						<img alt="Logo" src="${basePath}/easyui/themes/icons/large_chart.png">
					</td>
					<td>
						<h2 style="margin: 1px; color: #333">XXXX管理系统</h2>
					</td>
					<td align="right" style="padding: 5px 11px">
						欢迎，XXX
						<a href="#">退出</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="easyui-accordion" data-options="region:'west',split:true" title="菜单" style="width: 200px;">
			<div title="项目案例">
				<a href="#" class="easyui-linkbutton" onclick="addTab('AJAX用户登录','${basePath }/login/back/login.jsp')" data-options="iconCls:'icon-search'" style="width: 100%">AJAX用户登录</a>
				<a href="#" class="easyui-linkbutton" onclick="addTab('双色球数据上传','${basePath }/ssq/jsp/index.jsp')" data-options="iconCls:'icon-print'" style="width: 100%">双色球数据上传</a>
				<a href="#" class="easyui-linkbutton" onclick="addTab('双色球图形报表','${basePath }/report/report.jsp')" data-options="iconCls:'icon-reload'" style="width: 100%">双色球图形报表</a>
			</div>
			<div title="系统管理">
				<a href="#" class="easyui-linkbutton" onclick="addTab('测试页面','${basePath }/test.jsp')" data-options="iconCls:'icon-search'" style="width: 100%">测试页面</a>
				<a href="#" class="easyui-linkbutton" onclick="addTab('文件上传','${basePath }/upload.jsp')" data-options="iconCls:'icon-print'" style="width: 100%">文件上传</a>
				<a href="#" class="easyui-linkbutton" onclick="addTab('用户管理','${basePath }/queryUser.jsp')" data-options="iconCls:'icon-reload'" style="width: 100%">用户管理</a>
			</div>
		</div>
		<div id="tabs" class="easyui-tabs" data-options="region:'center',iconCls:'icon-ok'">
			<div title="使用指南" style="padding: 10px">
				<details open>
					<summary style="font-size: 20px"> 一、EasyUI是什么？ </summary>
					<ol>
						<li>easyui是一种基于jQuery的用户界面插件集合。</li>
						<li>使用easyui你不需要写很多代码，你只需要通过编写一些简单HTML标记，就可以定义用户界面。</li>
						<li>easyui是个完美支持HTML5网页的完整框架。</li>
						<li>easyui节省您网页开发的时间和规模。</li>
					</ol>
				</details>
				<details>
					<summary style="font-size: 20px"> 二、学习 EasyUI 的条件 </summary>
					<ol>
						<li>EasyUI 是基于jQuery 的 UI 库。所以必须需要 jQuery 课程的基础。而 jQuery 需要哪些基础，出门右转找度娘。</li>
					</ol>
				</details>
				<details>
					<summary style="font-size: 20px"> 三、EasyUI 的目录结构 </summary>
					<ol class="row">
						<li><span style="color: red">locale目录：</span><span>国际化JS文件目录</span></li>
						<li><span style="color: red">plugins目录：</span><span>插件（控件）目录</span></li>
						<li><span style="color: red">themes目录：</span><span>主题目录</span></li>
						<li><span>demo目录：</span><span>插件案例目录</span></li>
						<li><span style="color: red">jquery.min.js：</span><span>jQuery JS文件</span></li>
						<li><span style="color: red">jquery.easyui.min.js：</span><span>EasyUI JS文件</span></li>
						<li><span>easyloader.js：</span><span>JS文件、CSS文件加载器（按需加载）</span></li>
					</ol>
				</details>
				<details>
					<summary style="font-size: 20px"> 四、EasyUI的使用方法 </summary>
					<ol>
						<li><details open>
								<summary>导入资源（注意导入顺序，jQuery应该先于EasyUI导入）</summary>
								<ol>
									<li><code>&lt;link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"></code></li>
									<li><code>&lt;link rel="stylesheet" type="text/css" href="${basePath }/easyui/themes/icon.css"></code></li>
									<li><code>&lt;script type="text/javascript" src="${basePath }/easyui/jquery.min.js">&lt;/script></code></li>
									<li><code>&lt;script type="text/javascript" src="${basePath }/easyui/jquery.easyui.min.js">&lt;/script></code></li>
									<li><code>&lt;script type="text/javascript" src="${basePath }/easyui/locale/easyui-lang-zh_CN.js">&lt;/script></code></li>
								</ol>
							</details></li>
						<li><details>
								<summary>页面控件增强语法：class + 自定义属性（data-options）</summary>
								<ol class="row">
									<li><span>设置样式：</span> <code>class="easyui-插件名"，如：class="easyui-textbox"</code></li>
									<li><span>设置属性：</span> <code>data-options="属性名:属性值,···,属性名:属性值"</code></li>
									<li><span>完整的例子，定义一个面板：</span> <code>&lt;div id="p" class="easyui-panel" title="My Panel" data-options="iconCls:'icon-save',collapsible:true,maximizable:true"></code></li>
								</ol>
							</details></li>
						<li><details>
								<summary>调用控件方法，EasyUI为每种控件定义了一个方法，方法名与插件名一致，以tabs控件为例</summary>
								<ol class="row">
									<li><span>获取tabs控件：</span> <code>var t = $("#tid")</code></li>
									<li><span>设置tabs属性：</span> <code>t.tabs({属性名:属性值,···,属性名:属性值})</code></li>
									<li><span>调用tabs方法：</span> <code>t.tabs('方法名',参数)</code></li>
								</ol>
							</details></li>
					</ol>
				</details>
				<details>
					<summary style="font-size: 20px"> 五、常用 EasyUI 插件 </summary>
					<ol class="row" id="demo">
						<li><span style="color: red"> textbox 文本框：</span> <span><input class="easyui-textbox" data-options="prompt:'输入邮箱地址...',validType:'email'" style="width: 200px; height: 32px"></span></li>
						<li><span style="color: red"> combobox 下拉列表框：</span> <span><select class="easyui-combobox" name="state" style="width: 200px;">
									<option value="AL">Alabama</option>
									<option value="AK">Alaska</option>
									<option value="AZ">Arizona</option>
								</select></span></li>
						<li><span> numberspinner 数字微调框：</span> <span><input class="easyui-numberspinner" value="1000" data-options="increment:100" style="width: 200px;"></span></li>
						<li><span> linkbutton 链接按钮：</span><span>请访问官网</span></li>
						<li><span> layout 布局：</span><span>请访问官网</span></li>
						<li><span> accordion 分类（手风琴）：</span><span>请访问官网</span></li>
						<li><span style="color: red"> linkbutton 按钮：</span><span>请访问官网</span></li>
						<li><span> tabs 选项卡：</span><span>请访问官网（注意：easyui 的 tabs 使用不方便，要做改造）</span></li>
						<li><span> pagination 分页：</span><span>请访问官网</span></li>
						<li><span> progressbar 进度条：</span><span>请访问官网</span></li>
						<li><span> window 窗口：</span><span>请访问官网</span></li>
						<li><span style="color: red"> dialog 对话框 ：</span><span>请访问官网</span></li>
						<li><span style="color: red"> messager 消息窗口：</span><span>请访问官网</span></li>
						<li><span> tree 树：</span><span>请访问官网</span></li>
						<li><span style="color: red"> datagrid 表格 ：</span><span>请访问官网</span></li>
					</ol>
				</details>
			</div>
		</div>
		<div data-options="region:'south'" style="text-align: center; padding: 3px">
			<span>版权：源辰公司</span>
		</div>
	</div>
</body>
</html>