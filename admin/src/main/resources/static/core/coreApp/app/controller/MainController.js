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
							dis.setText("<font size=4 color=red><b>"
									+ "您未登陆" + "</b></font>");
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
				var myMask = new Ext.LoadMask(Ext.ComponentQuery
								.query("panel[xtype=centerview]")[0], {
							msg : "加载页面 中，请您稍等..."
						});
				var mainView = funInfo.mainView;
				var funPanel = mainView.down(funInfo.funViewXtype);
				if (!funPanel) {
					if (null != myMask)
						myMask.show();
					try {
						self.application.getController(funInfo.funController)
								.init();
						funPanel = Ext.create(funInfo.funViewName, {
									closable : true
								});
					} catch (e) {
						myMask.hide();
						// console.log(e);
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
										emptyText : '默认密码为工号'

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
									var oldpwd = formObj
											.findField('oldPassword')
											.getValue();
									var newpwd = formObj
											.findField('newPassword')
											.getValue();
									var resObj = self.ajax({
												url : "login!changePassword.action",
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
							self.addFunItem({
								mainView : mainView,
								funViewXtype : resObj.viewid,
								funController : resObj.viewcontroller,
								funViewName : resObj.viewname
							});
						}
					});
					return;
					switch (record.data["id"]) {
					//TODO 动态的获取
						// 用户浏览
						case "userlist" :
							self.addFunItem({
								mainView : mainView,
								funViewXtype : "peoplegrid",
								funController : "core.basicinfomanage.peoplemanage.controller.PeopleController",
								funViewName : "core.basicinfomanage.peoplemanage.view.PeopleGrid"
							});
							break;
						// 用户跟新
						case "userupdate" :
							self.addFunItem({
								mainView : mainView,
								funViewXtype : "updatepeoplegrid",
								funController : "core.basicinfomanage.peoplemanage.controller.PeopleController",
								funViewName : "core.basicinfomanage.peoplemanage.view.UpdatePeopleGrid"
							});
							break;
						// 用户删除
						case "userdelete" :
								self.addFunItem({
									mainView : mainView,
									funViewXtype : "deletepeoplegrid",
									funController : "core.basicinfomanage.peoplemanage.controller.PeopleController",
									funViewName : "core.basicinfomanage.peoplemanage.view.DeletePeopleGrid"
								});
								break;
						// 用户添加
						case "useradd" :
								self.addFunItem({
									mainView : mainView,
									funViewXtype : "addpeople",
									funController : "core.basicinfomanage.peoplemanage.controller.PeopleController",
									funViewName : "core.basicinfomanage.peoplemanage.view.AddPeople"
								});
								break;
						// 用户角色设置
						case "userrolesetting" :
								self.addFunItem({
									mainView : mainView,
									funViewXtype : "setrolepeoplegrid",
									funController : "core.basicinfomanage.peoplemanage.controller.PeopleController",
									funViewName : "core.basicinfomanage.peoplemanage.view.SetRolePeopleGrid"
								});
								break;

                        // 角色浏览
                        case "rolelist" :
                            self.addFunItem({
                                mainView : mainView,
                                funViewXtype : "rolegrid",
                                funController : "core.systemmanage.rolemanage.controller.RoleController",
                                funViewName : "core.systemmanage.rolemanage.view.RoleGrid"
                            });
                            break;
                        // 角色添加
                        case "roleadd" :
                            self.addFunItem({
                                mainView : mainView,
                                funViewXtype : "addrole",
                                funController : "core.systemmanage.rolemanage.controller.RoleController",
                                funViewName : "core.systemmanage.rolemanage.view.AddRole"
                            });
                            break;
                        // 角色更新
                        case "roleupdate" :
                            self.addFunItem({
                                mainView : mainView,
                                funViewXtype : "updaterolegrid",
                                funController : "core.systemmanage.rolemanage.controller.RoleController",
                                funViewName : "core.systemmanage.rolemanage.view.UpdateRoleGrid"
                            });
                            break;
                        // 角色删除
                        case "roledelete" :
                            self.addFunItem({
                                mainView : mainView,
                                funViewXtype : "deleterolegrid",
                                funController : "core.systemmanage.rolemanage.controller.RoleController",
                                funViewName : "core.systemmanage.rolemanage.view.DeleteRoleGrid"
                            });
                            break;
                        // 角色菜单设置
                        case "rolemenusetting" :
                            self.addFunItem({
                                mainView : mainView,
                                funViewXtype : "setrolemenugrid",
                                funController : "core.systemmanage.rolemanage.controller.RoleController",
                                funViewName : "core.systemmanage.rolemanage.view.SetRoleMenuGrid"
                            });
                            break;

						// 服务管理
                        case "servicelist" :
                            self.addFunItem({
                                mainView : mainView,
                                funViewXtype : "wsgrid",
                                funController : "core.basicinfomanage.wsmanage.controller.WsController",
                                funViewName : "core.basicinfomanage.wsmanage.view.WsGrid"
                            });
                            break;

					}
				}
			}
		});
	},
	views : ["core.app.view.WestView", "core.app.view.CenterView",
			"core.app.view.MainViewLayout"],
	stores : [],
	models : []
});