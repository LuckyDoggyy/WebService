Ext.define("core.basicinfomanage.wsmanage.store.WsStore", {
			extend : 'Ext.data.Store',
			model : 'core.basicinfomanage.wsmanage.model.WsModel',
			pageSize : 500,
			proxy : {
				type : "ajax",
				url : "ws/listWs",
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