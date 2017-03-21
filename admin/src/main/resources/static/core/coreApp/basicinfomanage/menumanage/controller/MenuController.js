Ext.define("core.basicinfomanage.menumanage.controller.MenuController",
				{
					extend : "Ext.app.Controller",
					mixins : {
						suppleUtil : "core.util.SuppleUtil",
						messageUtil : "core.util.MessageUtil",
						formUtil : "core.util.FormUtil",
						treeUtil : "core.util.TreeUtil"
					},
					init : function() {
						var self = this;
						this.control({
						    "panel[xtype=deletemenugrid]" : {
                                   select : this.deleteCheckEdit,
                                   deselect : this.deleteCheckEdit
                                },
                                "panel[xtype=updatemenugrid]" : {
                                   select : this.updateCheckEdit,
                                   deselect : this.updateCheckEdit
                                },

							"menugrid button[ref=searchMenu]" : {
											click : function(btn) {
												var tbar = btn.ownerCt;
												var mid = tbar.down("textfield[name=mid]").getValue();
												var grid = tbar.ownerCt;
												var _store = grid.getStore();
												proxy = _store.getProxy();
												proxy.extraParams = {
													mid : mid
												};
												_store.loadPage(1);
												return false;
											}
										},
							"deletemenugrid button[ref=searchMenu]" : {
											click : function(btn) {
												var tbar = btn.ownerCt;
												var mid = tbar.down("textfield[name=mid]").getValue();
												var grid = tbar.ownerCt;
												var _store = grid.getStore();
												proxy = _store.getProxy();
												proxy.extraParams = {
													mid : mid
												};
												_store.loadPage(1);
												return false;
											}
										},
							"updatemenugrid button[ref=searchMenu]" : {
										click : function(btn) {
											var tbar = btn.ownerCt;
											var mid = tbar.down("textfield[name=mid]").getValue();
											var grid = tbar.ownerCt;
											var _store = grid.getStore();
											proxy = _store.getProxy();
											proxy.extraParams = {
												mid : mid
											};
											_store.loadPage(1);
											return false;
										}
									},

                                "panel[xtype=addmenu] button[ref=addMenu]" : {
                                         click : function(btn) {
                                             var addmenu = btn.up("panel[xtype=addmenu]");
                                             var formObj = addmenu.getForm();
                                             var params = self.getFormValue(formObj);
                                             if (formObj.isValid()) {
                                                 var resObj = self.ajax({
                                                             url : "menu/addMenu",
                                                             params : params
                                                         });
                                                 if (resObj.success) {
                                                     self.msgbox('菜单添加成功');
                                                     addmenu.down("textfield[name=mid]").reset();
                                                     addmenu.down("textfield[name=menuname]").reset();
                                                     return false;
                                                 } else {
                                                     Ext.Msg.alert("友情提示", resObj.obj);
                                                     return false;
                                                 }
                                             } else {
                                                 Ext.Msg.alert('友情提示', "请检查增加用户的数据");
                                                 return false;
                                             }
                                             return false;
                                         }
                                     },

                                  "panel[xtype=updatemenugrid] button[ref=updateMenu]" : {
										 click : function(btn) {
												 var grid = btn.up("panel[xtype=updatemenugrid]");
												 var records = grid.getSelectionModel().getSelection();
												 if (records.length != 1) {
													 Ext.Msg.alert('提示', '请选择一个菜单！');
													 return false;
												 };
												 var window = Ext.create('Ext.window.Window', {
															 title : '修改菜单信息',
															 height : 340,
															 width : 580,
															 constrain : true,
															 maximizable : true,
															 layout : 'fit',
															 fixed : true,
															 modal : true,
															 items : {
																 xtype : 'updatemenuform',
																 id : 'updatemenuform'
															 }
														 });
												 var form = window.down("panel[xtype=updatemenuform]");
												 form.loadRecord(records[0]);
												 window.show();
												 return false;
											 }
										 },

										 "panel[xtype=updatemenuform] button[ref=updateMenu]" : {
														 click : function(btn) {
															 var updatemenuform = btn.up("panel[xtype=updatemenuform]");
															 var formObj = updatemenuform.getForm();
															 var params = self.getFormValue(formObj);
															 if (formObj.isValid()) {
																 var resObj2 = self.ajax({
																			 url : "menu/updateMenu",
																			 params : params
																		 });
																 if (resObj2.success) {
																	 self.msgbox("菜单修改成功");
																	 Ext.ComponentQuery.query("panel[xtype=updatemenugrid] component[xtype=pagingtoolbar]")[0].moveFirst();
																	 btn.ownerCt.ownerCt.ownerCt.close();
																	 return false;
																 } else {
																	 Ext.Msg.alert("友情提示", resObj.obj);
																	 return false;
																 }
															 } else {
																 Ext.Msg.alert('友情提示', "请检查要修改菜单的数据");
																 return false;
															 }
															 return false;
														 }
													 },

                                "deletemenugrid button[ref=deleteMenu]" : {
										 click : function(btn) {
											 var grid = btn.up("panel[xtype=deletemenugrid]");
											 var records = grid.getSelectionModel().getSelection();
											 var autoids = new Array();
											 for (var i = 0; i < records.length; i++) {
												 autoids.push(records[i].get('autoid'));
											 }
											 Ext.Msg.confirm( "菜单删除确认","<center><h3>确定要删除选中的菜单吗？<h3></center>",
												 function(result) {
													 if (result == "yes") {
														 var resObj = self
																 .ajax({
																	 url : "menu/deleteMenu",
																	 params : {
																		 autoids : autoids.join(",")
																	 }
																 });
														 if (resObj.success) {
															 grid.getStore().load();
															 self.msgbox("删除成功");
															 return false;
														 } else {
															 Ext.Msg.alert('友情提示',resObj.obj);
															 return false;
														 }
														 return false;
													 } else {
														 return false;
													 }
												 });
										 }
									 }

                                    });
                                  },
						views : ["core.basicinfomanage.menumanage.view.AddMenu",
								"core.basicinfomanage.menumanage.view.UpdateMenuForm",
								"core.basicinfomanage.menumanage.view.DeleteMenuGrid",
								"core.basicinfomanage.menumanage.view.UpdateMenuGrid",
								"core.basicinfomanage.menumanage.view.MenuGrid"],
						stores : ["core.basicinfomanage.menumanage.store.MenuStore",
									"core.basicinfomanage.menumanage.store.MenuNodeStore"],
						models : ["core.basicinfomanage.menumanage.model.MenuModel",
									"core.basicinfomanage.menumanage.model.MenuNode"],
						deleteCheckEdit : function() {
								var grid = Ext.ComponentQuery.query("panel[xtype=deletemenugrid]")[0];
								var num = grid.getSelectionModel().getSelection().length;
								var deleteMenu = Ext.ComponentQuery.query("panel[xtype=deletemenugrid] button[ref=deleteMenu]")[0];
								if (deleteMenu != null) {
									deleteMenu.setDisabled(num == 0);
								}
							},
							updateCheckEdit : function() {
								var grid = Ext.ComponentQuery.query("panel[xtype=updatemenugrid]")[0];
								var num = grid.getSelectionModel().getSelection().length;
								var updateMenu = Ext.ComponentQuery.query("panel[xtype=updatemenugrid] button[ref=updateMenu]")[0];
								if (updateMenu != null) {
									updateMenu.setDisabled(num != 1);
								}
							}
				});