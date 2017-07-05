Ext.define("core.servicemanage.bpelmanage.controller.BpelController",
				{
					extend : "Ext.app.Controller",
					mixins : {
						suppleUtil : "core.util.SuppleUtil",
						messageUtil : "core.util.MessageUtil",
						formUtil : "core.util.FormUtil",
						jsonUtil : "core.util.JsonUtil",
						treeUtil : "core.util.TreeUtil"
					},
					init : function() {
						var self = this;
						var operator = {};
						this.control({
                             "bpgrid button[ref=searchBP]" : {
                                   click : function(btn) {
                                       this.search(btn);
                                       return false;
                                   }
                               },
                             "updatebpgrid button[ref=searchBP]" : {
                                   click : function(btn) {
                                       this.search(btn);
										return false;
                                   }
                               },
                                "bpusergrid button[ref=searchBP]" : {
                                      click : function(btn) {
                                          this.search(btn);
                                        return false;
                                      }
                                  },

                                "deletebpgrid button[ref=searchBP]" : {
                                   click : function(btn) {
                                       this.search(btn);
                                       return false;
                                   }
                               },
                                "usergrid button[ref=searchPeople]" : {
                                  click : function(btn) {
                                      this.searchUser(btn);
                                      return false;
                                  }
                              },
                               "allusergrid button[ref=searchPeople]" : {
                                 click : function(btn) {
                                     this.searchUser(btn);
                                     return false;
                                 }
                             },
                                'bpgrid actioncolumn': {
										  itemclick: function (rec,node) {
										  		this.operate(rec,node);
										  		return false;
										  }
									},
								'updatebpgrid actioncolumn': {
										  itemclick: function (rec,node) {
										 		this.operate(rec,node);
										 		return false;
									  }
								},
								'deletebpgrid actioncolumn': {
									  itemclick: function (rec,node) {
											this.operate(rec,node);
											return false;
									  }
								},
								 "deletebpgrid button[ref=unableFlow]" : {
											click : function(btn) {
												var grid = btn.up("panel[xtype=deletebpgrid]");
												var records = grid.getSelectionModel().getSelection();
												if(records.length<1){
													Ext.Msg.alert('友情提示',"请勾选数据");
													return false;
												};
												var autoids = new Array();
												for (var i = 0; i < records.length; i++) {
													autoids.push(records[i].get('autoid'));
												}
												var resObj = self.ajax({
															url : "flow/unableFlows",
															params : {
																autoids : autoids.join(",")
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
											}
										},
								 "deletebpgrid button[ref=enableFlow]" : {
											click : function(btn) {
												var grid = btn.up("panel[xtype=deletebpgrid]");
												var records = grid.getSelectionModel().getSelection();
												if(records.length<1){
													Ext.Msg.alert('友情提示',"请勾选数据");
													return false;
												};
												var autoids = new Array();
												for (var i = 0; i < records.length; i++) {
													autoids.push(records[i].get('autoid'));
												}
												var resObj = self.ajax({
															url : "flow/enableFlows",
															params : {
																autoids : autoids.join(",")
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
											}
										},

								 "deletebpgrid button[ref=deleteFlow]" : {
                                                click : function(btn) {
                                                    var grid = btn.up("panel[xtype=deletebpgrid]");
                                                    var records = grid.getSelectionModel().getSelection();
                                                    if(records.length<1){
                                                        Ext.Msg.alert('友情提示',resObj.obj);
                                                        return false;
                                                    };
                                                    var autoids = new Array();
                                                    for (var i = 0; i < records.length; i++) {
                                                        autoids.push(records[i].get('autoid'));
                                                    }
                                                    Ext.Msg.confirm( "流程删除确认","<center><h3>确定要删除选中的流程吗？<h3></center>",
                                                        function(result) {
                                                            if (result == "yes") {
                                                                var resObj = self
                                                                        .ajax({
                                                                            url : "flow/deleteFlows",
                                                                            params : {
                                                                                autoids : autoids.join(",")
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
                                    "panel[xtype=flowcallform] button[ref=call]" : {
                                                  click : function(btn) {
                                                      var flowcallform = btn.up("panel[xtype=flowcallform]");
                                                      var formObj = flowcallform.getForm();
                                                      var params = self.getFormValue(formObj);
                                                      var serviceParams = new Array();
                                                      params.input.split(",").forEach(function(name){
                                                         var value=flowcallform.down("textfield[name="+name+"]").getValue();
                                                         serviceParams.push({'name':name,'value':value});
                                                      });
                                                      var paramStr=JSON.stringify(serviceParams);
                                                      var res = self.ajax({
                                                                  url : "flow/callFlowInternal",
                                                                  params : {
                                                                       autoId:params.autoid,
                                                                       callParams:paramStr
                                                                  }
                                                              });
                                                      var json=self.formatJson(res,false);
                                                      flowcallform.ownerCt.down("panel[xtype=form]").down("textfield[name=callResult]").setValue(json);
                                                      return false;
                                                  }
                                              },

                                     "bpusergrid button[ref=setUser]" : {
                                            click : function(btn) {
                                                var grid = btn.ownerCt.ownerCt;
                                                var records = grid.getSelectionModel().getSelection();
                                                var fid=records[0].get("autoid");
                                                var window = Ext.create(
                                                            'Ext.window.Window', {
                                                                title : '流程用户设置',
                                                                constrain : true,
                                                                maximizable : true,
                                                                maximized : true,
                                                                layout : 'border',
                                                                fixed : true,
                                                                modal : true,
                                                                items : [{
                                                                         region: 'west',
                                                                         title: '所有用户',
                                                                         xtype: "allusergrid",
                                                                         width: 700
                                                                     },{
                                                                         region: 'center',
                                                                         title: ' 禁用用户',
                                                                         xtype: "usergrid"
                                                                     }]
                                                            });
                                                var store=window.down("panel[xtype=usergrid]").getStore();
                                                proxy = store.getProxy();
                                                proxy.extraParams = {
                                                    flowid : fid
                                                };
                                                store.loadPage(1);
                                                var unableButton=window.down("panel[xtype=allusergrid]").down("[xtype=toolbar]").down("button[ref=unable]");
                                                unableButton.html=fid;
                                                var enableButton=window.down("panel[xtype=usergrid]").down("[xtype=toolbar]").down("button[ref=enable]");
                                                enableButton.html=fid;
                                                window.show();
                                                return false;
                                            }
                                        },

                            "panel[xtype=allusergrid] button[ref=unable]" : {
                                click : function(btn) {
                                    var flowid = btn.html;
                                    var grid = btn.up("panel[xtype=allusergrid]");
                                    var records = grid.getSelectionModel().getSelection();
                                    if(records.length<1){
                                        Ext.Msg.alert('友情提示',"请勾选数据");
                                        return false;
                                    };
                                    var uids = new Array();
                                    for (var i = 0; i < records.length; i++) {
                                        uids.push(records[i].get('uid'));
                                    }
                                    var res = self.ajax({
                                        url : "flow/unableUserInFlow",
                                        params : {
                                            uids:uids,
                                            flowid:flowid
                                        }
                                    });
                                    if (res.success) {
                                        var usergrid = Ext.ComponentQuery.query("panel[xtype=usergrid]")[0];
                                        usergrid.getStore().load();
                                        //proxy = store.getProxy();
                                        //proxy.extraParams = {
                                        //    sid : sid,
                                        //    sname : sname
                                        //};
                                        self.msgbox(res.obj);
                                        return false;
                                    } else {
                                        Ext.Msg.alert('友情提示',res.obj);
                                        return false;
                                    }
                                    return false;
                                }
                            },

                            "panel[xtype=usergrid] button[ref=enable]" : {
                                click : function(btn) {
                                    var flowid = btn.html;
                                    var grid = btn.up("panel[xtype=usergrid]");
                                    var records = grid.getSelectionModel().getSelection();
                                    if(records.length<1){
                                        Ext.Msg.alert('友情提示',"请勾选数据");
                                        return false;
                                    };
                                    var uids = new Array();
                                    for (var i = 0; i < records.length; i++) {
                                        uids.push(records[i].get('uid'));
                                    }
                                    var res = self.ajax({
                                        url : "flow/enableUserInFlow",
                                        params : {
                                            uids:uids,
                                            flowid:flowid
                                        }
                                    });
                                    if (res.success) {
                                        grid.getStore().load();
                                        //proxy = store.getProxy();
                                        //proxy.extraParams = {
                                        //    sid : sid,
                                        //    sname : sname
                                        //};
                                        self.msgbox(res.obj);
                                        return false;
                                    } else {
                                        Ext.Msg.alert('友情提示',res.obj);
                                        return false;
                                    }
                                    return false;
                                }
                            },

						});
					},
					views : ["core.servicemanage.bpelmanage.view.BPGrid",
					        "core.servicemanage.bpelmanage.view.FlowCallForm",
							"core.servicemanage.bpelmanage.view.UpdateBPGrid",
							"core.servicemanage.bpelmanage.view.DeleteBPGrid",
							"core.servicemanage.bpelmanage.view.BPUserGrid",
							"core.servicemanage.bpelmanage.view.AllUserGrid",
							"core.servicemanage.bpelmanage.view.UserGrid",
							"core.servicemanage.bpelmanage.view.AddBP",
					        "core.servicemanage.bpelmanage.view.BPView"],
					stores : ["core.servicemanage.bpelmanage.store.BPStore",
					          "core.servicemanage.bpelmanage.store.UserStore",
                              "core.servicemanage.bpelmanage.store.AllUserStore"],
					models : ["core.servicemanage.bpelmanage.model.BPModel"],
					search : function(btn) {
						   var tbar = btn.ownerCt;
						   var flowid = tbar.down("textfield[name=flowid]").getValue();
						   var flowname = tbar.down("textfield[name=flowname]").getValue();
						   var grid = tbar.ownerCt;
						   var _store = grid.getStore();
						   proxy = _store.getProxy();
						   proxy.extraParams = {
							   flowid : flowid,
							   flowname : flowname
						   };
						   _store.loadPage(1);
						   return false;
						   },
                   searchUser : function(btn) {
                                    var tbar = btn.ownerCt;
                                    var uid = tbar.down("textfield[name=uid]").getValue();
                                    var name = tbar.down("textfield[name=account]").getValue();
                                    var grid = tbar.ownerCt;
                                    var _store = grid.getStore();
                                    proxy = _store.getProxy();
                                    proxy.extraParams = {
                                        uid : uid,
                                        name : name
                                    };
                                    _store.loadPage(1);
                                    return false;
                                },
					operate: function (rec,node) {
						  var autoid= rec.get('autoid');
						  if (node.action == 'flowview') {
							  var window = Ext.create(
									   'Ext.window.Window', {
										  title : '流程',
										  constrain : true,
										  maximizable : true,
										  maximized : true,
										  layout : 'fit',
										  fixed : true,
										  modal : false,
										  html: '<iframe id="frame" src="myflow/view.html?'+autoid+'" frameborder="0" width="100%" height="100%"></iframe>'
									   });
							   window.show();
							   return false;
							}else if(node.action =='flowcall'){
								var window = Ext.create('Ext.window.Window', {
                                       title : '流程调用',
                                       constrain : true,
                                       maximizable : true,
                                       maximized : true,
                                       layout : 'border',
                                       fixed : true,
                                       modal : true,
                                        items : [{
                                                 region: 'west',
                                                 xtype : 'flowcallform',
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
                                                        style:'overflow-y:scroll;',
                                                        name: 'callResult',
                                                        height: 800
                                                        }]
                                            }]
                                    });
                                    var form = window.down("panel[xtype=flowcallform]");
                                    form.loadRecord(rec);
                                    var array=rec.get('input').split(",");
                                    array.forEach(function(e){
                                        fd = new Ext.form.TextField({name: e,fieldLabel:e,allowBlank : true});
                                        //form.items.add(form.items.getCount()-1, fd);
                                        form.items.add(fd);
                                    });
                                    form.doLayout();
                                    window.show();
                                    return false;
							}else if(node.action =='flowupdate'){
								var window = Ext.create(
									   'Ext.window.Window', {
										  title : '流程修改',
										  constrain : true,
										  maximizable : true,
										  maximized : true,
										  layout : 'fit',
										  fixed : true,
										  modal : false,
										  html: '<iframe id="frame" src="myflow/update.html?'+autoid+'" frameborder="0" width="100%" height="100%"></iframe>'
									   });
							   window.show();
							   return false;
							}
							return false;
						  },
				});