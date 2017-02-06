Ext.define("core.systemmanage.rolemanage.controller.RoleController",
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
						var operator = {};

						this.control({

						 "panel[xtype=rolegrid]" : {
                            select : this.checkEdit,
                            deselect : this.checkEdit
                        },

						    "rolegrid button[ref=addRole]" : {
                                click : function(btn) {
                                    var window = Ext.create('Ext.window.Window', {
                                                title : '增加角色',
                                                height : 120,
                                                width : 300,
                                                constrain : true,
                                                maximizable : true,
                                                // minimizable :true,
                                                layout : 'fit',
                                                fixed : true,
                                                modal : true,
                                                items : {
                                                    xtype : 'addroleform',
                                                    id : 'addroleform'
                                                }
                                            });
                                    window.show();
                                    return false;
                                }
                            },

                            "panel[xtype=addroleform] button[ref=return]" : {
                                click : function(btn) {
                                    btn.ownerCt.ownerCt.ownerCt.close();
                                    return false;
                                }
                            },
                            "panel[xtype=addroleform] button[ref=addrole]" : {
                                click : function(btn) {
                                    var addroleform = btn.up("panel[xtype=addroleform]");
                                    var formObj = addroleform.getForm();
                                    var params = self.getFormValue(formObj);
                                    if (formObj.isValid()) {
                                        var resObj = self.ajax({
                                                    url : "role/addRole",
                                                    params : params
                                                });
                                        if (resObj.success) {
                                            self.msgbox('添加角色成功');
                                            Ext.ComponentQuery.query("panel[xtype=rolegrid] component[xtype=pagingtoolbar]")[0].moveFirst();
                                            btn.ownerCt.ownerCt.ownerCt.close();
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
                            }
                        });
					},
					views : ["core.systemmanage.rolemanage.view.RoleGrid",
                            "core.systemmanage.rolemanage.view.AddRoleForm",
                            "core.systemmanage.rolemanage.view.RoleForm"],
					stores : ["core.systemmanage.rolemanage.store.RoleStore"],
					models : ["core.systemmanage.rolemanage.model.RoleModel"],
					checkEdit : function() {
						var grid = Ext.ComponentQuery.query("panel[xtype=rolegrid]")[0];
						var num = grid.getSelectionModel().getSelection().length;
						var deleteRole = Ext.ComponentQuery.query("panel[xtype=rolegrid] button[ref=deleteRole]")[0];
						var setroleright = Ext.ComponentQuery.query("panel[xtype=rolegrid] button[ref=setroleright]")[0];
						if (deleteRole != null) {
							deleteRole.setDisabled(num == 0);
						}
						if (setroleright != null) {
							setroleright.setDisabled(num != 1);
						}
					}
				});