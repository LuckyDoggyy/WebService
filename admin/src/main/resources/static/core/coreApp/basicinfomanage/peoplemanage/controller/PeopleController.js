Ext.define("core.basicinfomanage.peoplemanage.controller.PeopleController",
    {
	extend : "Ext.app.Controller",
	mixins : {
		suppleUtil : "core.util.SuppleUtil",
		messageUtil : "core.util.MessageUtil",
		formUtil : "core.util.FormUtil"
	},
	init : function() {
		var self = this;

		this.control({

		    "panel[xtype=setrolepeoplegrid]" : {
        				select : this.roleCheckEdit,
        				deselect : this.roleCheckEdit
        	 },
        	 "panel[xtype=deletepeoplegrid]" : {
                        select : this.deleteCheckEdit,
                        deselect : this.deleteCheckEdit
             },
             "panel[xtype=updatepeoplegrid]" : {
                        select : this.updateCheckEdit,
                        deselect : this.updateCheckEdit
             },

                  "panel[xtype=addpeople] button[ref=addPeople]" : {
                         click : function(btn) {
                             var addpeople = btn.up("panel[xtype=addpeople]");
                             var formObj = addpeople.getForm();
                             var params = self.getFormValue(formObj);

                             if (formObj.isValid()) {
                                 var resObj = self.ajax({
                                             url : "user/addUser",
                                             params : params
                                         });
                                 if (resObj.success) {
                                     self.msgbox('用户添加成功');
                                     addpeople.down("textfield[name=account]").reset();
                                     addpeople.down("textfield[name=nickName]").reset();
                                     addpeople.down("textarea[name=remarks]").reset();
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

                         "panel[xtype=updatepeoplegrid] button[ref=updatePeople]" : {
                                click : function(btn) {
                                        var grid = btn.up("panel[xtype=updatepeoplegrid]");
                                        var records = grid.getSelectionModel().getSelection();
                                        if (records.length != 1) {
                                            Ext.Msg.alert('提示', '请选择一个用户！');
                                            return false;
                                        };
                                        var window = Ext.create('Ext.window.Window', {
                                                    title : '修改用户信息',
                                                    height : 240,
                                                    width : 450,
                                                    constrain : true,
                                                    maximizable : true,
                                                    layout : 'fit',
                                                    fixed : true,
                                                    modal : true,
                                                    items : {
                                                        xtype : 'updatepeopleform',
                                                        id : 'updatepeopleform'
                                                    }
                                                });
                                        var form = window.down("panel[xtype=updatepeopleform]");
                                        form.loadRecord(records[0]);
                                        window.show();
                                        return false;
                                    }
                                },

                                "panel[xtype=updatepeopleform] button[ref=updatePeople]" : {
                                                click : function(btn) {
                                                    var updatepeopleform = btn.up("panel[xtype=updatepeopleform]");
                                                    var formObj = updatepeopleform.getForm();
                                                    var params = self.getFormValue(formObj);
                                                    if (formObj.isValid()) {
                                                        var resObj2 = self.ajax({
                                                                    url : "user/updateUser",
                                                                    params : params
                                                                });
                                                        if (resObj2.success) {
                                                            self.msgbox("修改成功");
                                                            Ext.ComponentQuery.query("panel[xtype=updatepeoplegrid] component[xtype=pagingtoolbar]")[0].moveFirst();
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

                     "deletepeoplegrid button[ref=deletePeople]" : {
                             click : function(btn) {
                                 var grid = btn.up("panel[xtype=deletepeoplegrid]");
                                 var records = grid.getSelectionModel().getSelection();
                                 var uids = new Array();
                                 for (var i = 0; i < records.length; i++) {
                                     uids.push(records[i].get('uid'));
                                 }
                                 Ext.Msg.confirm( "用户删除确认","<center><h3>确定要删除选中的用户吗？<h3></center>",
                                     function(result) {
                                         if (result == "yes") {
                                             var resObj = self
                                                     .ajax({
                                                         url : "user/deleteUser",
                                                         params : {
                                                             uids : uids.join(",")
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
                         },
            /**
			 * 查找人员信息
			 */
			"peoplegrid button[ref=searchPeople]" : {
				click : function(btn) {
					var tbar = btn.ownerCt;
					var uid = tbar.down("textfield[name=uid]").getValue();
					var name = tbar.down("textfield[name=name]").getValue();
					var grid = tbar.ownerCt;
					var _store = grid.getStore();
					proxy = _store.getProxy();
					proxy.extraParams = {
						uid : uid,
						name : name
					};
					_store.loadPage(1);
					return false;
				}
			},
			"deletepeoplegrid button[ref=searchPeople]" : {
                    click : function(btn) {
                        var tbar = btn.ownerCt;
                        var uid = tbar.down("textfield[name=uid]").getValue();
                        var name = tbar.down("textfield[name=name]").getValue();
                        var grid = tbar.ownerCt;
                        var _store = grid.getStore();
                        proxy = _store.getProxy();
                        proxy.extraParams = {
                            uid : uid,
                            name : name
                        };
                        _store.loadPage(1);
                        return false;
                    }
                },
            "updatepeoplegrid button[ref=searchPeople]" : {
                    click : function(btn) {
                        var tbar = btn.ownerCt;
                        var uid = tbar.down("textfield[name=uid]").getValue();
                        var name = tbar.down("textfield[name=name]").getValue();
                        var grid = tbar.ownerCt;
                        var _store = grid.getStore();
                        proxy = _store.getProxy();
                        proxy.extraParams = {
                            uid : uid,
                            name : name
                        };
                        _store.loadPage(1);
                        return false;
                    }
                },
                "setrolepeoplegrid button[ref=searchPeople]" : {
                        click : function(btn) {
                            var tbar = btn.ownerCt;
                            var uid = tbar.down("textfield[name=uid]").getValue();
                            var name = tbar.down("textfield[name=name]").getValue();
                            var grid = tbar.ownerCt;
                            var _store = grid.getStore();
                            proxy = _store.getProxy();
                            proxy.extraParams = {
                                uid : uid,
                                name : name
                            };
                            _store.loadPage(1);
                            return false;
                        }
                    },

			 "setrolepeoplegrid button[ref=checkRole]" : {
                    click : function(btn) {
                        var grid = btn.ownerCt.ownerCt;
                        var records = grid.getSelectionModel().getSelection();
                        var uid=records[0].get("uid");
                        var username=records[0].get("name");
                        var window = Ext.create(
                                    'Ext.window.Window', {
                                        title : '用户角色设置',
                                        constrain : true,
                                        maximizable : true,
                                        maximized : true,
                                        layout : 'border',
                                        fixed : true,
                                        modal : true,
                                        items : [{
                                                 region: 'west',
                                                 title: '用户当前角色&nbsp;&nbsp;&nbsp;&nbsp;  <font  size=4 color=red><b>'+'用户id:&nbsp;'+uid+'&nbsp;&nbsp;&nbsp;&nbsp;姓名:'+username+'</b></font>',
                                                 xtype: "userrolegrid",
                                                 width: 700
                                             },{
                                                 region: 'center',
                                                 title: '全部角色',
                                                 xtype: "allrolegrid"
                                             }]
                                    });
                        var store=window.down("panel[xtype=userrolegrid]").getStore();
                        proxy = store.getProxy();
                        proxy.extraParams = {
                            uid : uid
                        };
                        store.loadPage(1);

//                        var rolestore=window.down("panel[xtype=allrolegrid]").getStore();
//                        rolestore.loadPage(1);

                        window.show();
                        var button=window.down("panel[xtype=allrolegrid]").down("[xtype=toolbar]").down("button[ref=addforUser]");
                        button.html=uid;
                        return false;
                    }
                },



            "userrolegrid button[ref=deleteRole]" : {
                click : function(btn) {
                    var grid = btn.ownerCt.ownerCt;
                    var records = grid.getSelectionModel().getSelection();
                    if(records.length==0){
                        Ext.Msg.alert('友情提示',"请选择要删除的角色");
                        return false;
                    }
                    var autoids = new Array();
                    for (var i = 0; i < records.length; i++) {
                        autoids.push(records[i].get('autoid'));
                    }
                    Ext.Msg.confirm( "用户角色删除确认","<center><h3>确定要删除选中的角色吗？<h3></center>",
                            function(result) {
                                if (result == "yes") {
                                    var resObj = self
                                            .ajax({
                                                url : "user/removeUserRole",
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
                    return false;
                }
            },

                 "allrolegrid button[ref=addforUser]" : {
                        click : function(btn) {
                            var uid = btn.html;
                            var grid = btn.ownerCt.ownerCt;
                            var records = grid.getSelectionModel().getSelection();
                            if(records.length==0){
                                Ext.Msg.alert('友情提示',"请选择要添加的角色");
                                return false;
                            }
                            var rids = new Array();
                            for (var i = 0; i < records.length; i++) {
                                rids.push(records[i].get('rid'));
                            }
                            Ext.Msg.confirm( "添加角色确认","<center><h3>确定要给该用户添加选中的角色吗？<h3></center>",
                                    function(result) {
                                        if (result == "yes") {
                                            var resObj = self
                                                    .ajax({
                                                        url : "user/addUserRole",
                                                        params : {
                                                            rids : rids.join(","),
                                                            uid : uid
                                                        }
                                                    });
                                            if (resObj.success) {
                                                var userrolegrid = Ext.ComponentQuery.query("panel[xtype=userrolegrid]")[0];
                                                userrolegrid.getStore().load();
                                                self.msgbox("添加成功");
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
                    },



            "setrolepeoplegrid button[ref=checkuserservice]" : {
                click : function(btn) {
                    var grid = btn.ownerCt.ownerCt;
                    var records = grid.getSelectionModel().getSelection();
                    var uid=records[0].get("uid");
                    var username=records[0].get("name");

                    var window = Ext.create(
                                'Ext.window.Window', {
                                    title : '用户服务设置',
                                    constrain : true,
                                    maximizable : true,
                                    maximized : true,
                                    layout : 'border',
                                    fixed : true,
                                    modal : true,
                                    items : [{
                                             region: 'west',
                                             title: '用户当前可用服务&nbsp;&nbsp;&nbsp;&nbsp;  <font  size=4 color=red><b>'+'用户id:&nbsp;'+uid+'&nbsp;&nbsp;&nbsp;&nbsp;姓名:'+username+'</b></font>',
                                             xtype: "userservicegrid",
                                             width: 600
                                         },{
                                             region: 'center',
                                             title: '全部服务',
                                             xtype: "allwsgrid"
                                         }]
                                });
                    var store=window.down("panel[xtype=userservicegrid]").getStore();
                    proxy = store.getProxy();
                    proxy.extraParams = {
                        uid : uid
                    };
                    store.loadPage(1);
                    window.show();
                    var button=window.down("panel[xtype=allwsgrid]").down("[xtype=toolbar]").down("button[ref=addforUser]");
                    button.html=uid;
                    return false;
                }
            },








            "userservicegrid button[ref=deleteUserService]" : {
                click : function(btn) {
                    var grid = btn.ownerCt.ownerCt;
                    var records = grid.getSelectionModel().getSelection();
                    if(records.length==0){
                        Ext.Msg.alert('友情提示',"请选择要删除的服务");
                        return false;
                    }
                    var uid = records[0].get("uid");
                    var sids = new Array();
                    for (var i = 0; i < records.length; i++) {
                        sids.push(records[i].get('sid'));
                    }
                    Ext.Msg.confirm( "用户服务删除确认","<center><h3>确定要删除选中的服务吗？<h3></center>",
                            function(result) {
                                if (result == "yes") {
                                    var resObj = self
                                            .ajax({
                                                url : "wsowner/delete",
                                                params : {
                                                    sids : sids.join(","),
                                                    uid : uid
                                                }
                                            });
                                    if (resObj.success) {
                                        grid.getStore().load();
                                        self.msgbox(resObj.obj);
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
            },

             "allwsgrid button[ref=searchallWs]" : {
                    click : function(btn) {
                        var tbar = btn.ownerCt;
                        var sid = tbar.down("textfield[name=sid]").getValue();
                        var sname = tbar.down("textfield[name=sname]").getValue();
                        var grid = tbar.ownerCt;
                        var _store = grid.getStore();
                        proxy = _store.getProxy();
                        proxy.extraParams = {
                            sid : sid,
                            sname : sname
                        };
                        _store.loadPage(1);
                        return false;
                    }
                },

                 "allwsgrid button[ref=addforUser]" : {
                        click : function(btn) {
                            var uid = btn.html;
                            var grid = btn.ownerCt.ownerCt;
                            var records = grid.getSelectionModel().getSelection();
                            if(records.length==0){
                                Ext.Msg.alert('友情提示',"请选择要添加的服务");
                                return false;
                            }
                            var sids = new Array();
                            for (var i = 0; i < records.length; i++) {
                                sids.push(records[i].get('sid'));
                            }
                            Ext.Msg.confirm( "添加服务确认","<center><h3>确定要给该用户添加选中的服务吗？<h3></center>",
                                    function(result) {
                                        if (result == "yes") {
                                            var resObj = self
                                                    .ajax({
                                                        url : "wsowner/addServiceForUser",
                                                        params : {
                                                            sids : sids.join(","),
                                                            uid : uid
                                                        }
                                                    });
                                            if (resObj.success) {
                                                var userservicegrid = Ext.ComponentQuery.query("panel[xtype=userservicegrid]")[0];
                                                userservicegrid.getStore().load();
                                                self.msgbox("添加成功");
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
		return false;
	},
	views : ["core.basicinfomanage.peoplemanage.view.PeopleGrid",
	"core.basicinfomanage.peoplemanage.view.DeletePeopleGrid",
	"core.basicinfomanage.peoplemanage.view.UpdatePeopleGrid",
	"core.basicinfomanage.peoplemanage.view.setRolePeopleGrid",
			"core.basicinfomanage.peoplemanage.view.AddPeople",
			"core.basicinfomanage.peoplemanage.view.UserServiceGrid",
			"core.basicinfomanage.peoplemanage.view.AllWsGrid",
	        "core.basicinfomanage.peoplemanage.view.AllRoleGrid",
	        "core.basicinfomanage.peoplemanage.view.UserRoleGrid",
			"core.basicinfomanage.peoplemanage.view.UserServiceMainLayout",
			"core.basicinfomanage.peoplemanage.view.UpdatePeopleForm"],

	stores : ["core.basicinfomanage.peoplemanage.store.PeopleStore",
	            "core.basicinfomanage.peoplemanage.store.WsOwnerStore",
	            "core.basicinfomanage.peoplemanage.store.UserRoleStore",
	            "core.systemmanage.rolemanage.store.RoleStore",
	            "core.basicinfomanage.wsmanage.store.WsStore"],
	models : ["core.basicinfomanage.peoplemanage.model.WsOwnerModel",
	            "core.systemmanage.rolemanage.model.RoleModel",
	            "core.basicinfomanage.peoplemanage.model.PeopleModel",
	            "core.basicinfomanage.peoplemanage.model.UserRoleModel",
	            "core.basicinfomanage.wsmanage.model.WsModel"],
	deleteCheckEdit : function() {
		var grid = Ext.ComponentQuery.query("panel[xtype=deletepeoplegrid]")[0];
		var num = grid.getSelectionModel().getSelection().length;
		var deletePeople = Ext.ComponentQuery.query("panel[xtype=deletepeoplegrid] button[ref=deletePeople]")[0];
		if (deletePeople != null) {
			deletePeople.setDisabled(num == 0);
		}
	},
	updateCheckEdit : function() {
		var grid = Ext.ComponentQuery.query("panel[xtype=updatepeoplegrid]")[0];
		var num = grid.getSelectionModel().getSelection().length;
		var updatePeople = Ext.ComponentQuery.query("panel[xtype=updatepeoplegrid] button[ref=updatePeople]")[0];
		if (updatePeople != null) {
			updatePeople.setDisabled(num != 1);
		}
	},
	roleCheckEdit : function() {
    		var grid = Ext.ComponentQuery.query("panel[xtype=setrolepeoplegrid]")[0];
    		var num = grid.getSelectionModel().getSelection().length;
    		var checkList = Ext.ComponentQuery.query("panel[xtype=setrolepeoplegrid] button[ref=checkRole]")[0];
            if (checkList != null) {
                checkList.setDisabled(num != 1);
            }
    	}
});