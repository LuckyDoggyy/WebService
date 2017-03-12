Ext.define("core.basicinfomanage.menumanage.store.MenuNodeStore", {
			extend : 'Ext.data.Store',
			model : 'core.basicinfomanage.menumanage.model.MenuNode',
			pageSize : 500,
			proxy : {
				type : "ajax",
				url : "menu/listNode",
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