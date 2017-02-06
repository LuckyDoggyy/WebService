Ext.define("core.basicinfomanage.peoplemanage.store.WsOwnerStore", {
			extend : 'Ext.data.Store',
			model : 'core.basicinfomanage.peoplemanage.model.WsOwnerModel',
			pageSize : 50,
			proxy : {
				type : "ajax",
				url : "wsowner/list",
				actionMethods : {
					create : 'POST',
					read : 'POST', // by default GET
					update : 'POST',
					destroy : 'POST'
				},
				reader : {
					type : "json",
					root : "rows",
					totalProperty : 'totalCount'
				},
				writer : {
					type : "json"
				}
			},
			autoLoad : false
		});