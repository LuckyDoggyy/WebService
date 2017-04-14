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
                                   }
                               },
                                'bpgrid actioncolumn': {
										  itemclick: function (rec,node) {
										  var autoid= rec.get('autoid');
										  if (node.action == 'flowview') {
											  var window = Ext.create(
													   'Ext.window.Window', {
														  title : '业务',
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
										  	}
										  }
									}
						});
					},
					views : ["core.servicemanage.bpelmanage.view.BPGrid",
							"core.servicemanage.bpelmanage.view.AddBP",
					        "core.servicemanage.bpelmanage.view.BPView"],
					stores : ["core.servicemanage.bpelmanage.store.BPStore"],
					models : ["core.servicemanage.bpelmanage.model.BPModel"]
				});