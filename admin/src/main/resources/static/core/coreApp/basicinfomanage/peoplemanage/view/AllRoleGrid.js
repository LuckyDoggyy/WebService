Ext.define("core.basicinfomanage.peoplemanage.view.AllRoleGrid", {
	extend : "Ext.grid.Panel",
	alias : "widget.allrolegrid",
	title : "<center height=40>全部角色</center>",
	selModel : {
		selType : "checkboxmodel",
		mode : 'SIMPLE'
	},
	multiSelect : true,
	columnLines : true, // 展示竖线
	loadMask : {
		msg : "数据加载中，请稍等..."
	},
	autoScroll : true,
	disableSelection : false,
	enableKeyNav : true, // 可以使用键盘控制上下

	dockedItems : [{
        xtype : 'toolbar',
        dock : 'top',
        items : [{  xtype : 'button',
                    text : '添加到左侧用户',
                    ref : 'addforUser'
                }]
    }],

	columns : [ {
                text : "角色编号",
                align : 'center',
                dataIndex : "rid",
                width : 80
        	}, {
        		text : "角色名称",
        		align : 'center',
        		dataIndex : "rname",
        		width : 200
        	}],

    	store : "core.basicinfomanage.rolemanage.store.RoleStore",
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
    				store : "core.basicinfomanage.rolemanage.store.RoleStore",
    				displayInfo : true,
    				flex : 1
    			}]

});