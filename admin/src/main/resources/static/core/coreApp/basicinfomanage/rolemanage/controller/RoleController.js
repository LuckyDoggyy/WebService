Ext.define("core.basicinfomanage.rolemanage.controller.RoleController",
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

//						"panel[xtype=deleterolegrid]" : {
//                            select : this.deleteCheckEdit,
//                            deselect : this.deleteCheckEdit
//                        },

                        "panel[xtype=updaterolegrid]" : {
                            select : this.updateCheckEdit,
                            deselect : this.updateCheckEdit
                        },
                        "panel[xtype=setrolemenugrid]" : {
                            select : this.setMenuCheckEdit,
                            deselect : this.setMenuCheckEdit
                        },

                    "panel[xtype=addrole] button[ref=addrole]" : {
                        click : function(btn) {
                            var addrolepanel = btn.up("panel[xtype=addrole]");
                            var formObj = addrolepanel.getForm();
                            var params = self.getFormValue(formObj);
                            if (formObj.isValid()) {
                                var resObj = self.ajax({
                                            url : "role/addRole",
                                            params : params
                                        });
                                if (resObj.success) {
                                    self.msgbox('添加角色成功');
                                    addrolepanel.down("textfield[name=name]").reset();
                                    return false;
                                } else {
                                    Ext.Msg.alert("友情提示", "角色添加异常");
                                    return false;
                                }
                            } else {
                                Ext.Msg.alert('友情提示', "请检查增加角色的数据");
                                return false;
                            }
                            return false;
                        }
                    },

                    "updaterolegrid button[ref=updateRole]" : {
                            click : function(btn) {
                                    var grid = btn.up("panel[xtype=updaterolegrid]");
                                    var records = grid.getSelectionModel().getSelection();
                                    if (records.length != 1) {
                                        Ext.Msg.alert('提示', '请选择一个角色！');
                                        return false;
                                    };
                                    var window = Ext.create('Ext.window.Window', {
                                                title : '修改角色',
                                                height : 240,
                                                width : 450,
                                                constrain : true,
                                                maximizable : true,
                                                layout : 'fit',
                                                fixed : true,
                                                modal : true,
                                                items : {
                                                    xtype : 'updateroleform',
                                                    id : 'updateroleform'
                                                }
                                            });
                                    var form = window.down("panel[xtype=updateroleform]");
                                    form.loadRecord(records[0]);
                                    window.show();
                                    return false;
                                }
                            },

                            "panel[xtype=updateroleform] button[ref=updateRole]" : {
                            				click : function(btn) {
                            					var updateroleform = btn.up("panel[xtype=updateroleform]");
                            					var formObj = updateroleform.getForm();
                            					var params = self.getFormValue(formObj);
                            					if (formObj.isValid()) {
                            						var resObj2 = self.ajax({
                            									url : "role/updateRole",
                            									params : params
                            								});
                            						if (resObj2.success) {
                            							self.msgbox("修改成功");
                            							Ext.ComponentQuery.query("panel[xtype=updaterolegrid] component[xtype=pagingtoolbar]")[0].moveFirst();
                            							btn.ownerCt.ownerCt.ownerCt.close();
                            							return false;
                            						} else {
                            							Ext.Msg.alert("友情提示", "修改失败");
                            							return false;
                            						}
                            					} else {
                            						Ext.Msg.alert('友情提示', "请检查要修改角色的数据");
                            						return false;
                            					}
                            					return false;
                            				}
                            			},

                           "setrolemenugrid button[ref=setroleright]" : {
                                click : function(btn) {
                                    var grid = btn.ownerCt.ownerCt;
                                    var records = grid.getSelectionModel().getSelection();
                                    var rid=records[0].get("rid");
                                    var rname=records[0].get("rname");
                                    var window = Ext.create('Ext.window.Window', {
                                            title : '角色权限',
                                            height : 680,
                                            width : 660,
                                            constrain : true,
                                            maximizable : true,
                                            layout : 'fit',
                                            fixed : true,
                                            modal : true,
                                            items : {
                                                xtype : 'menutree',
                                                id : 'menutree'
                                            }
                                        });
                                var ridtxt = Ext.ComponentQuery.query("panel[xtype=menutree] textfield[name=rid]")[0];
                                ridtxt.setValue(rid);
                                ridtxt.setReadOnly(true);
                                var rnametxt = Ext.ComponentQuery.query("panel[xtype=menutree] textfield[name=rname]")[0];
                                rnametxt.setValue(rname);
                                rnametxt.setReadOnly(true);

                                var store=window.down("panel[xtype=menutree]").getStore();
                                proxy = store.getProxy();
                                proxy.extraParams = {
                                    rid : rid
                                };
                                store.load();
                                window.show();
                                return false;
                                }
                            },

                             "panel[xtype=menutree] button[ref=saveMenu]" : {
                                    click : function(btn) {
                                    var tree = btn.up("panel[xtype=menutree]");
                                    nodes=tree.getChecked();
                                    var mids = new Array();
                                    for (var i = 0; i < nodes.length; i++) {
                                            if(nodes[i].isLeaf()){
                                                mids.push(nodes[i].internalId);
                                            }
                                        }
                                    var rid = tree.down("textfield[name=rid]").getValue();
                                   Ext.Msg.confirm("角色菜单修改确认","<center><h3>确定要修改角色的菜单吗？<h3></center>",
                                           function(result) {
                                               if (result == "yes") {
                                                   var resObj = self
                                                           .ajax({
                                                               url : "role/saveRoleMenu",
                                                               params : {
                                                                   mids : mids.join(","),
                                                                   rid: rid
                                                               }
                                                           });
                                                   if (resObj.success) {
                                                       var store=tree.getStore();
                                                       proxy = store.getProxy();
                                                       proxy.extraParams = {
                                                           rid : rid
                                                       };
                                                       store.load();
                                                       self.msgbox("修改成功");
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
                                    return false;
                                    }
                                }
                        });
					},
            views : ["core.basicinfomanage.rolemanage.view.RoleGrid",
                    "core.basicinfomanage.rolemanage.view.DeleteRoleGrid",
                    "core.basicinfomanage.rolemanage.view.UpdateRoleGrid",
                    "core.basicinfomanage.rolemanage.view.SetRoleMenuGrid",
                    "core.basicinfomanage.rolemanage.view.UpdateRoleForm",
                    "core.basicinfomanage.rolemanage.view.AddRole",
                    "core.basicinfomanage.rolemanage.view.MenuTree",
                    "core.basicinfomanage.rolemanage.view.RoleForm"],
            stores : ["core.basicinfomanage.rolemanage.store.RoleStore",
                    "core.basicinfomanage.rolemanage.store.MenuTreeStore"],
            models : ["core.basicinfomanage.rolemanage.model.RoleModel",
                    "core.basicinfomanage.rolemanage.model.MenuTreeModel"],
            deleteCheckEdit : function() {
                var grid = Ext.ComponentQuery.query("panel[xtype=deleterolegrid]")[0];
                var num = grid.getSelectionModel().getSelection().length;
                var deleteRole = Ext.ComponentQuery.query("panel[xtype=deleterolegrid] button[ref=deleteRole]")[0];
                if (deleteRole != null) {
                    deleteRole.setDisabled(num == 0);
                }
            },
            updateCheckEdit : function() {
                var grid = Ext.ComponentQuery.query("panel[xtype=updaterolegrid]")[0];
                var num = grid.getSelectionModel().getSelection().length;
                var updateRole = Ext.ComponentQuery.query("panel[xtype=updaterolegrid] button[ref=updateRole]")[0];
                if (updateRole != null) {
                    updateRole.setDisabled(num != 1);
                }
            },
            setMenuCheckEdit : function() {
                var grid = Ext.ComponentQuery.query("panel[xtype=setrolemenugrid]")[0];
                var num = grid.getSelectionModel().getSelection().length;
                var setroleright = Ext.ComponentQuery.query("panel[xtype=setrolemenugrid] button[ref=setroleright]")[0];
                if (setroleright != null) {
                    setroleright.setDisabled(num != 1);
                }
            }
        });