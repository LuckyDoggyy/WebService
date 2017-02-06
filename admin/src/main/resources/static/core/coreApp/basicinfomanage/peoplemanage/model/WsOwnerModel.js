Ext.define("core.basicinfomanage.peoplemanage.model.WsOwnerModel", {
			extend : "Ext.data.Model",
			fields : [{
            						name : 'autoid'
            					},{
            						name : "uid",
            						type : "string",
            					}, {
            						name : 'username'
            					}, {
                                 	name : 'sid'
                                }, {
                                    name : 'servername'
                                }, {
                                    name : 'ip'
                                }, {
                                    name : 'port'
                                }, {
                                    name : 'detail'
                                }]

		});