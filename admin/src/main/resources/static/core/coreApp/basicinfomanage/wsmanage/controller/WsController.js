Ext.define("core.basicinfomanage.wsmanage.controller.WsController",
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
						    "panel[xtype=updatewsgrid]" : {
                                select : this.updateCheckEdit,
                                deselect : this.updateCheckEdit
                            },

                             "panel[xtype=deletewsgrid]" : {
                                select : this.deleteCheckEdit,
                                deselect : this.deleteCheckEdit
                            },

                            "wsgrid button[ref=searchWs]" : {
                                click : function(btn) {
                                    var tbar = btn.ownerCt;
                                    var sid = tbar.down("textfield[name=sid]").getValue();
                                    var serviceName = tbar.down("textfield[name=serviceName]").getValue();
                                    var grid = tbar.ownerCt;
                                    var _store = grid.getStore();
                                    proxy = _store.getProxy();
                                    proxy.extraParams = {
                                        sid : sid,
                                        serviceName : serviceName
                                    };
                                    _store.loadPage(1);
                                    return false;
                                }
                            },

                            "updatewsgrid button[ref=searchWs]" : {
                                click : function(btn) {
                                    var tbar = btn.ownerCt;
                                    var sid = tbar.down("textfield[name=sid]").getValue();
                                    var serviceName = tbar.down("textfield[name=serviceName]").getValue();
                                    var grid = tbar.ownerCt;
                                    var _store = grid.getStore();
                                    proxy = _store.getProxy();
                                    proxy.extraParams = {
                                        sid : sid,
                                        serviceName : serviceName
                                    };
                                    _store.loadPage(1);
                                    return false;
                                }
                            },
                            "deletewsgrid button[ref=searchWs]" : {
                                    click : function(btn) {
                                        var tbar = btn.ownerCt;
                                        var sid = tbar.down("textfield[name=sid]").getValue();
                                        var serviceName = tbar.down("textfield[name=serviceName]").getValue();
                                        var grid = tbar.ownerCt;
                                        var _store = grid.getStore();
                                        proxy = _store.getProxy();
                                        proxy.extraParams = {
                                            sid : sid,
                                            serviceName : serviceName
                                        };
                                        _store.loadPage(1);
                                        return false;
                                    }
                                },

                              "panel[xtype=addws] button[ref=addWS]" : {
                                         click : function(btn) {
                                             var addws = btn.up("panel[xtype=addws]");
                                             var formObj = addws.getForm();
                                             var params = self.getFormValue(formObj);
                                             if (formObj.isValid()) {
                                                 var resObj = self.ajax({
                                                             url : "ws/addWs",
                                                             params : params
                                                         });
                                                 if (resObj.success) {
                                                     self.msgbox(resObj.obj);
                                                     addws.down("textfield[name=serviceName]").reset();
                                                     addws.down("textfield[name=url]").reset();
                                                     addws.down("textarea[name=targetNamespace]").reset();
                                                     addws.down("textarea[name=method]").reset();
                                                     addws.down("textarea[name=remarks]").reset();
                                                     return false;
                                                 } else {
                                                     Ext.Msg.alert("友情提示", resObj.obj);
                                                     return false;
                                                 }
                                             } else {
                                                 Ext.Msg.alert('友情提示', "请检查增加服务的数据");
                                                 return false;
                                             }
                                             return false;
                                         }
                                     },


                               "panel[xtype=updatewsgrid] button[ref=updateService]" : {
                                        click : function(btn) {
                                                var grid = btn.up("panel[xtype=updatewsgrid]");
                                                var records = grid.getSelectionModel().getSelection();
                                                if (records.length != 1) {
                                                    Ext.Msg.alert('提示', '请选择一个服务！');
                                                    return false;
                                                };
                                                var window = Ext.create('Ext.window.Window', {
                                                            title : '修改服务信息',
                                                            height : 320,
                                                            width : 550,
                                                            constrain : true,
                                                            maximizable : true,
                                                            layout : 'fit',
                                                            fixed : true,
                                                            modal : true,
                                                            items : {
                                                                xtype : 'updatewsform',
                                                                id : 'updatewsform'
                                                            }
                                                        });
                                                var form = window.down("panel[xtype=updatewsform]");
                                                form.loadRecord(records[0]);
                                                window.show();
                                                return false;
                                            }
                                        },

                            "panel[xtype=updatewsform] button[ref=updateWs]" : {
                                    click : function(btn) {
                                        var updatewsform = btn.up("panel[xtype=updatewsform]");
                                        var formObj = updatewsform.getForm();
                                        var params = self.getFormValue(formObj);
                                        if (formObj.isValid()) {
                                            var resObj = self.ajax({
                                                        url : "ws/updateWs",
                                                        params : params
                                                    });
                                            if (resObj.success) {
                                                self.msgbox(resObj.obj);
                                                Ext.ComponentQuery.query("panel[xtype=updatewsgrid] component[xtype=pagingtoolbar]")[0].moveFirst();
                                                btn.ownerCt.ownerCt.ownerCt.close();
                                                return false;
                                            } else {
                                                Ext.Msg.alert("友情提示", resObj.obj);
                                                return false;
                                            }
                                        } else {
                                            Ext.Msg.alert('友情提示', "请检查要修改服务的数据");
                                            return false;
                                        }
                                        return false;
                                    }
                                },


                            "deletewsgrid button[ref=deleteService]" : {
                                click : function(btn) {
                                    var grid = btn.up("panel[xtype=deletewsgrid]");
                                    var records = grid.getSelectionModel().getSelection();
                                    var sids = new Array();
                                    for (var i = 0; i < records.length; i++) {
                                        sids.push(records[i].get('sid'));
                                    }
                                    Ext.Msg.confirm( "服务删除确认","<center><h3>确定要删除选中的服务吗？<h3></center>",
                                        function(result) {
                                            if (result == "yes") {
                                                var resObj = self
                                                        .ajax({
                                                            url : "ws/deleteWs",
                                                            params : {
                                                                sids : sids.join(",")
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
                            }
						});
					},
					views : [
							"core.basicinfomanage.wsmanage.view.WsGrid",
							"core.basicinfomanage.wsmanage.view.UpdateWsGrid",
							"core.basicinfomanage.wsmanage.view.DeleteWsGrid",
							"core.basicinfomanage.wsmanage.view.AddWS",
							"core.basicinfomanage.wsmanage.view.UpdateWsForm"],
					stores : ["core.basicinfomanage.wsmanage.store.WsStore"],
					models : ["core.basicinfomanage.wsmanage.model.WsModel"],
					updateCheckEdit : function() {
						var grid = Ext.ComponentQuery.query("panel[xtype=updatewsgrid]")[0];
						var num = grid.getSelectionModel().getSelection().length;
						var updateWs = Ext.ComponentQuery.query("panel[xtype=updatewsgrid] button[ref=updateService]")[0];
						if (updateWs != null) {
							updateWs.setDisabled(num != 1);
						}
					},
					deleteCheckEdit : function() {
						var grid = Ext.ComponentQuery.query("panel[xtype=deletewsgrid]")[0];
						var num = grid.getSelectionModel().getSelection().length;
						var deleteWs = Ext.ComponentQuery.query("panel[xtype=updatewsgrid] button[ref=deleteService]")[0];
						if (deleteWs != null) {
							deleteWs.setDisabled(num == 0);
						}
					}
				});