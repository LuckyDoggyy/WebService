Ext.define("core.basicinfomanage.peoplemanage.view.AllWsGrid", {
	extend : "Ext.grid.Panel",
	alias : "widget.userrolegrid",
	title : "<center height=40>用户角色管理</center>",
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


	[{
                            name : 'autoid'
                        },{
                            name : "uid",
                        },{
                             name : "username",
                        },{
                              name : "rid",
                        },{
                             name : "rolename",
                        }]


	dockedItems : [{
        xtype : 'toolbar',
        dock : 'top',
        items : [{
            xtype : 'button',
            text : '添加到左侧用户',
            ref : 'addforUser'
        }]
    }],

	columns : [{
				text : "自增id",
				dataIndex : "autoid",
				align: 'center',
				width : 60
			}, {
				text : "用户id",
				dataIndex : "uid",
				align: 'center',
				width : 120
			}, {
				text : "用户名",
				dataIndex : "username",
				align: 'center',
				width : 120
			}, {
				text : "角色id",
				dataIndex : "rid",
				align: 'center',
				width : 50
			}, {
				text : "角色名",
				dataIndex : "rolename",
				align: 'center',
				width : 140
			}],
	store : "core.basicinfomanage.peoplemanage.store.UserRoleStore",
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
				store : "core.basicinfomanage.peoplemanage.store.UserRoleStore",
				displayInfo : true,
				flex : 1
			}]

});