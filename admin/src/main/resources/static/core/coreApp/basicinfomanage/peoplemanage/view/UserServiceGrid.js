Ext.define("core.basicinfomanage.peoplemanage.view.UserServiceGrid", {
	extend : "Ext.grid.Panel",
	alias : "widget.userservicegrid",

	selModel : {
		selType : "checkboxmodel",
		mode : 'SIMPLE'
	},
	layout : 'anchor',
	defaults : {
		anchor : '100%'
	},
	align : 'center',
	autoScroll : true,
	buttonAlign : 'center',
	dockedItems : [{
    		xtype : 'toolbar',
    		dock : 'top',
    		height : 36,
    		items : [{
    					xtype : 'button',
    					text : '删除服务',
    					ref : 'deleteUserService',
    					iconCls : 'table_remove',
                       }]
    	}],
    	columns : [{
    				text : "id",
    				dataIndex : "autoid",
    				align: 'center',
    				width : 55
    			},{
                    text : "用户id",
                    dataIndex : "uid",
                    align: 'center',
                    width : 55
                }, {
                    text : "用户名",
                    dataIndex : "username",
                    align: 'center',
                    width : 100
                }, {
    				text : "服务名",
    				dataIndex : "servername",
    				align: 'center',
    				width : 100
    			}, {
    				text : "ip",
    				dataIndex : "ip",
    				align: 'center',
    				width : 100
    			}, {
    				text : "端口",
    				dataIndex : "port",
    				align: 'center',
    				width : 50
    			}, {
    				text : "备注信息",
    				dataIndex : "remarks",
    				align: 'center',
    				width : 100
    			}],
    	store : "core.basicinfomanage.peoplemanage.store.WsOwnerStore",
    	bbar : [{
    				xtype : 'button',
    				text : '全选',
    				handler : function(button, e) {
    					button.ownerCt.ownerCt.getSelectionModel().selectAll();
    				}
    			}, {
    				xtype : 'button',
    				text : '取消',
    				handler : function(button, e) {
    					button.ownerCt.ownerCt.getSelectionModel().deselectAll();
    				}
    			}, {
    				xtype : 'pagingtoolbar',
    				store : "core.basicinfomanage.peoplemanage.store.WsOwnerStore",
    				displayInfo : true,
    				flex : 1
    			}]
    });