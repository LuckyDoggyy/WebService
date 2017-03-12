Ext.define("core.basicinfomanage.menumanage.store.MenuStore", {
			extend : 'Ext.data.Store',
			model : 'core.basicinfomanage.menumanage.model.MenuModel',
			pageSize : 500,
			proxy : {
				type : "ajax",
				url : "menu/list",
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