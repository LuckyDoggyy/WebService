Ext.define("core.servicemanage.bpelmanage.controller.BpelController",
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
                                "deletebpgrid button[ref=searchBP]" : {
                                   click : function(btn) {
                                       this.search(btn);
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
                                                            }
						});
					},
					views : ["core.servicemanage.bpelmanage.view.BPGrid",
							"core.servicemanage.bpelmanage.view.UpdateBPGrid",
							"core.servicemanage.bpelmanage.view.DeleteBPGrid",
							"core.servicemanage.bpelmanage.view.AddBP",
					        "core.servicemanage.bpelmanage.view.BPView"],
					stores : ["core.servicemanage.bpelmanage.store.BPStore"],
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
								var window = Ext.create(
									   'Ext.window.Window', {
										  title : '流程调用',
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