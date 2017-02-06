Ext.define('core.app.view.LoginWindow', {
	extend : 'Ext.window.Window',
	title : "门禁系统登录",
	iconCls : 'table_login',
	width : 400,
	height : 230,
	alias : "widget.loginwindow",
	modal : true,
	buttonAlign : 'center',
	closable : false,
	closeAction : "destroy",
	items : [{
				ref : "logininfo",
				xtype : "displayfield",
				value : "",
				height : 20,
				margin : "0 0 0 0"
			}, {
				layout : "column",
				bodyStyle : 'background:transparent',// 设置为透明,不不妨碍更换主题了
				border : 0,
				items : [{
					columnWidth : .7,
					xtype : "form",
					ref : "loginform",
					defaults : {
						labelSeparator : ':',
						labelWidth : 40,
						width : 200,
						// allowBlank : false,
						labelAlign : 'right',
						msgTarget : 'side'
					},
					defaultType : 'textfield',
					bodyStyle : 'background:transparent',// 设置为透明,不不妨碍更换主题了
					border : 0,
					items : [{
						fieldLabel : "用户名",
						labelWidth : 50,
						selectOnFocus : true,
						fieldCls : 'username',
						emptyText : "请输入用户名",
						// regex :
						// /([A-Za-z]{1})\w{1,19}/,
						// regexText :
						// '用户名格式有误',
						name : 'username',
						margin : "10 10 10 50",
						listeners : {
							change : function(_this, newV, oldV) {
								var password = _this.up("form").getForm()
										.findField("password");
								if (Ext.util.Cookies.get("username") != null) {
									if (newV == Ext.util.Cookies
											.get("username")) {
										password.setValue(Ext.util.Cookies
												.get("password"));
									} else {
										password.setValue("");
									}
								}
							}
						}
					}, {
						name : 'password',
						// selectOnFocus : true,
						labelWidth : 50,
						fieldLabel : '密&nbsp;&nbsp;&nbsp;码',
						fieldCls : 'password',
						emptyText : "请输入密码",
						inputType : 'password',
						margin : "20 10 10 50"
					}]
				}, {

					layout : 'fit',
					bodyStyle : 'background:transparent',// 设置为透明,不不妨碍更换主题了
					border : 0,
					columnWidth : .28,
					items : [{
						xtype : "displayfield",
						hideLabel : true,
						margin : "0 0 0 0",
						value : "<img height=100 src='./images/loginlogo.gif' />"
					}]
				}]
			}],
	buttons : [{
				xtype : "button",
				text : '登录',
				ref : "login",
				width : 50
			}, {
				xtype : "button",
				text : '退出',
				width : 50,
				// margin : "10 10 10 20",
				handler : function(_btn) {
					// var loginWin = _btn.up("loginwindow");
					Ext.Msg.confirm("提示", "是否要退出系统", function(btn) {
						if (btn == 'yes') {
							if (document.all) {// IE
								window.open('', '_parent', '');
								window.close();
							} else {// FF
								window.open('', '_self', '');
								_btn
										.up("loginwindow")
										.down("displayfield")
										.setValue("<font color=red>提示：检测到当前是Firefox浏览器！请在址栏上输入about:config然后回车;搜索dom.allow_scripts_to_close_windows双击修改值为true再试此功能，这样才能关闭窗口</font>");
								window.close();
							}
						}
					}, this);
				}
			}]
});
