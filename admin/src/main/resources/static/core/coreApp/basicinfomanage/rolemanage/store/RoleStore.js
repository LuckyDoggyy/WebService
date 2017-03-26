Ext.define("core.basicinfomanage.rolemanage.store.RoleStore", {
           			extend : 'Ext.data.Store',
           			model : 'core.basicinfomanage.rolemanage.model.RoleModel',
           			pageSize : 50,
           			proxy : {
           				type : "ajax",
           				url : "role/listRole",
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
           			autoLoad : true
           		});