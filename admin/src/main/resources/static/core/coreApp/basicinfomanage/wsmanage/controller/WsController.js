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

                            'wsgrid actioncolumn': {
                                   itemclick: function (rec,node) {
                                   var sid= rec.get('sid');
                                   if (node.action == 'viewParam') {
                                       var window = Ext.create(
                                                   'Ext.window.Window', {
                                                       title : '服务参数',
                                                       height : 280,
                                                       width : 550,
                                                       constrain : true,
                                                       maximizable : true,
                                                       layout : 'fit',
                                                       fixed : true,
                                                       modal : true,
                                                       items : [{
                                                                region: 'center',
                                                                title: '参数',
                                                                xtype: "showwsparamsgrid"
                                                            }]
                                                   });
                                       var store=window.down("panel[xtype=showwsparamsgrid]").getStore();
                                       proxy = store.getProxy();
                                       proxy.extraParams = {sid : sid};
                                       store.load();
                                       window.show();
                                       return false;
                                   }else if(node.action == 'call'){
                                        var window = Ext.create('Ext.window.Window', {
                                            title : '服务调用',
                                           constrain : true,
                                           maximizable : true,
                                           maximized : true,
                                           layout : 'border',
                                           fixed : true,
                                           modal : true,
                                            items : [{
                                                     region: 'west',
                                                     xtype : 'wscallform',
                                                     width: 460
                                                 },{
                                                    region: 'center',
                                                    xtype : 'form',
                                                    layout: 'anchor',
                                                    defaults : {
                                                    		anchor : '100%'
                                                    	},
                                                    items:[{
                                                            xtype : 'textarea',
                                                            name: 'callResult',
                                                            height: 800
                                                            }]
                                                }]
                                        });
                                        var resObj = self.ajax({
                                                 url : "ws/listWsParam",
                                                 params : {
                                                     sid : sid
                                                 }
                                             });
                                        var form = window.down("panel[xtype=wscallform]");
                                        form.loadRecord(rec);
                                        resObj.rows.forEach(function(e){
                                            fd = new Ext.form.TextField({name: e.paramName,fieldLabel:e.paramName,allowBlank : true});
                                            //form.items.add(form.items.getCount()-1, fd);
                                            form.items.add(fd);
                                        });
                                        fd = new Ext.form.TextField({name: 'out',fieldLabel:'输出参数',allowBlank : true});
                                        form.items.add(fd);
                                        form.doLayout();
                                        window.show();
                                        return false;
                                   }else if(node.action == 'viewOutput'){
                                            var out= rec.get('output');
                                            console.log(out);
                                            var window = Ext.create('Ext.window.Window', {
                                                       title : '输出配置',
                                                       height : 280,
                                                      width : 550,
                                                      constrain : true,
                                                      maximizable : true,
                                                      layout : 'fit',
                                                      fixed : true,
                                                      modal : true,
                                                       items : [{
                                                                    region: 'center',
                                                                    xtype : 'form',
                                                                    layout: 'anchor',
                                                                    defaults : {
                                                                            anchor : '100%'
                                                                        },
                                                                    items:[{
                                                                            xtype : 'textarea',
                                                                            style:"wrap:soft;",
                                                                            name: 'output',
                                                                            height: 800
                                                                            }]
                                                                }]
                                                    });
                                            var area = window.down("panel[xtype=form]").down("textarea[name=output]");
                                            area.setValue(out);
                                            window.show();
                                            return false;

                                   }
                                }
                             },

                            "panel[xtype=wscallform] button[ref=call]" : {
                                         click : function(btn) {
                                             var wscallform = btn.up("panel[xtype=wscallform]");
                                             var formObj = wscallform.getForm();
                                             var params = self.getFormValue(formObj);
                                             var resObj = self.ajax({
                                                              url : "ws/listWsParam",
                                                              params : {
                                                                  sid : params.sid
                                                              }
                                                          });
                                             var serviceParams = new Array();
                                             resObj.rows.forEach(function(e){
                                                var value=wscallform.down("textfield[name="+e.paramName+"]").getValue();
                                                serviceParams.push({'name':e.paramName,'value':value});
                                             });
                                             var output=wscallform.down("textfield[name=out]").getValue();
                                             var paramStr=JSON.stringify(serviceParams);
                                             params['sid']=params.sid;
                                             params['callParams']=paramStr;
                                             params['out']=output;
                                             if (formObj.isValid()) {
                                                 var res = self.ajax({
                                                             url : "ws/callWs",
                                                             params : params
                                                         });
                                                 if (res.success) {
                                                     wscallform.ownerCt.down("panel[xtype=form]").down("textfield[name=callResult]").setValue(res.obj);
                                                     return false;
                                                 } else {
                                                     wscallform.ownerCt.down("panel[xtype=form]").down("textfield[name=callResult]").setValue(res.obj);
                                                     Ext.Msg.alert("友情提示", "服务调用失败");
                                                     return false;
                                                 }
                                             } else {
                                                 Ext.Msg.alert('友情提示', "请检查增加服务的数据");
                                                 return false;
                                             }
                                             return false;
                                         }
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
                                             var store=Ext.ComponentQuery.query("panel[xtype=addws] component[xtype=grid]")[0].getStore();
                                             var serviceParams = new Array();
                                             store.each(function(record){
                                                var name=record.get('paramName');
                                                if(name!=''){
                                                    serviceParams.push({'paramName':name,'alies':record.get('alies'),'remark':record.get('remark')});
                                                }
                                             })
                                             var paramStr=JSON.stringify(serviceParams);
                                             params['serviceParams']=paramStr;
                                             if (formObj.isValid()) {
                                                 var resObj = self.ajax({
                                                             url : "ws/addWs",
                                                             params : params
                                                         });
                                                 if (resObj.success) {
                                                     self.msgbox(resObj.obj);
                                                     addws.down("textfield[name=serviceName]").reset();
                                                     addws.down("textfield[name=url]").reset();
                                                     addws.down("textfield[name=wsdlUrl]").reset();
                                                     addws.down("textfield[name=targetNamespace]").reset();
                                                     addws.down("textfield[name=method]").reset();
                                                     addws.down("textarea[name=remark]").reset();
                                                     store.removeAll();
                                                     return false
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
                                                            height : 400,
                                                            width : 880,
                                                            constrain : true,
                                                            maximizable : true,
                                                            layout : 'border',
                                                            fixed : true,
                                                            modal : true,
                                                            items : [{
                                                                     region: 'west',
                                                                     xtype : 'updatewsform',
                                                                     id : 'updatewsform',
                                                                     width: 600
                                                                 },{
                                                                    region: 'center',
                                                                    xtype : 'grid',
                                                                    columnLines : true,
                                                                    store: "core.basicinfomanage.wsmanage.store.WsParamStore",
                                                                    bbar : [{
                                                                            xtype : 'button',
                                                                            text : '添加',
                                                                            handler : function(button, e) {
                                                                                var data=[{'paramName':'','remark':''}];
                                                                                var store=button.ownerCt.ownerCt.getStore();
                                                                                var count=store.getCount();
                                                                                store.insert(count++,data);
                                                                                return false;
                                                                            }
                                                                         }, {
                                                                            xtype : 'button',
                                                                            text : '删除',
                                                                            handler :function(button, e) {
                                                                                    var grid=button.ownerCt.ownerCt;
                                                                                    var store = grid.getStore();
                                                                                    store.remove(grid.getSelectionModel().getSelection());
                                                                                    return false;
                                                                            }
                                                                        }],
                                                                    columns: [
                                                                        { header: '参数名',  dataIndex: 'paramName',editor: 'textfield' },
                                                                        { header: '别名',  dataIndex: 'alies',editor: 'textfield' },
                                                                        { header: '备注', dataIndex: 'remark',editor: 'textfield' }
                                                                    ],
                                                                    plugins: [ Ext.create('Ext.grid.plugin.RowEditing', {clicksToEdit: 1 })]
                                                                }]
                                                        });
                                                var form = window.down("panel[xtype=updatewsform]");
                                                form.loadRecord(records[0]);
                                                var store=window.down("panel[xtype=grid]").getStore();
                                                proxy = store.getProxy();
                                                proxy.extraParams = {sid : records[0].get('sid')};
                                                store.load();
                                                window.show();
                                                return false;
                                            }
                                        },

                            "panel[xtype=updatewsform] button[ref=updateWs]" : {
                                    click : function(btn) {
                                        var updatewsform = btn.up("panel[xtype=updatewsform]");
                                        var formObj = updatewsform.getForm();
                                        var params = self.getFormValue(formObj);
                                        var store=updatewsform.ownerCt.down("panel[xtype=grid]").getStore();
                                        var serviceParams = new Array();
                                        store.each(function(record){
                                            var name=record.get('paramName');
                                            if(name!=''){
                                                serviceParams.push({'autoid':record.get('autoid'),'paramName':name,'alies':record.get('alies'),'remark':record.get('remark')});
                                            }
                                        })
                                        var paramStr=JSON.stringify(serviceParams);
                                        params['serviceParams']=paramStr;
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
					views : ["core.basicinfomanage.wsmanage.view.ShowWsParamsGrid",
					        "core.basicinfomanage.wsmanage.view.WsCallForm",
							"core.basicinfomanage.wsmanage.view.WsGrid",
							"core.basicinfomanage.wsmanage.view.UpdateWsGrid",
							"core.basicinfomanage.wsmanage.view.DeleteWsGrid",
							"core.basicinfomanage.wsmanage.view.AddWS",
							"core.basicinfomanage.wsmanage.view.UpdateWsForm"],
					stores : ["core.basicinfomanage.wsmanage.store.WsStore",
					          "core.basicinfomanage.wsmanage.store.NewParamStore",
					          "core.basicinfomanage.wsmanage.store.WsParamStore"],
					models : ["core.basicinfomanage.wsmanage.model.WsModel",
					          "core.basicinfomanage.wsmanage.model.WsParamModel"],
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
						var deleteWs = Ext.ComponentQuery.query("panel[xtype=deletewsgrid] button[ref=deleteService]")[0];
						if (deleteWs != null) {
							deleteWs.setDisabled(num == 0);
						}
					}
				});