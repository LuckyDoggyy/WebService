Ext.define("core.basicinfomanage.menumanage.model.MenuModel", {
			extend : "Ext.data.Model",
			fields : [{
						name : "mid",
						type : "string"
					},{
						name : "menuname",
						type : "string"
					},{
						name : "pid"
					},{
						name : "pname"
					},{
						name : "viewid"
					},{
						name : "viewname"
					},{
						name : "viewcontroller"
					}]
		});