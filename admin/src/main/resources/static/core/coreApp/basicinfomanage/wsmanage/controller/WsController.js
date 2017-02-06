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

						    "panel[xtype=banzupeoplegrid]" : {
                                select : this.checkEdit,
                                deselect : this.checkEdit
                            },

                            "wsgrid button[ref=searchWs]" : {
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

                            "wsgrid button[ref=addService]" : {
                                click : function(btn) {
                                    var window = Ext.create(
                                            'Ext.window.Window', {
                                                title : '增加服务',
                                                height : 300,
                                                width : 450,
                                                constrain : true,
                                                maximizable : true,
                                                layout : 'fit',
                                                fixed : true,
                                                modal : true,
                                                items : {
                                                    xtype : 'addwsform',
                                                    id : 'addwsform'
                                                }
                                            });
                                    window.show();
                                    return false;
                                }
                            },

                            "panel[xtype=addwsform] button[ref=return]" : {
                                click : function(btn) {
                                    btn.ownerCt.ownerCt.ownerCt.close();
                                    return false;
                                }
                            },


                            /**
                             * 删除
                             */
                            "wsgrid button[ref=deleteService]" : {
                                click : function(btn) {
                                    var grid = btn.up("panel[xtype=wsgrid]");
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
                                                            url : "service/delete",
                                                            params : {
                                                                sids : sids.join(",")
                                                            }
                                                        });
                                                if (resObj.success) {
                                                    grid.getStore() .load();
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
							"core.basicinfomanage.wsmanage.view.AddWsForm",
							"core.basicinfomanage.wsmanage.view.UpdateWsForm",
							"core.basicinfomanage.wsmanage.view.AddWsForm2"],
					stores : ["core.basicinfomanage.wsmanage.store.WsStore"],
					models : ["core.basicinfomanage.wsmanage.model.WsModel"],
					checkEdit : function() {
						var grid = Ext.ComponentQuery.query("panel[xtype=wsgrid]")[0];
						var num = grid.getSelectionModel().getSelection().length;
						var deleteWs = Ext.ComponentQuery.query("panel[xtype=wsgrid] button[ref=deleteService]")[0];
						var updateWs = Ext.ComponentQuery.query("panel[xtype=wsgrid] button[ref=updateService]")[0];
						if (deleteWs != null) {
							deleteWs.setDisabled(num == 0);
						}
						if (updateWs != null) {
							updateWs.setDisabled(num != 1);
						}
					}
				});