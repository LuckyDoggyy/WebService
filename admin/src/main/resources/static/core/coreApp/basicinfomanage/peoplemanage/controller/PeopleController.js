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

		    "panel[xtype=peoplegrid]" : {
        				select : this.checkEdit,
        				deselect : this.checkEdit
        	 },


        	  "peoplegrid button[ref=addPeople]" : {
                     click : function(btn) {
                         var window = Ext.create('Ext.window.Window', {
                                     title : '增加用户',
                                     height : 150,
                                     width : 300,
                                     constrain : true,
                                     maximizable : true,
                                     layout : 'fit',
                                     fixed : true,
                                     modal : true,
                                     items : {
                                         xtype : 'addpeopleform',
                                         id : 'addpeopleform'
                                     }
                                 });
                         window.show();
                         return false;
                     }
                 },
                  "panel[xtype=addpeopleform] button[ref=return]" : {
                         click : function(btn) {
                             btn.ownerCt.ownerCt.ownerCt.close();
                             return false;
                         }
                    },

                  "panel[xtype=addpeopleform] button[ref=addPeople]" : {
                         click : function(btn) {
                             var addpeopleform = btn.up("panel[xtype=addpeopleform]");
                             var formObj = addpeopleform.getForm();
                             var params = self.getFormValue(formObj);
                             if (formObj.isValid()) {
                                 var resObj = self.ajax({
                                             url : "user/addUser",
                                             params : params
                                         });
                                 if (resObj.success) {
                                     self.msgbox('用户添加成功');
                                     Ext.ComponentQuery.query("panel[xtype=peoplegrid] component[xtype=pagingtoolbar]")[0].moveFirst();
                                     btn.ownerCt.ownerCt.ownerCt.close();
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
                     },

                     "peoplegrid button[ref=deletePeople]" : {
                             click : function(btn) {
                                 var grid = btn.up("panel[xtype=peoplegrid]");
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

            "peoplegrid button[ref=checkuserservice]" : {
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
			"core.basicinfomanage.peoplemanage.view.AddPeopleForm",
			"core.basicinfomanage.peoplemanage.view.UserServiceGrid",
			"core.basicinfomanage.peoplemanage.view.AllWsGrid",
			"core.basicinfomanage.peoplemanage.view.UserServiceMainLayout",
			"core.basicinfomanage.peoplemanage.view.UpdatePeopleForm",
			"core.basicinfomanage.peoplemanage.view.ChangeGangWeiForm"],

	stores : ["core.basicinfomanage.peoplemanage.store.PeopleStore",
	            "core.basicinfomanage.peoplemanage.store.WsOwnerStore",
	            "core.basicinfomanage.peoplemanage.store.UserRoleStore",
	            "core.basicinfomanage.wsmanage.store.WsStore"],
	models : ["core.basicinfomanage.peoplemanage.model.WsOwnerModel",
	            "core.basicinfomanage.peoplemanage.model.PeopleModel",
	            "core.basicinfomanage.peoplemanage.model.UserRoleModel",
	            "core.basicinfomanage.wsmanage.model.WsModel"],
	checkEdit : function() {
		var grid = Ext.ComponentQuery.query("panel[xtype=peoplegrid]")[0];
		var num = grid.getSelectionModel().getSelection().length;
		var deletePeople = Ext.ComponentQuery.query("panel[xtype=peoplegrid] button[ref=deletePeople]")[0];
//		var updatePeople = Ext.ComponentQuery.query("panel[xtype=peoplegrid] button[ref=updatePeople]")[0];
		var checkList = Ext.ComponentQuery.query("panel[xtype=peoplegrid] button[ref=checkuserservice]")[0];
		if (deletePeople != null) {
			deletePeople.setDisabled(num == 0);
		}
//		if (updatePeople != null) {
//			updatePeople.setDisabled(num != 1);
//		}
		if (checkList != null) {
			checkList.setDisabled(num != 1);
		}
	}
});