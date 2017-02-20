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
                            },

                           "rolegrid button[ref=setroleright]" : {
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
                                    console.log(mids);

                                    var rid = tree.down("textfield[name=rid]").getValue();
                                    console.log(rid);

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
            views : ["core.systemmanage.rolemanage.view.RoleGrid",
                    "core.systemmanage.rolemanage.view.AddRoleForm",
                    "core.systemmanage.rolemanage.view.MenuTree",
                    "core.systemmanage.rolemanage.view.RoleForm"],
            stores : ["core.systemmanage.rolemanage.store.RoleStore",
                    "core.systemmanage.rolemanage.store.MenuTreeStore"],
            models : ["core.systemmanage.rolemanage.model.RoleModel",
                    "core.systemmanage.rolemanage.model.MenuTreeModel"],
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