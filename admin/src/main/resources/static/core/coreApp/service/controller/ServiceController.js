Ext.define("core.service.controller.ServiceController",
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

						});
					},
					views : ["core.service.view.ServiceItem"],
					stores : [],
					models : []
				});