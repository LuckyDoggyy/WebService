/** 主控制器 */Ext.define("core.app.controller.MainController", {
	extend : "Ext.app.Controller",
	mixins : {
		suppleUtil : "core.util.SuppleUtil",
		messageUtil : "core.util.MessageUtil"
	},
	init : function() {
		var self = this;
		Ext.Ajax.request({
					// waitMsg : '正在登陆,请稍后...',
					url : "checklogin",
					method : "POST",
					timeout : 4000,
					sync : false,
					success : function(response, opts) {
						var resObj = Ext.decode(response.responseText);
						if (resObj.success) {
							var dis = Ext.getCmp("displaylogin");
							dis.setText("<font  size=4 color=red><b>"+ "欢迎 "+"</b></font>");
						} else {
							// Ext.Msg.alert("提示","用户名和密码错误");
							var dis = Ext.getCmp("displaylogin");
							dis.setText("<font size=4 color=red><b>"+ "您未登陆" + "</b></font>");
						}
					}
				});
		/** 显示登陆窗口 */
		// var loginWin
		// =Ext.create("core.app.view.LoginWindow");
		// loginWin.show();
		/** 公用添加页面的方法 */
		/**
		 * 动态加载controller并渲染它的主窗体
		 */
		this.addFunItem = function(funInfo) {
			if (funInfo) {
				var myMask = new Ext.LoadMask(Ext.ComponentQuery.query("panel[xtype=centerview]")[0], {
							msg : "加载页面 中，请您稍等..."
						});
				var mainView = funInfo.mainView;
				var funPanel = mainView.down(funInfo.funViewXtype);
				if (!funPanel) {
					if (null != myMask)
						myMask.show();
					try {
						self.application.getController(funInfo.funController).init();
						funPanel = Ext.create(funInfo.funViewName, {
									closable : true
								});
								if(funInfo.funViewXtype=="serviceitem"){
				                    funPanel.html='<iframe id="frame" src="serviceview/service.html?'+funInfo.fid+'" frameborder="0" width="100%" height="100%"></iframe>';
								}
					} catch (e) {
						myMask.hide();
					}
					mainView.add(funPanel);
					mainView.setActiveTab(funPanel);
					if (null != myMask)
						myMask.hide();
				} else {
					mainView.setActiveTab(funPanel);
				}
			}
		},
		/** 下在是控制部分 */
		this.control({
			// 修改密码
			"panel[xtype=mainviewlayout] button[ref=changepassword]" : {
				click : function(btn) {
					Ext.create('Ext.window.Window', {
						title : '修改密码',
						width : 290,
						height : 190,
						layout : 'fit',
						buttonAlign : 'center',
						modal : true,
						items : [{
							xtype : 'form',
							margin : '5 5 5 5',
							defaults : {
								labelSeparator : ':',
								// margin:'10
								// 5 5
								// 5',
								// labelWidth:60,
								// width:400,
								allowBlank : false,
								msgTarget : 'side',
								labelAlign : 'left'
							},
							anchor : '100%',
							// bodyStyle
							// :
							// "background:#DFE9F6",
							border : 0,
							items : [{
										xtype : 'textfield',
										fieldLabel : '原密码',
										name : 'oldPassword',
										inputType : 'password',
										emptyText : '默认密码为账号'

									}, {
										xtype : 'textfield',
										fieldLabel : '新密码',
										name : 'newPassword',
										inputType : 'password'
									}, {
										xtype : 'textfield',
										fieldLabel : '确认新密码',
										inputType : 'password',
										validator : function(value) {
											var pw = this.previousSibling().value;
											if (value != pw) {
												return '两次输入的密码不一致';
											} else {
												return true;
											}
										}
									}]
						}],
						buttons : [{
							text : '修改',
							handler : function(btn) {
								var win = btn.ownerCt.ownerCt;
								var formObj = win.child('form').getForm();

								if (formObj.isValid()) {
									var oldpwd = formObj.findField('oldPassword').getValue();
									var newpwd = formObj.findField('newPassword').getValue();
									var resObj = self.ajax({
												url : "user/updatePass",
												params : {
													oldPassword : oldpwd,
													newPassword : newpwd
												}
											});
									if (resObj.success) {
										self.msgbox(resObj.obj);
										btn.ownerCt.ownerCt.close();
										return false;
									} else {
										Ext.Msg.alert("友情提示", resObj.obj);
										return false;
									}
								}
							}
						}, {
							text : '关闭',
							handler : function(btn, o) {
								btn.ownerCt.ownerCt.close();
								return false;
							}
						}]
					}).show();
					return false;
				}
			},
			"panel[xtype=mainviewlayout] button[ref=manual]" : {
				click : function(btn) {
					window.location.href = "login!downloadManual.action";
					return false;
				}
			},
			"panel[xtype=mainviewlayout] button[ref=simplemanual]" : {
				click : function(btn) {
					window.location.href = "login!downloadSimpleManual.action";
					return false;

				}
			},
			// 退出系统
			"panel[xtype=mainviewlayout] button[ref=logoutsystem]" : {
				click : function(btn) {
					Ext.Msg.confirm('友情提示', '确定要退出系统吗?', function(btn, text) {
								if (btn == 'yes') {
									var resObj=self.ajax({
												url : "logout"
											});

									if (resObj.success) {
										location = "login.html";
										return false;
									}
								}
							});
				}
			},

			"westview treepanel" : {
				itemclick : function(tree, record, item, index, e, eOpts) {
					var mainView = tree.up("mainviewlayout").down("centerview");
					Ext.Ajax.request({
						url : "getView",
						method : "get",
						params : {mid : record.data["id"]},
						timeout : 4000,
						sync : false,
						success : function(response, opts) {
							var resObj = Ext.decode(response.responseText);
							if(resObj.viewid!=null&&resObj.viewid!=""){
								self.addFunItem({
									mainView : mainView,
									funViewXtype : resObj.viewid,
									funController : resObj.viewcontroller,
									funViewName : resObj.viewname,
									fid : resObj.fid
								});
							}
						}
					});
					return;
					}
				}
		});
	},
	views : ["core.app.view.WestView", "core.app.view.CenterView",
			"core.app.view.MainViewLayout"],
	stores : [],
	models : []
});