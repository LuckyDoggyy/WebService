Ext.define("core.systemmanage.rolemanage.view.RoleGrid", {
	extend : "Ext.grid.Panel",
    alias : "widget.rolegrid",
    title : "<center height=40>角色管理</center>",
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

	dockedItems : [ {
    		xtype : 'toolbar',
    		dock : 'top',
    		items : [ {
    			xtype : 'button',
    			text : '增加角色',
    			ref : 'addRole',
    			iconCls : 'table_add'
    		}, {
    			xtype : 'button',
    			text : '删除角色',
    			ref : 'deleteRole',
    			iconCls : 'table_remove',
    			disabled : true
    		}, {
    			xtype : 'button',
    			text : '设置角色权限',
    			ref : 'setroleright',
    			iconCls:'roleright',
    			disabled : true

    		}, {
    			xtype : 'button',
    			text : '人员角色分配与查询',
    			ref : 'addrolepeople',
    			iconCls:'roleadduser',
    			disabled : true
    		}]
    	} ],
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

	store : "core.systemmanage.rolemanage.store.RoleStore",
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
				store : "core.systemmanage.rolemanage.store.RoleStore",
				displayInfo : true,
				flex : 1
			}]

});