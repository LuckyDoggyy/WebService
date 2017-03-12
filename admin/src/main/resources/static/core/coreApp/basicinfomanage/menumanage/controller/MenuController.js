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
                                                     Ext.Msg.alert("友情提示", "用户添加异常");
                                                     return false;
                                                 }
                                             } else {
                                                 Ext.Msg.alert('友情提示', "请检查增加用户的数据");
                                                 return false;
                                             }
                                             return false;
                                         }
                                     }
                                    });
                                  },

						views : ["core.basicinfomanage.menumanage.view.AddMenu",
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
								var updateMenu = Ext.ComponentQuery.query("panel[xtype=updatepeoplegrid] button[ref=updateMenu]")[0];
								if (updateMenu != null) {
									updateMenu.setDisabled(num != 1);
								}
							}
				});