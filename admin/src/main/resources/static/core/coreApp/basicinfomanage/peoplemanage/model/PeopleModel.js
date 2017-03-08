Ext.define("core.basicinfomanage.peoplemanage.model.PeopleModel", {
			extend : "Ext.data.Model",
            fields : [{
						name : 'uid'
					},{
						name : "account",
						type : "string",
					},{
						name : "nickName",
						type : "string",
					},{
						name : "remarks",
						type : "string",
					}]
		});