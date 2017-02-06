Ext.define("core.basicinfomanage.wsmanage.model.WsModel", {
			extend : "Ext.data.Model",
			fields : [{
						name : "sid",
						type : "string"
					},{
						name : "name",
						type : "string"
					},{
						name : "ip"
					},{
						name : "port"
					},{
						name : "detail"
					}]
		});